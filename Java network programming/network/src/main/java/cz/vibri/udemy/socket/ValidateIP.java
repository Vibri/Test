package cz.vibri.udemy.socket;

import org.apache.commons.validator.routines.InetAddressValidator;

public class ValidateIP {

	public static void main(String[] args) {
		InetAddressValidator validator = new InetAddressValidator();
		String ipAddress = "192.168.0.321";
		boolean isValid = validator.isValid(ipAddress);
		if (isValid) {
			System.out.println(ipAddress + " is valid");
		} else {
			System.out.println(ipAddress + " is not valid");
		}

	}

	public boolean validateIpAddress(String ipAddress) {
		String[] numbers = ipAddress.split("\\.");
		if (numbers.length != 4) {
			return false;
		}

		for (String str : numbers) {
			int i = Integer.parseInt(str);
			if ((i < 0) || (i > 255)) {
				return false;
			}
		}
		return true;
	}

}
