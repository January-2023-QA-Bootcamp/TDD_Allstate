package object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import static common.CommonAction.*;
import static common.CommonWait.*;

public class DriverDetails {

	WebDriver driver;
	public DriverDetails(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		init(driver);
	}
	
	@FindBy(xpath = "//div[@class='page-header']/child::h3")
	WebElement title;
	
	@FindBy(name = "driver[0].firstName")
	WebElement firstName;
	
	@FindBy(name = "driver[0].middleInitial")
	WebElement middleInitial;
	
	@FindBy(name = "driver[0].lastName")
	WebElement lastName;
	
	@FindBy(name = "driver[0].dob")
	WebElement dobElement;
	
	@FindBy(name = "driver[0].maritalStatus")
	WebElement maritalStatus;
	
	@FindBy(xpath = "//a[contains(.,'Continue')]")
	WebElement continueBtn;
	
	public void validateTitle(String expected) {
		waitUntilVisible(firstName);
		Assert.assertEquals(getText(title), expected);
	}
	
	public void insertFirstName(String firstNameText) {
		insert(firstName, firstNameText);
	}
	
	public void insertMiddleName(char middleNameChar) {
		insert(middleInitial, middleNameChar);
	}
	
	public void insertLastName(String lastNameText) {
		insert(lastName, lastNameText);
	}
	
	public void insertDOB(String dob) {
		insert(dobElement, dob);
	}
	
	public void selectGender(String gender) {
		WebElement element = driver.findElement(By.xpath("//input[@name='driver[0].gender']/following::label[text()='" + gender + "']"));
		click(element);
	}
	
	public void selectMaritalStatus(String value) {
		dropdown(maritalStatus, value);
	}
	
	public void selectSR22(char isSR22) {
		String temp = String.valueOf(isSR22).toUpperCase();
		WebElement element = driver.findElement(By.xpath("//input/following::label[@for='r22CaseNumberSelect1" + temp + "']"));
		click(element);
	}

	public void selectAnyAccident(boolean hasAccident) {
		WebElement element;
		if(hasAccident) {
			element = driver.findElement(By.xpath("//label[@id='daccCntY0lbl']"));
		}else {
			element = driver.findElement(By.xpath("//label[@id='daccCntN0lbl']"));
		}
		click(element);
	}
	
	public void selectAdditionalDriver(char hasAdditionalDriver) {
		String temp = String.valueOf(hasAdditionalDriver).toUpperCase();
		WebElement element = driver.findElement(By.xpath("//label[@for='ans1_" + temp + "']"));
		click(element);
	}
	
	public void clickContinueBtn() {
		click(continueBtn);
	}
}
