package cz.vibri.aopdemo.dao;

import org.springframework.stereotype.Component;

import cz.vibri.aopdemo.Account;

@Component
public class AccountDao {
	
	private String name;
	private String serviceCode;

	public void addAccount(Account account, boolean vipFlag) {
		System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
	}
	
	public boolean doWork() {
		System.out.println(getClass() + ": doWork()");
		return true;
	}
	
	public String getName() {
		System.out.println(getClass() + ": getName()");
		return name;
	}
	
	public void setName(String name) {
		System.out.println(getClass() + ": setName()");
		this.name = name;
	}
	
	public String getServiceCode() {
		System.out.println(getClass() + ": getServiceCode()");
		return serviceCode;
	}
	
	public void setServiceCode(String serviceCode) {
		System.out.println(getClass() + ": setServiceCode()");
		this.serviceCode = serviceCode;
	}
}
