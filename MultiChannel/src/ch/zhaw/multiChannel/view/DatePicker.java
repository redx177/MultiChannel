/*
 * Class DatePicker
 * 
 * Version: 1.0
 *
 * 11.06.2013
 * 
 * This Class will show a Calendar to the user. 
 *
 * Copyright ZHAW 2013
 */

package ch.zhaw.multiChannel.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

class DatePicker {
	
	int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
	int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
	String day = "";
	JLabel l = new JLabel("", JLabel.CENTER);
	JDialog d;
	JButton[] button = new JButton[49];

	public DatePicker(JFrame parent) {

		d = new JDialog();
		d.setModal(true);
		String[] header = { "So", "Mo", "Di", "Mi", "Do", "Fr", "Sa" };
		JPanel p1 = new JPanel(new GridLayout(7, 7));
		p1.setPreferredSize(new Dimension(430, 120));

		for (int x = 0; x < button.length; x++) {
		
			final int selection = x;
			button[x] = new JButton();
			button[x].setFocusPainted(false);
			button[x].setBackground(Color.white);
			
			if (x > 6)
			
				button[x].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						
						day = button[selection].getActionCommand();
						d.dispose();
					}
				});
			if (x < 7) {
				
				button[x].setText(header[x]);
				button[x].setForeground(Color.red);
			}
			p1.add(button[x]);
		}
		
		JPanel p2 = new JPanel(new GridLayout(1, 3));
		JButton previous = new JButton("<<");
		previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
		
				month--;
				displayDate();
			}
		});
		
		p2.add(previous);
		p2.add(l);
		JButton next = new JButton(">>");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
		
				month++;
				displayDate();
			}
		});
		
		p2.add(next);
		d.add(p1, BorderLayout.CENTER);
		d.add(p2, BorderLayout.SOUTH);
		d.pack();
		d.setLocationRelativeTo(parent);
		displayDate();
		d.setVisible(true);
	}

	public void displayDate() {
		
		for (int x = 7; x < button.length; x++)
		
			button[x].setText("");
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"MMMM yyyy");
			java.util.Calendar cal = java.util.Calendar.getInstance();
			cal.set(year, month, 1);
			
			int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
			int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
			
			for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++)
			
				button[x].setText("" + day);
				l.setText(sdf.format(cal.getTime()));
				d.setTitle("Date Picker");
	}

	public String setPickedDate() {
		
		if (day.equals(""))
		
			return day;
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"dd.MM.yyyy");
			java.util.Calendar cal = java.util.Calendar.getInstance();
			cal.set(year, month, Integer.parseInt(day));
			return sdf.format(cal.getTime());
	}
}

