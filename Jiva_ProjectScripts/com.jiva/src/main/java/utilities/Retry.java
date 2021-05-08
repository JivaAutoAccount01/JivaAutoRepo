package utilities;

import java.util.concurrent.atomic.AtomicInteger;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	int counter = 0;
	int retryLimit = 1;
	
	AtomicInteger count = new AtomicInteger(retryLimit);
	/*
	 * @see org.testng.IRetryAnalyzer#retry(org.testng.ITestResult)
	 * 
	 * This method decides how many times a test needs to be rerun. TestNg will call
	 * this method every time a test fails. So we can put some code in here to
	 * decide when to rerun the test.
	 * 
	 * Note: This method will return true if a tests needs to be retried and false
	 * it not.
	 *
	 */

	public boolean retry(ITestResult result) {

		if (counter < retryLimit) {
			System.out.println("Going to retry test case: " + result.getMethod() + ", " + (retryLimit - count.intValue() + 1) + " out of " + retryLimit);
			counter++;
			return true;
		}
		return false;
	}
}