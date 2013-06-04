package ch.zhaw.multiChannel.model;

public interface Validator {
	boolean isValid();
	String getErrorMessage();
}
