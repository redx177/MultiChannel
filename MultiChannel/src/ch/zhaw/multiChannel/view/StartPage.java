/*
 * Class StartPage
 * 
 * Version: 1.0
 *
 * 11.06.2013
 * 
 * This Class will create our GUI.
 *
 * Copyright ZHAW 2013
 */

package ch.zhaw.multiChannel.view;

import ch.zhaw.multiChannel.controller.Controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class StartPage {

	private Controller controller;
	private JFrame frame;

	public StartPage(Controller controller) {
		
		this.controller = controller;
	}

	/*Our Frame will be created here*/
	public void Show() {

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		JLabel label = new JLabel("Nachricht Erfassen");

		contentPane.add(BorderLayout.NORTH, label);
		contentPane.add(BorderLayout.CENTER, getInputPanel());

		frame.setTitle("MultiChannel");
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
	
	/*Here our Buttons will be created and add in our Frame*/
	private JPanel getInputPanel() {

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2, 1, 1));
		JButton sms = new JButton("SMS");
		sms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.composeSms();
			}
		});

		JButton mms = new JButton("MMS");
		mms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.composeMms();
			}
		});

		JButton email = new JButton("Email");
		email.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.composeEmail();
			}
		});

		JButton fax = new JButton("Fax");
		fax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.composeFax();
			}
		});

		panel.add(sms);
		panel.add(mms);
		panel.add(email);
		panel.add(fax);

		return panel;
	}

	public void ShowOnlyOnePageMessage() {
		JOptionPane.showMessageDialog(frame, "Es kann nur eine Seite offen sein. " +
				"Bitte schliessen Sie die andere bevor sie eine weitere öffnen.");
	}
}

