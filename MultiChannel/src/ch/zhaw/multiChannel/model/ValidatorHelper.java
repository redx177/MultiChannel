/*
 * Interface ValidatorHelper
 * 
 * Version: 1.0
 *
 * 11.06.2013
 * 
 * This Interface will offer Methods to help the class Validator.
 *
 * Copyright ZHAW 2013
 *
*/

package ch.zhaw.multiChannel.model;

public class ValidatorHelper {

	public static boolean isInteger(String input) {
	
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
