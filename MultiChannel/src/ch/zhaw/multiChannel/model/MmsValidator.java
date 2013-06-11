/*
 * Class MmsValidator
 * 
 * Version: 1.0
 *
 * 11.06.2013
 * 
 * This class will make sure that the message was sent without errors.
 *   
 * Copyright ZHAW 2013
 */

package ch.zhaw.multiChannel.model;

import java.io.File;

public class MmsValidator implements Validator {

	private AttachmentMessage attachmentMessage;
	private String errorMessage;

	public MmsValidator(AttachmentMessage attachmentMessage) {
		
		this.attachmentMessage = attachmentMessage;
	}

	public boolean isValid() {
		
		return validateReceiver() && validateAttachement();
	}

	public String getErrorMessage() {
		
		return errorMessage;
	}

	private boolean validateAttachement() {
		
		for (File currentFile : attachmentMessage.getAttachments()) {
			
			String fileName = currentFile.getName();
			int i = fileName.lastIndexOf('.');
			
			if (i > 0) {
				
				String extension = fileName.substring(i + 1);
				
				if (!extension.equals("jpg") && !extension.equals("gif")) {
					
					errorMessage = "Ungültiges File (erlaubt: .jpg, .gif): " + fileName;
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

	private boolean validateReceiver() {
		
		String[] receivers = attachmentMessage.getReceivers();
		
		for(String receiver : receivers) {
			
			if (receiver == null || !ValidatorHelper.isInteger(receiver)) {
				
				errorMessage = "Ungültiger Empfänger, nur Nummern erlaubt: " + receiver;
				return false;
			}
		}
		
		return true;
	}
}
