/*
 * Interface AttachmentMessagePage
 * 
 * Version: 1.0
 *
 * 11.06.2013
 * 
 * This Interface will offer Methods to other class be able to test the 
 * message format.
 *
 * Copyright ZHAW 2013
 *
*/

package ch.zhaw.multiChannel.model;

public interface Validator {
	
	boolean isValid();
	String getErrorMessage();

}
