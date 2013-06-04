package ch.zhaw.multiChannel.view;

import sun.java2d.loops.CustomComponent;

import javax.swing.*;
import java.awt.*;

public class MinimumSizedFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public MinimumSizedFrame() {
		setTitle("Custom Component Test");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void display() {
		add(new MinimumSizedFrame(), BorderLayout.NORTH);
		add(new MinimumSizedFrame(), BorderLayout.CENTER);
		add(new MinimumSizedFrame(), BorderLayout.SOUTH);
		add(new MinimumSizedFrame(), BorderLayout.EAST);
		pack();

		// enforces the minimum size of both frame and component
		setMinimumSize(getMinimumSize());
		setPreferredSize(getPreferredSize());
		setVisible(true);
	}

	public static void main(String[] args) {
		MinimumSizedFrame main = new MinimumSizedFrame();
		main.display();
	}
}
