package com.healthpath.selenium.framework;
import org.junit.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.concurrent.TimeUnit;
import java.net.URL;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;

public class UWPApp {
	
	 private static WindowsDriver<WindowsElement> CalculatorSession;
    private static WebElement CalculatorResult = null;
	public static void main(String[] args) {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("app", "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
            CalculatorSession = new WindowsDriver<WindowsElement>(new URL("http://127.0.0.1:4723/"), capabilities);
            CalculatorSession.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

            CalculatorResult = CalculatorSession.findElementByAccessibilityId("CalculatorResults");
            Assert.assertNotNull(CalculatorResult);
            CalculatorSession.findElementByName("Clear").click();
            

        }catch(Exception e){
            e.printStackTrace();
        } finally {
        }
    }

}
