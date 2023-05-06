package retry;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class RetryAll implements IAnnotationTransformer{

	@SuppressWarnings("rawtypes")
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, 
			Constructor testConstructor, Method testMethod) {
		//Log.log(">>>>> " + testMethod.getName() + " <<<<<");
		annotation.setRetryAnalyzer(Retry.class);
	}
}
