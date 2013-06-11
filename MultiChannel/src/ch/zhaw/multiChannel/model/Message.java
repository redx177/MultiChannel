/*
 * Class Message
 * 
 * Version: 1.0
 *
 * 11.06.2013
 * 
 * This class will create a new Message with Date, Receiver and Text.
 *   
 * Copyright ZHAW 2013
 */

package ch.zhaw.multiChannel.model;

import java.util.Date;

public class Message {

	private Date sendDate;
	private String[] receivers;
	private String message;

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
