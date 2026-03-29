package cz.vibri.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class VibriAopExpressions {

	//Pointcut declarations
		@Pointcut("execution(* cz.vibri.aopdemo.dao.*.*(..))")
		public void forDaoPackage() {}
		
		//create point cut for getter methods
		@Pointcut("execution(* cz.vibri.aopdemo.dao.*.get*(..))")
		public void getter() {}
		
		//create point cut for setter methods
		@Pointcut("execution(* cz.vibri.aopdemo.dao.*.set*(..))")
		public void setter() {}
		
		// create point cut: include package ... exclude setter/getter
		@Pointcut("forDaoPackage() && !(getter() || setter())")
		public void forDaoPackageNoGetterSetter() {}
		
}
