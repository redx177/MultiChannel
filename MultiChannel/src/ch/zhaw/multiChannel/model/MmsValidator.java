package ch.zhaw.multiChannel.model;

import java.io.File;

public class MmsValidator {
	
	private AttachmentMessage attachmentMessage;
	public String errorMessage; 
	public MmsValidator(AttachmentMessage attachmentMessage){
		
		this.attachmentMessage = attachmentMessage;
	}
	
	public boolean validate(){
		return validateAttachement();
	}

	private boolean validateAttachement(){
			
		for(File currentFile : attachmentMessage.getAttachments()){
			if(currentFile.canExecute()){
				errorMessage="Exe können nicht verschickt werden!";
				return false;
			}
			if(currentFile.length() > 5242880){
				errorMessage="File ist grösser als 5MB!";
				return false;
			}
			
		}
		return true;
	}
	
}
