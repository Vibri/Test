package cz.vibri.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDao {

	public void addAccount() {
		System.out.println(getClass() + ": DOING STUFF: ADDING A MEMBERSHIT ACCOUNT");
	}
	
	public boolean addSillyMember() {
		System.out.println(getClass() + ": DOING STUFF: ADDING A SILLY MEMBERSHIT ACCOUNT");
		
		return true;
	}
	
	public void goToSleep() {
		System.out.println(getClass() + ": I'm going to sleep now ...");
	}
}
