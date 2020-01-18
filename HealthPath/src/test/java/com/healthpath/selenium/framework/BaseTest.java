package com.healthpath.selenium.framework;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.healthpath.pages.RegistrationLoginPage;
import com.healthpath.selenium.framework.Configuration;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public abstract class BaseTest {
	
	static
	WebDriver driver;
	public static ExtentReports extent;
	private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
	private static final String BREAK_LINE = "</br>";
	public static ExtentTest test;
	/*
	 * protected String userName; protected String password; protected String
	 * browserType; //private WebDriver driver; protected String applicationUrl;
	 * 
	 * // pages object initialization protected LoginPage loginPage; protected
	 * DashboardPage dashBoardPage;
	 * 
	 * enum DriverType { Firefox, IE, Chrome }
	 */

	protected RegistrationLoginPage loginPage;
	protected ExcelReadWrite exceldata;
	protected String applicationUrl;





	
	/*
	 * public BaseTest(String browser) { this.browserType = browser; }
	 */

	@BeforeSuite
	public void before() {
		extent = new ExtentReports("target/surefire-reports/ExtentReport.html", true);
	}

	@BeforeMethod
	public void browserSetUp() throws Exception {
		// Set the Desired Capabilities
		String environment =Configuration.readApplicationFile("env");
		
		switch(environment) {
			case "Firefox":
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.setCapability("marionette", true);
				//firefoxOptions.setHeadless(true);
				driver = new FirefoxDriver(firefoxOptions);
				driver.manage().window().maximize();
				break;
				
			case "IE":
				 WebDriverManager.iedriver().setup();
				//System.setProperty("webdriver.ie.driver",
					//	getPath() + "//src//test//resources//webdriver/IEDriverServer.exe");
			//	InternetExplorerOptions ieoptions = new InternetExplorerOptions();
				//ieoptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				//ieoptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, true);
				driver = new InternetExplorerDriver();
				 driver.manage().window().maximize();
                break;
              
			case "chrome":
                WebDriverManager.chromedriver().setup();
                
                String path = Utilities.getPath();
    			String downloadFilepath = path + "/Exported file folder";
    					
               // String downloadFilepath = "/path/to/download";
                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", downloadFilepath);
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", chromePrefs);
                driver = new ChromeDriver(options);
                
              //  driver = new ChromeDriver();
                driver.manage().window().maximize();

                // Delete cookies
                //driver.manage().deleteAllCookies();
                // open application URL
                break;
                
			case "Edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                driver.manage().window().maximize();
                break;
                
			case "Opera":
                WebDriverManager.operadriver().setup();
                driver = new OperaDriver();
                driver.manage().window().maximize();
                break;


		}
	    this.applicationUrl = Configuration.readApplicationFile("URL");
        getWebDriver().navigate().to(applicationUrl);
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		Thread.sleep(4000);
		
		loginPage = PageFactory.initElements(getWebDriver(), RegistrationLoginPage.class);
		
	
		exceldata = PageFactory.initElements(getWebDriver(), ExcelReadWrite.class);
	}
	@AfterMethod(alwaysRun = true)
	public void afterMainMethod(ITestResult result) throws Exception{
		if (result.getStatus() == ITestResult.FAILURE) {
			captureScreenshot(result);
			String methodname= result.getMethod().getMethodName();
			String classname= getClass().getName();
			System.out.println("CLASSNME: "+classname);
			String summary=	"ERROR IN: "+classname+ ":: "+methodname+ ":: "+result.getThrowable().getMessage().split("\\r?\\n")[0];
			System.out.println(summary);
			test.log(LogStatus.FAIL, summary);
			driver.quit();
			//test.log(LogStatus.FAIL, result.getThrowable().getMessage());
			extent.endTest(test);
			extent.flush();
			
		}
		else{
			driver.quit();
			//test.log(LogStatus.FAIL, result.getThrowable().getMessage());
			extent.endTest(test);
			extent.flush();
		}
		
		
	}
	
	@AfterSuite
	public void tearDownSuite() {
		//reporter.endReport();
		driver.quit();
		extent.flush();
		extent.close();
	}
	/*
	 * @AfterSuite public void tearDownSuite() { // reporter.endReport(); }
	 */

	public WebDriver getWebDriver() {
		return driver;
	}


	// Get absolute path
	public static String getPath() {
		String path = "";
		File file = new File("");
		String absolutePathOfFirstFile = file.getAbsolutePath();
		path = absolutePathOfFirstFile.replaceAll("\\\\+", "/");
		return path;
	}

	/*
	 * // Get absolute path public String getPathUpload() { String path = "";
	 * File file = new File(""); String absolutePathOfFirstFile =
	 * file.getAbsolutePath(); path = absolutePathOfFirstFile.replaceAll("/",
	 * "//"); return path; }
	 */

	/* capturing screenshot */
	public void captureScreenshot(ITestResult result) throws IOException, InterruptedException {
		try {
			String screenshotName = Utilities.getFileName(result.getName());
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String path = Utilities.getPath();
			String screen = path + "/screenshots/" + screenshotName + ".png";
			File screenshotLocation = new File(screen);
			FileUtils.copyFile(screenshot, screenshotLocation);
			Thread.sleep(2000);
			String image= test.addScreenCapture(screen);
			test.log(LogStatus.FAIL, screenshotName, image);

			Reporter.log(
					"<a href= '" + screen + "'target='_blank' ><img src='" + screen + "'>" + screenshotName + "</a>");
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	public void reportLog(String message) {
		// test.log(LogStatus.INFO, message);
		message = BREAK_LINE + message;
		logger.info("Message: " + message);
		Reporter.log(message);
	}

	// Creating file name
	public String getFileName(String file) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String fileName = file + dateFormat.format(cal.getTime());
		return fileName;
	}	
}
