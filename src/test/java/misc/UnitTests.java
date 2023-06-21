package misc;

import org.testng.annotations.Test;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
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
	
	@Test
	public void systemGetPropTest() {
		System.out.println(System.getProperty("user.dir"));
	}
	
	@Test
	public void fileOps() {
		File folder = new File(System.getProperty("user.dir")+"/screenShot");
		System.out.println(folder.exists());
	}
	
	@Test
	public void dateTest() {
		System.out.println(new Date());
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ssss");
		System.out.println(dateFormat.format(new Date()));
		System.out.println(Instant.now());
	}
}
