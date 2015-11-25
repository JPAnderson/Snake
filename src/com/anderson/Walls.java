package com.anderson;


import java.util.Random;

public class Walls {
    /*
        class for creating randomized walls. At the start of a game, 5 single block walls
        are created in random positions on the grid. These remain if the player replays the game,
        but NOT if the player quits and returns.

        Inspired by the Kibble Object (written by Clara) with some changes in the Snake class to actually make them walls.
     */

    private int wallX; //Wall's X position
    private int wallY;

    public Walls(Snake snake){
        createWalls(snake);
    }

    protected void createWalls(Snake s){ //Like Kibble, it gets a random position that the snake ISN'T in and creates walls there.
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
