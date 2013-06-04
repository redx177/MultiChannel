package ch.zhaw.multiChannel.model;

public class SmsValidator implements Validator {

	private Message message;
	private String errorMessage;

	public SmsValidator(Message message) {
		this.message = message;
	}

	public boolean isValid() {
		return validateReceivers();
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	private boolean validateReceivers() {
		String[] receivers = message.getReceivers();
		for(String receiver : receivers) {
			if (receiver == null || !ValidatorHelper.isInteger(receiver)) {
				errorMessage = "Ungültiger Empfänger, nur Nummern erlaubt: " + receiver;
				return false;
			}
		}

		int messageLength = message.getMessage().length();
		if (messageLength > 160) {
			errorMessage = "Die Nachricht hat "+ messageLength +"/160 Zeichen.";
			return false;
		}

		return true;
	}
}
