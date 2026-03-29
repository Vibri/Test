package cz.vibri.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

	//Pointcut declarations
	@Pointcut("execution(* cz.vibri.aopdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	//create point cut for getter methods
	@Pointcut("execution(* cz.vibri.aopdemo.dao.*.get*(..))")
	private void getter() {}
	
	//create point cut for setter methods
	@Pointcut("execution(* cz.vibri.aopdemo.dao.*.set*(..))")
	private void setter() {}
	
	// create point cut: include package ... exclude setter/getter
	@Pointcut("forDaoPackage() && !(getter() || setter())")
	private void forDaoPAckageNoGetterSetter() {}
	
	// this is where we add all of our related advices for logging

	// let's start with an @Before advice
//	@Before("execution(public void addAccount())")
//	public void beforeAddAccountAdvice() {
//		System.out.println("\n=====>>> Executing @Before advice on addAccount()");
//	}
	
	// fully qualified detection
//	@Before("execution(public void cz.vibri.aopdemo.dao.AccountDao.addAccount())")
//	public void beforeFullyQualifiedAddAccountAdvice() {
//		System.out.println("\n=====>>> Executing @Before fully qualified advice on addAccount()");
//	}
	
	// any method with add prefix in method name
//	@Before("execution(* add*(cz.vibri.aopdemo.Account, ..))")
//	public void beforeAnyAddAdvice() {
//		System.out.println("\n=====>>> Executing @Before advice on any add*()");
//	}
	
	// any method with add prefix in method name and an number of parameters
//	@Before("execution(* add*(..))")
//	public void beforeAnyAddAdviceAnyParameters() {
//		System.out.println("\n=====>>> Executing @Before advice on any add*()");
//	}
	
	// pointcut on methods in package
	@Before("forDaoPAckageNoGetterSetter()")
	public void beforeAnyMethodInPackage() {
		System.out.println("\n=====>>> Executing @Before advice on any method in package");
	}
	
	@Before("forDaoPAckageNoGetterSetter()")
	public void  performApiAnalytics() {
		System.out.println("\n=====>>> Performing API analytics");
	}
}
