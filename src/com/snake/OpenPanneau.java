package com.snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.swing.JPanel;

import javax.imageio.ImageIO;

public class OpenPanneau extends JPanel {

	public void paintComponent(Graphics g) {
		try {
			Image img= ImageIO.read(new File("C:\\Users\\Public\\Pojets\\JAVA\\Image\\A_logo.jpg"));
			g.drawImage(img, 0, -50, this.getWidth(), this.getHeight()+50, this);
			
			Font font=new Font("Courier", Font.PLAIN, 14);
			g.setColor(Color.darkGray);
			g.setFont(font);
			g.drawString("initialistion...", (this.getWidth()/2)-50, this.getHeight()-10);
			
			Font font2=new Font("Didot", Font.BOLD, 20);
			g.setColor(Color.gray);
			g.setFont(font2);
			g.drawString("Aoukou Games", (this.getWidth()/3), this.getHeight()-50);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
