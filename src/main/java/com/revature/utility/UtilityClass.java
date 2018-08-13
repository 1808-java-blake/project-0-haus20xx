package com.revature.utility;

public abstract class UtilityClass {
	
	public static String removePunctuation(String input) {
		String returned = "";
		for(int i = 0;i<input.length();i++) {
			if (Character.isDigit(input.charAt(i))) {
				returned = returned + input.charAt(i);
			}
		}
		return returned;
	}
	
	
}
