package com.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class 	SnakeGame extends JPanel implements ActionListener, KeyListener{
	
	public class Tile{
		int x;
		int y;
		
		Tile(int pX, int pY){
			this.x=pX;
			this.y=pY;
		}
	}
	
	private int borderWidth;
	private int borderHeiht;
	private int tileSize=20;
	private Tile snakeHead;
	private Tile food;
	private Random random;
	private Timer gameLoop;
	private int velocityX=0;
	private int velocityY=0;
	private int vitesse=100;
	private boolean gameOver=false;
	private ArrayList <Tile> body= new ArrayList<Tile>();
	

	public SnakeGame(int pborderWidth, int pborderHeight) {
		this.borderWidth = pborderWidth;
		this.borderHeiht = pborderHeight;
		
		setPreferredSize(new Dimension( this.borderWidth,this.borderHeiht));
		setBackground(Color.BLACK);
		addKeyListener(this);
		setFocusable(true);
		
		snakeHead = new Tile(5,5);
		food= new Tile(10,10);
		random=new Random();
		placeFood();
		gameLoop = new Timer(vitesse,this);
		gameLoop.start();
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		
//		for(int i=0;i<borderWidth/tileSize;i++) {
//			g.drawLine(i*tileSize, 0, i*tileSize, borderHeiht);
//			g.drawLine(0,i*tileSize, borderWidth, i*tileSize);
//			
//		}
		
		for (int i=0; i<body.size();i++) {
			Tile snakePart=body.get(i);
			if(gameOver) {
				g.setColor(Color.red);

			}else {
				
				g.setColor(Color.green);
			}
			g.fillRect(snakePart.x*tileSize, snakePart.y * tileSize, tileSize, tileSize);
		}
		
		g.setColor(Color.red);
		g.fillRect(food.x*tileSize, food.y*tileSize, tileSize, tileSize);
		
		if(gameOver) {
			g.setColor(Color.red);

		}else {
			
			g.setColor(Color.orange);
		}
		g.fillRect(snakeHead.x*tileSize, snakeHead.y*tileSize, tileSize, tileSize);
		
		g.setFont(new Font( "Arial",Font.PLAIN,20));
		
		if(gameOver) {
			g.setColor(Color.red);
			g.drawString("gameOver : "+String.valueOf(body.size()), tileSize, tileSize);
		}else {
			g.setColor(Color.white);
			g.drawString("Score : "+String.valueOf(body.size()), tileSize, tileSize);
		}
	}
	
	public void placeFood() {
		food.x=random.nextInt(borderWidth/tileSize);
		food.y=random.nextInt(borderHeiht/tileSize);
	}
	
	public void move() {
		
		if (collision(snakeHead, food)) {
			body.add(new Tile(food.x, food.y));
			placeFood();
		}
		
		for(int i=body.size()-1;i>=0;i--) {
			Tile snakePart=body.get(i);
			
			if(i==0) {
				snakePart.x=snakeHead.x;
				snakePart.y=snakeHead.y;
				
			}else {
				snakePart.x=body.get(i-1).x;
				snakePart.y=body.get(i-1).y;
				
			}
		}
		snakeHead.x += velocityX;
		snakeHead.y += velocityY;
		
		for(int i=0;i<body.size();i++) {
			if(collision(snakeHead,body.get(i))) {
				gameOver=true;
			}
		}
		
		if(snakeHead.x==borderWidth/tileSize) {
			snakeHead.x=0;
		}
		if(snakeHead.y==borderHeiht/tileSize) {
			snakeHead.y=0;
		}
		if(snakeHead.x<0) {
			snakeHead.x=borderWidth/tileSize;
		}
		if(snakeHead.y<0) {
			snakeHead.y=borderHeiht/tileSize;
		}
	}
	
	public boolean collision(Tile pT1, Tile pT2) {
		
		return pT1.x==pT2.x && pT1.y == pT2.y;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		move();
		repaint();
		if(gameOver) {
			gameLoop.stop();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_UP && velocityY!=1) {
			velocityX=0;
			velocityY=-1;
			
		}else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY!=-1) {
			
			velocityX=0;
			velocityY=1;
			
		}else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX!=-1) {
			
			velocityX=1;
			velocityY=0;
			
		}else if (e.getKeyCode() == KeyEvent.VK_LEFT && velocityX!=1) {
			
			velocityX=-1;
			velocityY=0;
			
		}
		if(gameOver){
			body.removeAll(body);
			gameOver= false;
			gameLoop.start();
		}
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
