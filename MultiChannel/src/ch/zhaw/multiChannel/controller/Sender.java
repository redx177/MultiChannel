/*
 * Class Sender
 * 
 * Version: 1.0
 *
 * 11.06.2013
 * 
 * This Class will offer the Methods to send a message.
 *
 * Copyright ZHAW 2013
 */

package ch.zhaw.multiChannel.controller;

import ch.zhaw.multiChannel.model.AttachmentMessage;
import ch.zhaw.multiChannel.model.Message;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Sender {

	public void send(Controller.PageType type, Message message) {

		String[] receivers = message.getReceivers();
		int length = receivers.length;
		String s = length == 1 ? "" : "s";
		String joinedReceivers = receivers[0];

		for (int i = 1; i < length; i++) {
			joinedReceivers += "\n - " + receivers[i];
		}

		System.out.printf("Sending %s:%n---------------%n", getTypeAsString(type));
		System.out.printf(String.format("To %d receiver%s:%n - %s%n%n", length, s, joinedReceivers));
		System.out.printf("Sending on: %s%n%n", new SimpleDateFormat("dd.MM.yyyy HH:mm").format(message.getSendDate()));
		System.out.printf("Message:%n%s%n%n", message.getMessage().length() > 0 ? message.getMessage() : "<<No Message>>");
	}

	public void send(Controller.PageType type, AttachmentMessage attachmentMessage) {

		send(type, (Message) attachmentMessage);

		if (attachmentMessage.getAttachments().size() > 0) {
			String s = attachmentMessage.getAttachments().size() == 1 ? "" : "s";
			System.out.printf("Attachment%s:%n%s%n", s, getAttachmentsAsString(attachmentMessage.getAttachments()));
		}
	}

	private String getAttachmentsAsString(ArrayList<File> attachments) {

		String result = "";

		for (File file : attachments) {
			result += " - " + file.getName() + "\n";
		}

		return result;
	}

	private String getTypeAsString(Controller.PageType type) {

		String typeDescription;
		switch (type) {
			case Sms:
				return "SMS";
			case Fax:
				return "Fax";
			case Mms:
				return "MMS";
			case Email:
				return "Email";
		}
		return "";
	}
}
