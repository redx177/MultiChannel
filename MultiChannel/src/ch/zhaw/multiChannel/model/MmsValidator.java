package ch.zhaw.multiChannel.model;

public class MmsValidator {
	
	private AttachmentMessage attachmentMessage;
	
	public MmsValidator(AttachmentMessage attachmentMessage){
		
		this.attachmentMessage = attachmentMessage;
	}
	
	public boolean validate(){
		
		return true;
	}

}
