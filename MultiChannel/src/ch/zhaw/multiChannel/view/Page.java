package ch.zhaw.multiChannel.view;

import ch.zhaw.multiChannel.controller.Controller;
import ch.zhaw.multiChannel.model.Message;

public interface Page {
	public void show(String title);
	public boolean isVisible();
	public Message getMessage();
	public void close();
	void showError(String errorMessage);
	void showNotification(String message);
}
