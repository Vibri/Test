package cz.vibri.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

	// pointcut on methods in package
	@Before("cz.vibri.aopdemo.aspect.VibriAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAnyMethodInPackage() {
		System.out.println("\n=====>>> Executing @Before advice on any method in package");
	}
	
}
