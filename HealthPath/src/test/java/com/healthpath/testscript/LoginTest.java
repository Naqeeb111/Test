package com.healthpath.testscript;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.healthpath.selenium.framework.BaseTest;
import com.healthpath.selenium.framework.ExcelReadWrite;
import com.relevantcodes.extentreports.LogStatus;


public class LoginTest extends BaseTest{


	@Test
	public void loginTest() throws Exception {
		 
		 String firstName =ExcelReadWrite.getValueofColumnFromExcel("Registration", "FitstName", 1);
		 String lastName =ExcelReadWrite.getValueofColumnFromExcel("Registration", "LastName", 1);
		  
		 test=extent.startTest("TempConnect Login and verify the links of devise and data Test");
		 reportLog("TempConnect Login and verify the links of devise and data Test");
		 
		 reportLog("Click on Registration Icon");
		 loginPage.clickOnRegistrationIcon();

		 reportLog("Click on SignUp link");
		 loginPage.clickOnSignUp();
		 
		 reportLog("Enter Randum email ID");
		 String email = loginPage.enterEmail();
		 
		 reportLog("Enter First name and Last Name");
		 loginPage.enterFistandLastName(firstName,lastName);
		 
		 reportLog("Click on SignUp Button");
		 loginPage.clickOnSignUpBtn();
		 
		 reportLog("Swith to yopmail and verify email");
		 loginPage.swithToYopmail(email);
		 loginPage.switchToNewTab();
		 
		 reportLog("Set password");
		 loginPage.setPassword(ExcelReadWrite.getValueofColumnFromExcel("Registration", "Password", 1));
		 
		 	 
		 
		 Thread.sleep(10000);
		 
		 //reportLog("Verified after click on Science menu page redirect to associate page");
		// loginPage.menuScience();
		 
		// reportLog("Handle Subscribe popup");
		// loginPage.popUp();
		 //loginPage.refreshDriver();
		 
		// loginPage.menuScience();
		// reportLog("Verified after click on Symptoms menu page redirect to associate page");
		// loginPage.menuSymptoms();
		 
		 //reportLog("Verified after click on About menu page redirect to associate page");
		 //loginPage.menuAbout();
		 
		// reportLog("Verified after click on Solutions menu page redirect to associate page");
		// loginPage.menuSolutions();
		 
		// reportLog("Verified after click on Community menu page redirect to associate page");
		// loginPage.menuCommunity();
		 
		// reportLog("Verified after click on Practitioner menu page redirect to associate page");
		// loginPage.menuPractitioner();
		 
		// reportLog("Verified after click on FAQS menu page redirect to associate page");
		// loginPage.menuFAQS();
}
}


