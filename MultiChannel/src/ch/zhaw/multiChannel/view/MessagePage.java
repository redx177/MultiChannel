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

	JLabel empfaenger_lbl = new JLabel("Empfänger:");
	JTextField empfaenger_txt = new JTextField(15);
	JTextArea nachricht_txt = new JTextArea(15, 40);
	JButton send_btn = new JButton("Nachricht senden");
	JCheckBox zeitversetzt_cbox = new JCheckBox("Zeit versetzt senden?");
	JTextField datum_txt = new JTextField(20);
	JComboBox uhrzeit_auswahl;


	JPanel hauptPanel = new JPanel();
	JPanel obererPanel = new JPanel();
	JPanel mittlererPanel = new JPanel();
	JPanel untererPanel = new JPanel();

	JFrame hauptFrame = new JFrame();

	public MessagePage(Controller controller) {
		this.controller = controller;
	}

	public void Show(String title) {
		uhrzeit_auswahl = new JComboBox(getUhrzeitListe());
		uhrzeit_auswahl.setEditable(false);
		datum_txt.setText("sofort senden");
		datum_txt.setEditable(false);
		obererPanel.setLayout(new FlowLayout());
		obererPanel.add(empfaenger_lbl);
		obererPanel.add(empfaenger_txt);

		mittlererPanel.setLayout(new FlowLayout());
		mittlererPanel.add(nachricht_txt);

		untererPanel.setLayout(new FlowLayout());
		untererPanel.add(zeitversetzt_cbox);
		untererPanel.add(datum_txt);
		untererPanel.add(uhrzeit_auswahl);
		untererPanel.add(send_btn);

		hauptPanel.setSize(600, 700);
		hauptPanel.setLayout(new BorderLayout());
		hauptPanel.add(BorderLayout.NORTH, obererPanel);
		hauptPanel.add(BorderLayout.WEST, mittlererPanel);
		hauptPanel.add(BorderLayout.SOUTH, untererPanel);

		hauptFrame.getContentPane().add(hauptPanel);
		hauptFrame.setSize(600, 500);
		hauptFrame.setVisible(true);
		hauptFrame.setTitle(title);
		hauptFrame.pack();

		hauptFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		zeitversetzt_cbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent datum) {
				if (zeitversetzt_cbox.isSelected()) {
					datum_txt.setText(new DatePicker(hauptFrame).setPickedDate());
					uhrzeit_auswahl.setEditable(true);
				} else {
					datum_txt.setText("");
					uhrzeit_auswahl.setEditable(false);
				}
			}
		});
		send_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.SendMessageRequest();
			}
		});
	}

	public boolean IsVisible() {
		return hauptFrame.isVisible();
	}

	public Message GetMessage() {
		String[] empfaenger = empfaenger_txt.getText().split(";");
		String nachricht = nachricht_txt.getText();
		if (!zeitversetzt_cbox.isSelected()) {
			return new Message(empfaenger, nachricht);
		} else {
			SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			Date date;
			try {
				date = format.parse(datum_txt.getText() + " " + uhrzeit_auswahl.getSelectedItem());
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}

			return new Message(empfaenger, nachricht, date);
		}
	}

	public void Close() {
		hauptFrame.dispose();
	}

	private Vector<String> getUhrzeitListe() {
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