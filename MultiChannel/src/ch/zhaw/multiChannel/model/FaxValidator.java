package ch.zhaw.multiChannel.model;

public class FaxValidator {

	private Message message;

	public FaxValidator(Message message) {

		this.message = message;
	}

	private boolean validate() {
		int numberOfReceiver = message.getReceivers().length;
		String[] receivers = message.getReceivers();

		for (int i = 0; i <= numberOfReceiver; i++) {
			if (receivers[i] == null || !isInteger(receivers[i])) {
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
