package retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import report.Log;

public class Retry implements IRetryAnalyzer{

	int retrylimit = 2;
	int alreadyTry = 0;
	
	@Override
	public boolean retry(ITestResult result) {
		if(alreadyTry < retrylimit) {
			alreadyTry++;
			Log.log("Retrying ...");
			return true;
		}
		return false;
	}

}
