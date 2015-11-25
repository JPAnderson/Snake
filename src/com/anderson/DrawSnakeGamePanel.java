package com.anderson;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.TimerTask;

import javax.swing.*;

/** This class responsible for displaying the graphics, so the snake, grid, kibble, instruction text and high score
 *
 * @author Clara
 *
 */
public class DrawSnakeGamePanel extends JPanel {

	private static int gameStage = SnakeGame.BEFORE_GAME;  //use this to figure out what to paint

	private Snake snake;
	private Kibble kibble;
	private Score score;
	private Walls wall1;
	private Walls wall2;
	private Walls wall3;
	private Walls wall4;
	private Walls wall5;




	DrawSnakeGamePanel(Snake s, Kibble k, Score sc, Walls w, Walls w2, Walls w3, Walls w4, Walls w5){
		this.snake = s;
		this.kibble = k;
		this.score = sc;
		this.wall1 = w;
		this.wall2 = w2;
		this.wall3 = w3;
		this.wall4 = w4;
		this.wall5 = w5;
	}

	public Dimension getPreferredSize() {
		return new Dimension(SnakeGame.xPixelMaxDimension, SnakeGame.yPixelMaxDimension);
	}


	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

			/* Where are we at in the game? 4 phases..
			 * 1. Before game starts
			 * 2. During game
			 * 3. Game lost aka game over
			 * 4. or, game won
			 */

		gameStage = SnakeGame.getGameStage();

		switch (gameStage) {
			case 1: {
				displayInstructions(g);
				break;
			}
			case 2: {
				displayGame(g);
				break;
			}
			case 3: {
				displayGameOver(g);
				break;
			}
			case 4: {
				displayGameWon(g);
				break;
			}
		}
	}





	private void displayGameWon(Graphics g) {
		// TODO Replace this with something really special!
		g.clearRect(100,100,350,350);
		g.drawString("YOU WON SNAKE!!!", 150, 150);


	}
	private void displayGameOver(Graphics g) {

		g.clearRect(100,100,350,350);
		g.drawString("GAME OVER", 150, 150);

		String textScore = score.getStringScore();
		String textHighScore = score.getStringHighScore();
		String newHighScore = score.newHighScore();

		g.drawString("SCORE = " + textScore, 150, 250);

		g.drawString("HIGH SCORE = " + textHighScore, 150, 300);
		g.drawString(newHighScore, 150, 400);

		g.drawString("press a key to play again", 150, 350);
		g.drawString("Press q to quit the game",150,400);



	}

	private void displayGame(Graphics g) {
		displayGameGrid(g);
		displaySnake(g);
		displayKibble(g);
		displayWalls(g);

	}

	private void displayGameGrid(Graphics g) {

		int maxX = SnakeGame.xPixelMaxDimension;
		int maxY= SnakeGame.yPixelMaxDimension;
		int squareSize = SnakeGame.squareSize;
		SnakeGame.snakeFrame.setBackground(Color.BLUE);

		g.clearRect(0, 0, maxX, maxY);

		g.setColor(Color.WHITE);

		//Draw grid - horizontal lines
		for (int y=0; y <= maxY ; y+= squareSize){
			g.drawLine(0, y, maxX, y);
		}
		//Draw grid - vertical lines
		for (int x=0; x <= maxX ; x+= squareSize){
			g.drawLine(x, 0, x, maxY);

		}
	}

	private void displayKibble(Graphics g) {

		//Draw the kibble in green
		g.setColor(Color.RED);

		int x = kibble.getKibbleX() * SnakeGame.squareSize;
		int y = kibble.getKibbleY() * SnakeGame.squareSize;

		g.fillRect(x, y, SnakeGame.squareSize, SnakeGame.squareSize);
		//Removed (magic?)numbers here that originally increased padding to accommodate for the border.
		//With the grid removed, it an be drawn fine with no modification
		//Original code >> g.fillRect(x+1, y+1, SnakeGame.squareSize-1, SnakeGame.squareSize-1);

	}
	//https://blog.udemy.com/dql-queries/
	private void displayWalls(Graphics g){ //Method for displaying the wall objects.
		g.setColor(Color.BLACK);		   //If it looks familiar it's because it's code was heavily
		//influenced from the displayKibble method that Clara wrote.
		int x = wall1.getWallX() * SnakeGame.squareSize;
		int y = wall1.getWallY() * SnakeGame.squareSize;

		int x2 = wall2.getWallX() * SnakeGame.squareSize;
		int y2 = wall2.getWallY() * SnakeGame.squareSize;

		int x3 = wall3.getWallX() * SnakeGame.squareSize;
		int y3 = wall3.getWallY() * SnakeGame.squareSize;

		int x4 = wall4.getWallX() * SnakeGame.squareSize;
		int y4 = wall4.getWallY() * SnakeGame.squareSize;

		int x5 = wall5.getWallX() * SnakeGame.squareSize;
		int y5 = wall5.getWallY() * SnakeGame.squareSize;
		g.fillRect(x, y, SnakeGame.squareSize , SnakeGame.squareSize);
		g.fillRect(x2, y2, SnakeGame.squareSize , SnakeGame.squareSize);
		g.fillRect(x3, y3, SnakeGame.squareSize, SnakeGame.squareSize);
		g.fillRect(x4, y4, SnakeGame.squareSize, SnakeGame.squareSize);
		g.fillRect(x5, y5, SnakeGame.squareSize, SnakeGame.squareSize);
	}

	private void displaySnake(Graphics g) {

		LinkedList<Point> coordinates = snake.segmentsToDraw();

		//Draw head in grey
		g.setColor(Color.BLACK); //Changed snake head to be drawn in black to enhance minimalist look.
		//As the snake is always moving forward, it should be relatively
		//intuitive to see where the head is.
		Point head = coordinates.pop();
		g.fillRect((int)head.getX(), (int)head.getY(), SnakeGame.squareSize, SnakeGame.squareSize);


		g.setColor(Color.BLACK);
		for (Point p : coordinates) {
			g.fillRect((int)p.getX(), (int)p.getY(), SnakeGame.squareSize, SnakeGame.squareSize);
		}

	}

	public void displayInstructions(Graphics g) {
		//Slightly freshened the look of the intro page.
		g.drawRect(128, 100, 250,150);  //decorative rectangles
		g.drawRect(122, 95, 260, 162);
		g.drawString("Welcome to Snake", 200, 130); //Centered instructions
		g.drawString("Press any key to start", 195, 150);
		g.drawString("Press 'q' to quit", 210, 170);







	}


}

