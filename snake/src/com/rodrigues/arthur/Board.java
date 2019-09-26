package com.rodrigues.arthur;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Board extends JPanel implements ActionListener {

    private final int B_WIDTH  =  300;
    private final int B_HEIGHT = 250;
    private final int  DELAY = 140;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame;
    private boolean pause;

    private Timer timer;
    private Apple apple;
    private Snake snake;

    public Board() {
        inGame = false;
        initBoard();
    }

    /**
     * Este método configura e inicia o tabuleiro
     */
    private void initBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);
        loadAssets();
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setVisible(true);
    }

    /**
     * Este método inicializa a cobra e a maçã
     */
    private void loadAssets()  {
        apple =  new Apple();
        snake = new Snake();
    }

    /**
     * Este método pausa e despause o jogo
     */
    public void pauseResumeGame() {
        if (pause) {
            pause = false;
        } else {
            pause = true;
        }

    }

    /**
     * Este método inicia o jogo
     */
    public void initGame() {
        if (!inGame) {
            inGame = true;
            pause = false;
            snake.setDots(3);

            snake.setLocationDots();

            apple.locateApple();

            //  we use a timer on a timer to call action perfomed  method fixed delay
            timer = new Timer(DELAY, this);
            timer.start();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    /**
     * @param g
     * Este método gerencia o que sera renderizado em tela alternando entre tela de entrada,
     * o jogo em si e a tela de fim de jogo
     *
     */
    private void doDrawing(Graphics g) {
        if (inGame) {
            g.drawImage(apple.getAppleImage(), apple.getApple_x(),  apple.getApple_y(), this);
            snake.draw(g, this);

            Toolkit.getDefaultToolkit().sync();
        } else if (snake.getDots() > 0){
            gameOver(g);
        } else {
            initialScreen(g);
        }
    }

    /**
     * @param g
     *
     * Este método renderiza a tela de fim de jogo
     */
    private void gameOver(Graphics g) {
        String msg = "Game Over";
        String score = "Score: " + (snake.getDots() - 3);
        Font small = new Font("Helvetica",  Font.BOLD, 14);
        FontMetrics metr  = getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 3);
        g.drawString(score, (B_WIDTH - metr.stringWidth(score)) / 2, B_HEIGHT / 2);
    }

    /**
     * @param g
     *
     * Este método renderiza a tela de entrada do jogo
     */
    private void initialScreen(Graphics g) {
        String title = "Snake!";
        Font titleFont = new Font("Helvetica",  Font.BOLD, 16);
        FontMetrics metr  = getFontMetrics(titleFont);
        g.setColor(Color.white);
        g.setFont(titleFont);
        g.drawString(title, (B_WIDTH - metr.stringWidth(title)) / 2, B_HEIGHT / 2);
    }

    /**
     * Este método verifica se a colisão entre a cobra e a maçã
     */
    private void checkApple() {
        if ((snake.getHeadPositionX() == apple.getApple_x()) && (snake.getHeadPositionY()  == apple.getApple_y())) {
            snake.incrementDots();
            apple.locateApple();
        }
    }

    private void checkCollision() {

        inGame = snake.checkCollision(B_WIDTH, B_HEIGHT);

        if(!inGame) {
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame && !pause) {
            checkApple();
            checkCollision();
            snake.move();
        }

        repaint();
    }

    public boolean isLeftDirection() {
        return leftDirection;
    }

    public void setLeftDirection(boolean leftDirection) {
        this.leftDirection = leftDirection;
    }

    public boolean isRightDirection() {
        return rightDirection;
    }

    public void setRightDirection(boolean rightDirection) {
        this.rightDirection = rightDirection;
    }

    public boolean isUpDirection() {
        return upDirection;
    }

    public void setUpDirection(boolean upDirection) {
        this.upDirection = upDirection;
    }

    public boolean isDownDirection() {
        return downDirection;
    }

    public void setDownDirection(boolean downDirection) {
        this.downDirection = downDirection;
    }

    private class  TAdapter extends KeyAdapter {


        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection))  {
                leftDirection =  true;
                upDirection = false;
                downDirection = false;
                snake.setLeftDirection(true);
                snake.setUpDirection(false);
                snake.setDownDirection(false);
            }
            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection))  {
                rightDirection =  true;
                upDirection = false;
                downDirection = false;
                snake.setRightDirection(true);
                snake.setUpDirection(false);
                snake.setDownDirection(false);
            }
            if ((key == KeyEvent.VK_UP) && (!downDirection))  {
                upDirection =  true;
                rightDirection = false;
                leftDirection = false;
                snake.setUpDirection(true);
                snake.setRightDirection(false);
                snake.setLeftDirection(false);
            }
            if ((key == KeyEvent.VK_DOWN) && (!upDirection))  {
                downDirection =  true;
                rightDirection = false;
                leftDirection = false;
                snake.setDownDirection(true);
                snake.setRightDirection(false);
                snake.setLeftDirection(false);
            }
        }
    }
}
