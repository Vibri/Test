 package cz.vibri.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cz.vibri.aopdemo.dao.AccountDao;
import cz.vibri.aopdemo.dao.MembershipDao;

public class MainDemoApp {

	public static void main(String[] args) {

		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		// get the bean from spring container
		AccountDao dao = context.getBean("accountDao", AccountDao.class);
		//get membership bean from spring container
		MembershipDao membershipDao = context.getBean("membershipDao", MembershipDao.class);

		// call the business method
		Account myAccount = new Account();
		dao.addAccount(myAccount, true);
		dao.doWork();
		
		// call the accountdao getter/setter methods
		dao.setName("foobar");
		dao.setServiceCode("silver");
		
		String name = dao.getName();
		String serviceCode = dao.getServiceCode();

		// call the membership business method
		membershipDao.addAccount();
		membershipDao.goToSleep();
		
		// call any method with add prefix
		membershipDao.addSillyMember();

		context.close();
	}

}
