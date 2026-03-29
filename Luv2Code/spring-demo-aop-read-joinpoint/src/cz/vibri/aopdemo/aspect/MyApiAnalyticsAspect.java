package cz.vibri.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class MyApiAnalyticsAspect {

	@Before("cz.vibri.aopdemo.aspect.VibriAopExpressions.forDaoPackageNoGetterSetter()")
	public void  performApiAnalytics() {
		System.out.println("\n=====>>> Performing API analytics");
	}
}
