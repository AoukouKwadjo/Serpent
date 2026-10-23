package com.snake;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Snake {

	public static void main(String[] args) {
		
		

		JFrame openingFrame =new OpenFenetre();


        // Créer un Timer pour attendre 5 secondes (5000 ms)
        Timer timer = new Timer(500, (ActionListener) new ActionListener(){
        	
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fermer le premier JFrame
                openingFrame.dispose();

                int borderWidth=600;
        		int borderHeight=borderWidth;
        		
        		JFrame frame= new JFrame("Snake");
        		
        		frame.setVisible(true);
        		frame.setSize(borderWidth,borderHeight);
        		frame.setResizable(true);
        		frame.setLocationRelativeTo(null);
        		
        		SnakeGame snakeGame=new SnakeGame(borderWidth, borderHeight);
        		frame.add(snakeGame);
        		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        		

        		frame.pack();
        		snakeGame.requestFocus();
            }

        });

        // Démarrer le Timer (ne s'exécute qu'une seule fois)
        timer.setRepeats(false);
        timer.start();
	}

}
