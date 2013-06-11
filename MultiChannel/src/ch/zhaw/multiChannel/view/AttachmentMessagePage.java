/*
 * Class AttachmentMessagePage
 * 
 * Version: 1.0
 *
 * 11.06.2013
 * 
 * This Class will enable the user to send a file.
 *
 * Copyright ZHAW 2013
 */

package ch.zhaw.multiChannel.view;

import ch.zhaw.multiChannel.controller.Controller;
import ch.zhaw.multiChannel.model.AttachmentMessage;
import ch.zhaw.multiChannel.model.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AttachmentMessagePage extends MessagePage {

	private ArrayList<File> selectedFiles = new ArrayList<File>();
	private JPanel uploadPanel = new JPanel();
	private JPanel uploadedPanel = new JPanel();
	private String validAttachmentsText;

	public AttachmentMessagePage(Controller controller) {

		super(controller);
	}

	public void show(String title) {

		super.show(title);
	}

	public Message getMessage() {

		String[] receivers = receiverText.getText().split(";");
		String message = messageText.getText();

		if (!timeshiftBox.isSelected()) {

			return new AttachmentMessage(receivers, message, selectedFiles);

		} else {

			String date = dateTextField.getText();
			String time = timeComboBox.getSelectedItem().toString();
			SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyyHH:mm");
			try {
				return new AttachmentMessage(receivers, message, format.parse(dateTextField.getText() + timeComboBox.getSelectedItem()), selectedFiles);
			} catch (ParseException e) {
				showError(String.format("Ungültiges Datum: %s %s", date, time));
				return null;
			}
		}
	}

	protected void loadSendTimePanel(JPanel panel) {

		JPanel sendTimePanel = new JPanel();
		super.loadSendTimePanel(sendTimePanel);

		uploadPanel = new JPanel();
		loadUploadPanel();

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(uploadPanel);
		panel.add(sendTimePanel);
	}

	private void loadUploadPanel() {

		final JPanel uploadFormPanel = new JPanel();
		uploadFormPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton button = new JButton("Upload");
		uploadFormPanel.add(button);
		uploadFormPanel.add(new JLabel(String.format("(Erlaubte Dateien: %s - max 1mb)", validAttachmentsText)));
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(uploadPanel);

				if (returnVal == JFileChooser.APPROVE_OPTION) {

					selectedFiles.add(fc.getSelectedFile());
					repaintUploadedPanel();
				}
			}
		});

		uploadPanel.setLayout(new BoxLayout(uploadPanel, BoxLayout.Y_AXIS));
		uploadPanel.add(uploadFormPanel);
		uploadPanel.add(uploadedPanel);
	}

	private void repaintUploadedPanel() {

		/* Removing old panel.*/
		uploadPanel.remove(uploadedPanel);

		/* Creating new panel.*/
		uploadedPanel = new JPanel();
		uploadedPanel.setLayout(new GridLayout(selectedFiles.size(), 1));
		uploadPanel.add(uploadedPanel);

		/* Populating new panel.*/
		for (File file : selectedFiles) {

			JPanel filePanel = new JPanel();
			filePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			JButton removeButton = new JButton("x");
			filePanel.add(removeButton);
			filePanel.add(new JLabel(file.getName()));
			uploadedPanel.add(filePanel);

			removeButton.addActionListener(new ActionListener() {
				private File file;

				public void actionPerformed(ActionEvent e) {

					selectedFiles.remove(file);
					repaintUploadedPanel();
				}

				private ActionListener init(File file) {

					this.file = file;
					return this;
				}
			}.init(file));
		}

		mainFrame.validate();
		mainFrame.repaint();
	}

	public void setValidAttachmentsText(String validAttachmentsText) {

		this.validAttachmentsText = validAttachmentsText;
	}
}
