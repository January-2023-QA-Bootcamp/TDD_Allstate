package auto;

import org.testng.annotations.Test;
import base.TestBase;

public class AutoTest extends TestBase{

	@Test
	public void getA_Quote() {
		homePage.getAQuoteTitleValidation();
	}
}
