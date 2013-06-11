/*
 * Class EmailValidator
 * 
 * Version: 1.0
 *
 * 11.06.2013
 * 
 * This class will make sure that the mail was sent without errors.
 *   
 * Copyright ZHAW 2013
 */

package ch.zhaw.multiChannel.model;

import java.io.File;

public class EmailValidator implements Validator {

	private AttachmentMessage attachmentMessage;
	public String errorMessage;

	public EmailValidator(AttachmentMessage attachmentMessage) {

		this.attachmentMessage = attachmentMessage;
	}

	public boolean isValid() {

		return validateReceivers() && validateAttachement();
	}

	public String getErrorMessage() {

		return errorMessage;
	}

	private boolean validateReceivers() {

		for (String email : attachmentMessage.getReceivers()) {

			if (!isValidEmail(email)) {
				errorMessage = "Ungültige EMail: " + email;
				return false;
			}
		}
		return true;
	}

	private boolean validateAttachement() {

		for (File currentFile : attachmentMessage.getAttachments()) {
			String fileName = currentFile.getName();
			int i = fileName.lastIndexOf('.');
			if (i > 0) {
				String extension = fileName.substring(i + 1);
				if (extension.equals("exe")) {
					errorMessage = "Ungültiges File (erlaubt ist alles ausser .exe): " + fileName;
					return false;
				}
			}

			if (currentFile.length() > 1048576) {
				errorMessage = "File ist grösser als 1MB: " + fileName;
				return false;
			}
		}

		return true;
	}

	private static boolean isValidEmail(String email) {
		return email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	}
}
