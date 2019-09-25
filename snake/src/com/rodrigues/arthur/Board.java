package com.rodrigues.arthur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {

    private Apple apple;
    private Snake snake;
    private boolean inGame;

    private JanelaPrincipal referencia;

    public Board() {
        inGame = true;
    }

    private void checkCollision() {

        for (int z = snake.getDots();   z > 0; z--) {

            if ((z > 4) && (snake.x[0] == snake.x[z]) && (snake.y[0] == snake.y[z])) {
                inGame = false;
            }
        }
        if (snake.y[0] >= referencia.getB_HEIGHT()) {
            inGame = false;
        }

        if (snake.y[0] < 0) {
            inGame = false;
        }

        if (snake.x[0] >= referencia.getB_HEIGHT()) {
            inGame = false;
        }

        if (snake.x[0] < 0) {
            inGame = false;
        }

        if(!inGame) {
            referencia.turnOffTimer();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame) {
            apple.checkIfSnakeGetApple();
            this.checkCollision();
            snake.move();
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        g.drawImage(apple.getAppleImage(), apple.getApple_x(),  apple.getApple_y(), this);

        for  (int z = 0; z < snake.getDots(); z++) {

            if (z == 0) {
                g.drawImage(snake.getHead(), snake.x[z], snake.y[z], this);
            } else {
                g.drawImage(snake.getBall(), snake.x[z], snake.y[z], this);
            }
        }

        Toolkit.getDefaultToolkit().sync();

    }


}
