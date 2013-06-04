package ch.zhaw.multiChannel.view;

import ch.zhaw.multiChannel.controller.Controller;
import com.sun.deploy.panel.JavaPanel;
import sun.awt.OrientableFlowLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class AttachmentMessagePage extends MessagePage {

	private JPanel uploadPanel = new JPanel();
	private JPanel uploadedPanel = new JPanel();
	private ArrayList<File> selectedFiles = new ArrayList<File>();

	public AttachmentMessagePage(Controller controller) {
		super(controller);
	}

	public void Show(String title) {
		super.Show(title);
	}

	protected void loadSendTimePanel(JPanel panel) {
		JPanel sendTimePanel = new JPanel();
		sendTimePanel.setLayout(new BoxLayout(sendTimePanel, BoxLayout.X_AXIS));
		sendTimePanel.add(timeshiftBox);
		sendTimePanel.add(dateTextField);
		sendTimePanel.add(timeComboBox);
		sendTimePanel.add(sendButton);

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
		uploadFormPanel.add(new JLabel("(keine .exe - max 1mb)"));
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

		// Removing old panel.
		uploadPanel.remove(uploadedPanel);

		// Creating new panel.
		uploadedPanel = new JPanel();
		uploadedPanel.setLayout(new GridLayout(selectedFiles.size(),1));
		uploadPanel.add(uploadedPanel);

		// Populating new panel.
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
				private ActionListener init(File file){
					this.file = file;
					return this;
				}
			}.init(file));
		}

		mainFrame.validate();
		mainFrame.repaint();
	}
}
