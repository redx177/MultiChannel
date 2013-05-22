package ch.zhaw.multiChannel.model;

public class SmsValidator {

	private Message message;

	private SmsValidator(Message message) {

		this.message = message;

	}

	public boolean validate() {

		return validateReceivers();

	}

	private boolean validateReceivers() {
		int numberOfReceiver = message.getReceivers().length;
		String[] receivers = message.getReceivers();
		String message = this.message.getMessage();
		for (int i = 0; i <= numberOfReceiver; i++) {
			if (receivers[i] == null || !isInteger(receivers[i])) {
				return false;
			}
			if (message.length() > 160) {
				System.out.println("The message size exceedes the accepted size");
				return false;
			}

		}

		return true;
	}

	private boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
