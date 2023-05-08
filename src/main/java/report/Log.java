package report;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.Reporter;

import com.aventstack.extentreports.Status;

public class Log {

	static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public static void log(String msg) {
		Reporter.log(msg + "<br>");
		logger.log(Level.INFO, msg);
		ExtentReport.getTest().log(Status.INFO, msg);
	}
}
