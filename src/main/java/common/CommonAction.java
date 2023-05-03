package common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import report.Log;

public class CommonAction {

	public static String getInnerHTML(WebElement element) {
		String innerText = element.getAttribute("innerHTML");
		Log.log(element + " <<< Inner Text : "+innerText);
		return innerText;
	}
	
	public static String getText(WebElement element) {
		String innerText = element.getText();
		Log.log(element + " <<< Inner Text : "+innerText);
		return innerText;
	}
	
	public static void click(WebElement element) {
		element.click();
		Log.log(element + " Has been clicked");
	}
	
	public static void insert(WebElement element, String text) {
		element.sendKeys(text);
		Log.log(element + " <<< value inserted : " + text);
	}
	
	public static void insert(WebElement element, char text) {
		String temp = String.valueOf(text);
		element.sendKeys(temp);
		Log.log(element + " <<< value inserted : " + text);
	}
	
	public static void dropdown(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByVisibleText(value);
		//select.selectByValue(value);
		Log.log(element + " <<< selected : " + value);
	}
}
