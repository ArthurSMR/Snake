package com.rodrigues.arthur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements ActionListener {

    private Apple apple;
    private Snake snake;
    private boolean inGame;

    private JanelaPrincipal referencia;

    public Board() {
        inGame = true;
    }

    public void initBoard() {
        addKeyListener(new TAdapter());
        System.out.println("Entrou 1");
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(referencia.getB_WIDTH(), referencia.getB_HEIGHT()));
        snake.loadHeadAndDotImages();
        apple.loadImage();
        iniciaJogo();
    }

    public void iniciaJogo() {

        snake.setDots(3);
        snake.setLocationDots();

        apple.locateApple();

        referencia.turnOnTimer();
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


}
