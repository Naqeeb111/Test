package com.healthpath.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.aspectj.asm.IElementHandleProvider;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.DismissAlert;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.healthpath.selenium.framework.BasePage;

public class RegistrationLoginPage extends BasePage {

	public RegistrationLoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//a[@class='btn btn-white google-login']")
	private WebElement loginWithG;
	
	@FindBy(xpath="//input[@id='email']")
	private WebElement emailField;
	
	@FindBy(xpath="//span[text()='Next']")
	private WebElement nextBtn;
	
	@FindBy(xpath="//input[@type='password']")
	private WebElement enterPassword;
	
	@FindBy(xpath="//ul[contains(@class,'menu')]//a[contains(text(),'Science')]")
	private WebElement science;
	
	@FindBy(xpath="//a[@href='https://healthpath.com/symptoms/']")
	private WebElement Symptoms;
	
	@FindBy(xpath="//ul[contains(@class,'menu')]//a[contains(text(),'About')]")
	private WebElement About;
	
	@FindBy(xpath="//ul[contains(@class,'menu')]//a[contains(text(),'Solutions')]")
	private WebElement Solutions;
	
	@FindBy(xpath="//li[contains(@class,'help more')]//a[contains(@href,'#')][contains(text(),'Help')]")
	private WebElement Help;
	
	@FindBy(xpath="//ul[@class='sub-menu opened']//a[contains(text(),'Blog')]")
	private WebElement Blog;
	
	
	@FindBy(xpath="//a[@class='dropdown-item'][contains(text(),'Community')]")
	private WebElement Community;
	
	@FindBy(xpath="//a[@class='dropdown-item'][contains(text(),'Practitioners')]")
	private WebElement Practitioners;
	
	@FindBy(xpath="//a[@class='dropdown-item'][contains(text(),'FAQs')]")
	private WebElement FAQS;
		
	@FindBy(xpath="//div[@data-action='close-mc-modal']")
	private WebElement ClosedPopUp;
	
	@FindBy(xpath="//*[contains(text(),'ACCEPT COOKIES')]")
	private WebElement AcceptCookies;
	
	/** the settings tab*/
	@FindBy(xpath = "//a[(text()='Settings')]")
	public WebElement settingsTab;
	
	@FindBy(xpath = "//li[contains(@class,'ico user')]//a")
	public WebElement registrationIcon;
	
	@FindBy(xpath = "//a[contains(text(),'Sign up')]")
	public WebElement signUpLink;

	@FindBy(xpath = "//input[@id='first_name']")
	public WebElement fName;
	
	@FindBy(xpath = "//input[@id='last_name']")
	public WebElement lName;
	
	@FindBy(xpath = "//button[@class='btn btn-red']")
	public WebElement signUpBtn;
	
	@FindBy(id = "login")
	private WebElement yopmailLoginIdField;
	
	@FindBy(xpath = "//input[@title='Check inbox @yopmail.com']")
	private WebElement yopmailCheckInboxBttn;
	
	@FindBy(xpath = "//b[contains(text(),'CONFIRM')]")
	private WebElement confirmBtn;
	
	@FindBy(id = "password")
	private WebElement passwordField;
	
	@FindBy(id = "password_confirm")
	private WebElement confirmPwdField;
	
	@FindBy(xpath = "//input[@class='btn btn-red']")
	private WebElement setPasswordBtn;
	
	
	public void setPassword(String password) {
		passwordField.sendKeys(password);
		confirmPwdField.sendKeys(password);
		setPasswordBtn.click();
	}
	
	public void switchToNewTab() throws InterruptedException {
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		waitForElement(passwordField);
	}

	
	
	public void clickOnSignUpBtn() throws InterruptedException {
		waitForElement(signUpBtn);
		signUpBtn.click();
	}
	
	public void clickOnSignUp() throws InterruptedException {
		waitForElement(signUpLink);
		signUpLink.click();
	}
	
