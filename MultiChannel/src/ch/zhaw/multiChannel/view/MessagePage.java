package ch.zhaw.multiChannel.view;

import ch.zhaw.multiChannel.controller.Controller;
import ch.zhaw.multiChannel.model.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;


public class MessagePage implements Page {
	private Controller controller;
	String uhrzeit_String = new String();

	JLabel receiverLabel = new JLabel("Empfänger:");
	JLabel receiverInfoLabel = new JLabel("(separiert durch semikolon)");
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
	JPanel additionalPanel = new JPanel();

	MinimumSizedFrame mainFrame = new MinimumSizedFrame();

	public MessagePage(Controller controller) {
		this.controller = controller;
	}

	public void show(String title) {
		timeComboBox = new JComboBox(getTimeList());
		timeComboBox.setEditable(false);
		dateTextField.setText("sofort senden");
		dateTextField.setEditable(false);
		upperPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		upperPanel.add(receiverLabel);
		upperPanel.add(receiverText);
		upperPanel.add(receiverInfoLabel);

		middlePanel.setLayout(new BorderLayout());
		JScrollPane scrollMessageText = new JScrollPane(messageText);
		middlePanel.add(scrollMessageText, BorderLayout.CENTER);

		JPanel sendTimePanel = new JPanel();
		sendTimePanel.setLayout(new FlowLayout());
		loadSendTimePanel(lowerPanel);

		mainPanel.setSize(600, 700);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(BorderLayout.NORTH, upperPanel);
		mainPanel.add(BorderLayout.CENTER, middlePanel);
		mainPanel.add(BorderLayout.SOUTH, lowerPanel);

		mainFrame.getContentPane().add(mainPanel);
		mainFrame.setSize(600, 500);
		mainFrame.setMinimumSize(new Dimension(470, 200));
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
				controller.sendMessageRequest();
			}
		});
	}

	protected void loadSendTimePanel(JPanel panel) {
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(timeshiftBox);
		panel.add(dateTextField);
		dateTextField.setColumns(7);
		panel.add(timeComboBox);
		panel.add(sendButton);
	}

	public boolean isVisible() {
		return mainFrame.isVisible();
	}

	public Message getMessage() {
		String[] receivers = receiverText.getText().split(";");
		String message = messageText.getText();
		if (!timeshiftBox.isSelected()) {
			return new Message(receivers, message);
		} else {
			String date = dateTextField.getText();
			String time = timeComboBox.getSelectedItem().toString();
			SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyyHH:mm");
			try {
				return new Message(receivers, message, format.parse(dateTextField.getText() + timeComboBox.getSelectedItem()));
			} catch (ParseException e) {
				showError(String.format("Ungültiges Datum: %s %s", date, time));
				return null;
			}
		}
	}

	public void close() {
		mainFrame.dispose();
	}

	public void showError(String errorMessage) {
		JOptionPane.showMessageDialog(mainFrame, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public void showNotification(String errorMessage) {
		JOptionPane.showMessageDialog(mainFrame, errorMessage, "Danke", JOptionPane.INFORMATION_MESSAGE);
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