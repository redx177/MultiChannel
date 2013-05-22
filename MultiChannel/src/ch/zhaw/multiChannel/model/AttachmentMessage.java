package ch.zhaw.multiChannel.model;

import java.io.File;
import java.util.Date;

public class AttachmentMessage extends Message {

	private File[] attachments;

	public AttachmentMessage(String[] receivers, String message, Date sendDate,
			File[] attachments) {

		super(receivers, message, sendDate);
		this.attachments = attachments;
	}

	public File[] getAttachments() {
		return attachments;
	}

}
