package ch.zhaw.multiChannel.view;

import ch.zhaw.multiChannel.controller.Controller;
import ch.zhaw.multiChannel.model.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;


public class MessagePage implements Page {
	private Controller controller;
	String uhrzeit_String = new String();

	JLabel receiverLabel = new JLabel("Empfänger:");
	JTextField receiverText = new JTextField(15);
	JTextArea messageText = new JTextArea(15, 40);
	JButton sendButton = new JButton("Nachricht senden");
	JCheckBox timeshiftBox = new JCheckBox("Zeit versetzt senden?");
	JTextField dateTextField = new JTextField(20);
	JComboBox timeComboBox;


	JPanel mainPanel = new JPanel();
	JPanel upperPanel = new JPanel();
	JPanel middlePanel = new JPanel();
	JPanel lowerPanel = new JPanel();

	JFrame mainFrame = new JFrame();

	public MessagePage(Controller controller) {
		this.controller = controller;
	}

	public void Show(String title) {
		timeComboBox = new JComboBox(getTimeList());
		timeComboBox.setEditable(false);
		dateTextField.setText("sofort senden");
		dateTextField.setEditable(false);
		upperPanel.setLayout(new FlowLayout());
		upperPanel.add(receiverLabel);
		upperPanel.add(receiverText);

		middlePanel.setLayout(new FlowLayout());
		middlePanel.add(messageText);

		lowerPanel.setLayout(new FlowLayout());
		lowerPanel.add(timeshiftBox);
		lowerPanel.add(dateTextField);
		lowerPanel.add(timeComboBox);
		lowerPanel.add(sendButton);

		mainPanel.setSize(600, 700);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(BorderLayout.NORTH, upperPanel);
		mainPanel.add(BorderLayout.WEST, middlePanel);
		mainPanel.add(BorderLayout.SOUTH, lowerPanel);

		mainFrame.getContentPane().add(mainPanel);
		mainFrame.setSize(600, 500);
		mainFrame.setVisible(true);
		mainFrame.setTitle(title);
		mainFrame.pack();

		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		timeshiftBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent datum) {
				if (timeshiftBox.isSelected()) {
					dateTextField.setText(new DatePicker(mainFrame).setPickedDate());
					timeComboBox.setEditable(true);
				} else {
					dateTextField.setText("Zeitversetzt senden?");
					timeComboBox.setEditable(false);
				}
			}
		});
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.SendMessageRequest();
			}
		});
	}

	public boolean IsVisible() {
		return mainFrame.isVisible();
	}

	public Message GetMessage() {
		String[] receivers = receiverText.getText().split(";");
		String message = messageText.getText();
		if (!timeshiftBox.isSelected()) {
			return new Message(receivers, message);
		} else {
			SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			Date date;
			try {
				date = format.parse(dateTextField.getText() + " " + timeComboBox.getSelectedItem());
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}

			return new Message(receivers, message, date);
		}
	}

	public void Close() {
		mainFrame.dispose();
	}

	private Vector<String> getTimeList() {
		Vector<String> uhrzeit_liste = new Vector<String>();

		for (int h = 0; h < 24; h++) {
			for (int min = 0; min < 60; min = min + 5) {
				String stunde = Integer.toString(h);
				if (stunde.length() == 1) {
					stunde = "0" + stunde;
				}
				String minuten = Integer.toString(min);
				if (minuten.length() == 1) {
					minuten = "0" + minuten;
				}
				uhrzeit_String = stunde + ":" + minuten;
				uhrzeit_liste.add(uhrzeit_String);
			}
		}
		return uhrzeit_liste;
	}
}