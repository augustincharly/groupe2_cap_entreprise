package com.humanbooster.groupe2_cap_entreprise.utils;

public class Utils {

	public static Long stringToLong(String number) {
		Long numberToReturn = null;
		try {
			numberToReturn = Long.parseLong(number);
			return numberToReturn;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} 
		return numberToReturn;
	}

}
