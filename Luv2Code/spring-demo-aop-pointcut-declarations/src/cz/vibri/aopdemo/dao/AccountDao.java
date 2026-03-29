package cz.vibri.aopdemo.dao;

import org.springframework.stereotype.Component;

import cz.vibri.aopdemo.Account;

@Component
public class AccountDao {

	public void addAccount(Account account, boolean vipFlag) {
		System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
	}
	
	public boolean doWork() {
		System.out.println(getClass() + ": doWork()");
		return true;
	}
}
