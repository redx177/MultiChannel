package ch.zhaw.multiChannel.controller;

import ch.zhaw.multiChannel.model.AttachmentMessage;
import ch.zhaw.multiChannel.model.Message;
import com.sun.deploy.util.StringUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Sender {

	public void send(Controller.PageType type, Message message) {

		int length = message.getReceivers().length;
		String s = length == 1 ? "" : "s";

		System.out.printf("Sending %s:%n---------------%n", getTypeAsString(type));
		System.out.printf(String.format("To %d receiver%s:%n - %s%n%n", length, s, StringUtils.join(Arrays.asList(message.getReceivers()), "\n - ")));
		System.out.printf("Sending on: %s%n%n", new SimpleDateFormat("dd.MM.yyyy HH:mm").format(message.getSendDate()));
		System.out.printf("Message:%n%s%n%n", message.getMessage().length() > 0 ? message.getMessage() : "<<No Message>>");
	}

	public void send(Controller.PageType type, AttachmentMessage attachmentMessage) {
		send(type, (Message)attachmentMessage);

		if (attachmentMessage.getAttachments().size() > 0) {
			String s = attachmentMessage.getAttachments().size() == 1 ? "" : "s";
			System.out.printf("Attachment%s:%n%s%n", s, getAttachmentsAsString(attachmentMessage.getAttachments()));
		}
	}

	private String getAttachmentsAsString(ArrayList<File> attachments) {
		String result = "";
		for (File file : attachments) {
			result+= " - " + file.getName() + "\n";
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
