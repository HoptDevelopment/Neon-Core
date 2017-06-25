package com.roguehcf.neon.util;

import java.util.regex.Pattern;

public class NeonStrings {

	public static boolean isAlphanumeric(String s){
		Pattern p = Pattern.compile("[^a-zA-Z0-9]");
		boolean hasSpecialChar = p.matcher(s).find();
		return !(hasSpecialChar);
	}
	
}
