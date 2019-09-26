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
    private final int DOT_SIZE  = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 24;
    private final int  DELAY = 140;

    private final int x[]  =  new int[ALL_DOTS];
    private final int y[]  =  new int[ALL_DOTS];

    private int dots;
    private int apple_x;
    private int apple_y;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame;

    private Timer timer;
    private Image ball;
    private Apple apple;
    private Snake snake;
    private Image head;

    public Board() {
        dots = 0;
        inGame = false;
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);
        loadImages();
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setVisible(true);
    }

    private void loadImages()  {
        ImageIcon iid = new ImageIcon(getClass().getResource("/res/dot.png"));
        ball  = iid.getImage();

        apple =  new Apple();
        snake = new Snake();

        ImageIcon iih = new ImageIcon(getClass().getResource("/res/head.png"));
        head  = iih.getImage();
    }

    public void initGame() {
        inGame = true;
        dots = 3;

        for(int z = 0; z < dots; z++) {
            x[z] = 150 - z * 10;
            y[z] = 150;
        }

        apple.locateApple();

        //  we use a timer on a timer to call action perfomed  method fixed delay
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        if (inGame) {
            g.drawImage(apple.getAppleImage(), apple.getApple_x(),  apple.getApple_y(), this);

            for  (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(snake.getHead(), x[z], y[z], this);
                } else {
                    g.drawImage(snake.getBall(), x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();
        } else if (dots > 0){
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica",  Font.BOLD, 14);
        FontMetrics metr  = getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    private void checkApple() {
        if ((x[0] == apple.getApple_x()) && (y[0]  == apple.getApple_y())) {
            dots++;
            apple.locateApple();
        }
    }

    private void move()  {
        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }
        if (rightDirection) {
            x[0] += DOT_SIZE;
        }
        if (upDirection) {
            y[0] -= DOT_SIZE;
        }
        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    private void checkCollision() {

        for (int z = dots;   z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT) {
            inGame = false;
        }

        if (y[0] < 8) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }

        if(!inGame) {
            timer.stop();
        }
    }

    private void locateApple()  {
        int r = (int) (Math.random() * RAND_POS);
        apple_x = r * DOT_SIZE;

        r = (int) (Math.random() * RAND_POS);
        apple_y = r *  DOT_SIZE;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollision();
            move();
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
            }
            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection))  {
                rightDirection =  true;
                upDirection = false;
                downDirection = false;
            }
            if ((key == KeyEvent.VK_UP) && (!downDirection))  {
                upDirection =  true;
                rightDirection = false;
                leftDirection = false;
            }
            if ((key == KeyEvent.VK_DOWN) && (!upDirection))  {
                downDirection =  true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }
}
