package cz.vibri.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class MyCloudLogAsyncAspect {

	@Before("cz.vibri.aopdemo.aspect.VibriAopExpressions.forDaoPackageNoGetterSetter()")
	public void  logToCloudAsync() {
		System.out.println("\n=====>>> Logging to cloud async fashion");
	}
}