	public static String randomId() {
		Random r = new Random();
		List<Integer> id = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			int x = r.nextInt(9999);
			while (id.contains(x))
				x = r.nextInt(9999);
			id.add(x);
		}
		String emailId = String.format("hp"+id.get(0)+"@yopmail.com");
		return emailId;
	}	
	
	public void swithToYopmail(String email) throws InterruptedException {
	Thread.sleep(2000);   
	driver.get("http://www.yopmail.com/en/");
	waitForElement(yopmailLoginIdField);
	yopmailLoginIdField.sendKeys(email);
	yopmailCheckInboxBttn.click();
	driver.switchTo().frame("ifmail");
	Assert.assertTrue(isElementPresent(confirmBtn), "Email is not coming to complete the registration for heltpath application");
	confirmBtn.click();
	
	}
	
	public String enterEmail() throws InterruptedException {
		final String emailId = randomId();
		emailField.sendKeys(emailId);
		Thread.sleep(2000);
		return emailId;
		
	}
	
	public void clickOnRegistrationIcon() throws InterruptedException {
		waitForElement(registrationIcon);
		registrationIcon.click();
	}
	
	
	//Login with Google Account
		public void enterFistandLastName(String firstName, String lastName) throws InterruptedException {
	        Thread.sleep(3000);
	        fName.sendKeys(firstName);
	        lName.sendKeys(lastName);
		}
		
		//Verify Science menu and redirect to associate page
		public void menuScience() throws InterruptedException {
			Thread.sleep(3000);
			//waitForElement(science);
			String str = science.getText();
			System.out.println("Result Matched: " +str);
			Assert.assertTrue(str.contains("SCIENCE"), "Science menu is not appear");
			science.click();
			AcceptCookies.click();
		}
		
		public void refreshDriver() {
			driver.navigate().refresh();
			
		}
		//Verify Symptoms menu and redirect to associate page
		public void menuSymptoms() throws InterruptedException {
			Thread.sleep(3000);
			Actions action = new Actions(driver);
			action.moveToElement(Symptoms).click().build().perform();
			Symptoms.click();
		//	String sym = Symptoms.getText();
			//System.out.println("Result Matched: " +sym);
			//Assert.assertTrue(sym.contains("SYMPTOMS"), "SYMPTOMS menu is not appear");
			//verifyURL("https://healthpath.com/symptoms/");
			//System.out.println("URL Matched");
		}
		
		//Verify About menu and redirect to associate page
		public void menuAbout() {
			waitForElement(About);
			String abo = About.getText();
			System.out.println("Result Matched: " +abo);
			Assert.assertTrue(abo.contains("ABOUT"), "ABOUT menu is not appear");
			About.click();
		}
			
		//Verify Solutions menu and redirect to associate page
		public void menuSolutions() {
			waitForElement(Solutions);
			String sol = Solutions.getText();
			System.out.println("Result Matched: " +sol);
			Assert.assertTrue(sol.contains("SOLUTIONS"), "SOLUTIONS menu is not appear");
			Solutions.click();
		}
		
		public void popUp() {
			waitForElement(ClosedPopUp);
	        ClosedPopUp.click();
	        String currentWindow = driver.getWindowHandle();
			driver.switchTo().window(currentWindow);
	    }
		
		public void switchWindow() throws IOException{
			
		}

		//Verify Help menu and redirect to associate page
		public void menuCommunity() {
			waitForElement(Help);
			String hel = Help.getText();
			System.out.println("Result Matched: " +hel);
			Assert.assertTrue(hel.contains("HELP"), "HELP menu is not appear");
			Help.click();
			waitForElement(Community);
			Community.click();
		}
			
		//Verify Practitioners menu and redirect to associate page
		public void menuPractitioner() {
			waitForElement(Help);
			Help.click();
			waitForElement(Practitioners);
			Practitioners.click();
		}
			
		//Verify FAQS menu and redirect to associate page
		public void menuFAQS() {
			waitForElement(Help);
			Help.click();
			waitForElement(FAQS);
			FAQS.click();
		}	
	
		
}
