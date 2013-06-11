/*
 * Class Controller
 * 
 * Version: 1.0
 *
 * 11.06.2013
 * 
 * This Class control programm workflow.
 *
 * Copyright ZHAW 2013
 */

package ch.zhaw.multiChannel.controller;

import ch.zhaw.multiChannel.model.*;
import ch.zhaw.multiChannel.view.*;

public class Controller {

	private Page openedPage;
	private PageType currentPageType;
	private StartPage startPage;
	
	public static void main(String[] args) {
		
		new Controller().start();
	}

	private void start() {
		
		startPage = new StartPage(this);
		startPage.show();
	}

	public void composeSms() {
		
		currentPageType = PageType.Sms;
		openMessagePage("SMS");
	}

	public void composeFax() {
		
		currentPageType = PageType.Fax;
		openMessagePage("Fax");
	}

	public void composeMms() {
		
		currentPageType = PageType.Mms;
		openAttachmentMessagePage(PageType.Mms, "MMS");
	}

	public void composeEmail() {
		
		currentPageType = PageType.Email;
		openAttachmentMessagePage(PageType.Email, "E-Mail");
	}

	private void openMessagePage(String title) {
		
		if (openedPage != null && openedPage.isVisible()) {		
			startPage.ShowOnlyOnePageMessage();
			return;
		}
		
		openedPage = new MessagePage(this);
		openedPage.show(title);
	}

	private void openAttachmentMessagePage(PageType pageType, String title) {
		
		if (openedPage != null && openedPage.isVisible()) {
			startPage.ShowOnlyOnePageMessage();
			return;
		}

		String validAttachmentsText = pageType == PageType.Mms ? ".jpg, .gif" : "alles ausser .exe";

		openedPage = new AttachmentMessagePage(this);
		((AttachmentMessagePage)openedPage).setValidAttachmentsText(validAttachmentsText);
		openedPage.show(title);
	}

	public void sendMessageRequest() {
		
		Message message = openedPage.getMessage();

		Validator validator = null;
		try {
			validator = getValidator(message);
		} catch (Exception e) {
			openedPage.showError(e.getMessage());
		}

		assert validator != null;
		
		if (!validator.isValid()) {
			openedPage.showError(validator.getErrorMessage());
			return;
		}

		if (currentPageType == PageType.Mms || currentPageType == PageType.Email) {
			new Sender().send(currentPageType, (AttachmentMessage)message);
		
		} else {

			new Sender().send(currentPageType, message);
		}

		openedPage.showNotification("Vielen Dank, Ihre Nachricht wurde versendet.");
		openedPage.close();
	}

	private Validator getValidator(Message message) throws Exception {
		
		Validator validator;
		switch (currentPageType) {
		
			case Sms:
				validator = new SmsValidator(message);
				break;
			case Fax:
				validator = new FaxValidator(message);
				break;
			case Mms:
				validator = new MmsValidator((AttachmentMessage) message);
				break;
			case Email:
				validator = new EmailValidator((AttachmentMessage) message);
				break;
			default:
				throw new Exception("Unknown page type " + currentPageType);
		}
		
		return validator;
	}

	public enum PageType {
		
		Sms,
		Mms,
		Email,
		Fax,
	}
}
