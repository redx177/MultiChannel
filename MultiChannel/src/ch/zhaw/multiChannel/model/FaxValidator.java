/*
 * Class FaxValidator
 * 
 * Version: 1.0
 *
 * 11.06.2013
 * 
 * This class will make sure that the Fax message was sent without errors.
 *   
 * Copyright ZHAW 2013
 */

package ch.zhaw.multiChannel.model;

public class FaxValidator implements Validator {

	private Message message;
	private String errorMessage;

	public FaxValidator(Message message) {

		this.message = message;
	}

	public boolean isValid() {

		String[] receivers = message.getReceivers();

		for (String receiver : receivers) {
			if (receiver == null || !ValidatorHelper.isInteger(receiver)) {
				errorMessage = "Ungültiger Empfänger, nur Nummern erlaubt: " + receiver;
				return false;
			}
		}

		return true;
	}

	public String getErrorMessage() {

		return errorMessage;
	}
}
