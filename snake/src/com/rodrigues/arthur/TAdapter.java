package com.rodrigues.arthur;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TAdapter  extends KeyAdapter {

    private Snake snake;

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if ((key == KeyEvent.VK_LEFT) && (!snake.isRightDirection()))  {
            snake.setLeftDirection(true);
            snake.setUpDirection(false);
            snake.setDownDirection(false);
        }
        if ((key == KeyEvent.VK_RIGHT) && (!snake.isLeftDirection()))  {
            snake.setRightDirection(true);
            snake.setUpDirection(false);
            snake.setDownDirection(false);
        }
        if ((key == KeyEvent.VK_UP) && (!snake.isDownDirection()))  {
            snake.setUpDirection(true);
            snake.setRightDirection(false);
            snake.setLeftDirection(false);
        }
        if ((key == KeyEvent.VK_DOWN) && (!snake.isUpDirection()))  {
            snake.setDownDirection(true);
            snake.setRightDirection(false);
            snake.setLeftDirection(false);
        }
    }
}
