package ch.zhaw.multiChannel.model;

public class FaxValidator implements Validator {

	private Message message;
	private String errorMessage;

	public FaxValidator(Message message) {
		this.message = message;
	}

	public boolean isValid() {
		String[] receivers = message.getReceivers();
		for(String receiver : receivers) {
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
