package ch.zhaw.multiChannel.model;

import java.util.Date;

public class Message {
	private String[] receivers;
	private String message;
	private Date sendDate;

	public Message(String[] receivers, String message, Date sendDate) {
		this.receivers = receivers;
		this.message = message;
		this.sendDate = sendDate;
	}

	public Message(String[] receivers, String message) {
		this(receivers, message, new Date());
	}

	public String[] getReceivers() {
		return receivers;
	}

	public String getMessage() {
		return message;
	}

	public Date getSendDate() {
		return sendDate;
	}
}
