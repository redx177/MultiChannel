package ch.zhaw.multiChannel.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class AttachmentMessage extends Message {

	private ArrayList<File> attachments;

	public AttachmentMessage(String[] receivers, String message, Date sendDate, ArrayList<File> attachments) {
		super(receivers, message, sendDate);
		this.attachments = attachments;
	}

	public AttachmentMessage(String[] receivers, String message, ArrayList<File> attachments) {
		super(receivers, message);
		this.attachments = attachments;
	}

	public ArrayList<File> getAttachments() {
		return attachments;
	}

}
