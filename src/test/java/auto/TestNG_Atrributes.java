package auto;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import report.Log;
import retry.Retry;

public class TestNG_Atrributes {
	
	@Test(groups = {"testng", "unit1"})
	public void test1() {
		Log.log("This is test1");
	}
	
	@Test(groups = {"testng", "unit2"})
	public void test2() {
		Log.log("This is test2");
		AssertJUnit.fail();
	}
	
	@Test(groups = "unit1", dependsOnMethods = "test2", ignoreMissingDependencies = true)
	public void test3() {
		Log.log("This is test3");
	}
	
	@Test(groups = {"testng", "unit2"})
	public void test4() {
		Log.log("This is test4");
	}
	
	@Test(groups = {"unit2"}, retryAnalyzer = Retry.class)
	public void test5() {
		Log.log("This is test5");
		AssertJUnit.fail();
	}
}
