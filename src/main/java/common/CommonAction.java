package common;

import org.openqa.selenium.WebElement;

public class CommonAction {

	public static String getInnerHTML(WebElement element) {
		return element.getAttribute("innerHTML");
	}
}
