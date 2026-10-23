package com.snake;

import javax.swing.JFrame;

public class OpenFenetre extends JFrame {

	public OpenFenetre() {
		this.setTitle("initialisation");
		this.setSize(500,300);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		
		this.setContentPane(new OpenPanneau());
		
		this.setVisible(true);
	}
}
