package ch.zhaw.multiChannel.model;

public class SmsValidator {

	private Message message;

	private SmsValidator(Message message) {

		this.message = message;

	}

	public boolean validate() {

		return true;

	}
}
