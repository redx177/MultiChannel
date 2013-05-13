package ch.zhaw.multiChannel.view;

import ch.zhaw.multiChannel.controller.Controller;
import ch.zhaw.multiChannel.model.Message;

public interface Page {
	public void Show(String title);
	public boolean IsVisible();
	public Message GetMessage();
	public void Close();
}
