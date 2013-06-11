/*
 * Interface Page
 * 
 * Version: 1.0
 *
 * 11.06.2013
 * 
 * This Interface will implement the MessagePage. It will offer all
 *  Methods needed in other Class 
 *
 * Copyright ZHAW 2013
 */

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
