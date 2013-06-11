/*
 * Class SmsValidator
 * 
 * Version: 1.0
 *
 * 11.06.2013
 * 
 * This class will make sure that the message was sent without errors.
 *   
 * Copyright ZHAW 2013
 */

package ch.zhaw.multiChannel.model;

public class SmsValidator implements Validator {

	private Message message;
	private String errorMessage;

	public SmsValidator(Message message) {

		this.message = message;
	}

	public boolean isValid() {

		return validateReceivers() && validateMessage();
	}

	public String getErrorMessage() {

		return errorMessage;
	}

	private boolean validateReceivers() {

		String[] receivers = message.getReceivers();

		for (String receiver : receivers) {
			if (receiver == null || !ValidatorHelper.isInteger(receiver)) {
				errorMessage = "Ungültiger Empfänger, nur Nummern erlaubt: " + receiver;
				return false;
			}
		}

		return true;
	}

	private boolean validateMessage() {

		int messageLength = message.getMessage().length();

		if (messageLength > 160) {
			errorMessage = "Die Nachricht hat " + messageLength + "/160 Zeichen.";
			return false;
		}

		return true;
	}
}
