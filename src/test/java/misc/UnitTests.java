package misc;

import org.testng.annotations.Test;
import util.Configuration;

public class UnitTests {

	Configuration conf = new Configuration();
	
	@Test
	public void configurationUnitTest() {
		System.out.println(conf.readProp("browser"));
	}
	
	@Test
	public void configNumTest() {
		System.out.println(conf.readPropNum("pageLoad"));
	}
	
}
