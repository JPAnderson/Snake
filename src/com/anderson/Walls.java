package com.anderson;


import java.util.Random;

public class Walls {

    private int wallX;
    private int wallY;

    public Walls(Snake snake){
        createWalls(snake);
    }

    protected void createWalls(Snake s){
        Random rng = new Random();
        boolean wallInSnake = true;
        while(wallInSnake) {
            wallX = rng.nextInt(SnakeGame.xSquares);
            wallY = rng.nextInt(SnakeGame.ySquares);
            wallInSnake = s.isSnakeSegment(wallX, wallY);
        }
    }


    public int getWallX() {
        return wallX;
    }

    public int getWallY() {
        return wallY;
    }
}
