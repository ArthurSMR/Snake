package com.rodrigues.arthur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;

public class Snake implements ActionListener {

    private Image ball;
    private Image head;

    private int dots;
    private final int ALL_DOTS = 900;
    private int DOT_SIZE  = 10;

    //Location dots points
    public final int x[]  =  new int[ALL_DOTS];
    public final int y[]  =  new int[ALL_DOTS];

    //Directions
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;

    Snake() {
        loadHeadAndDotImages();
    }

    /**
     * Este método carrega as imagens do corpo e da cabeça da cobra
     */
    public void loadHeadAndDotImages() {
        ImageIcon iid = new ImageIcon(getClass().getResource("/res/dot.png"));
        ball  = iid.getImage();

        ImageIcon iih = new ImageIcon(getClass().getResource("/res/head.png"));
        head  = iih.getImage();
    }

    /**
     * Este método organiza os 'Dots' em filas
     */
    public void setLocationDots() {

        for(int z = 0; z < dots; z++) {
            x[z] = 150 - z * 10;
            y[z] = 150;
        }
    }

    /**
     * Este método faz o cálculo de movimentação da cobra
     */
    public void move() {
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

    /**
     * @param g
     * @param board
     *
     * Este método desenha a cobra na tela
     */
    public void draw(Graphics g, ImageObserver board) {
        for  (int z = 0; z < dots; z++) {
            if (z == 0) {
                g.drawImage(this.getHead(), x[z], y[z], board);
            } else {
                g.drawImage(this.getBall(), x[z], y[z], board);
            }
        }
    }

    /**
     * @param widthLimit
     * @param heightLimit
     * @return Boolean - responde se houve colisão
     *
     * Este método verifica a colisão
     */
    public boolean checkCollision(int widthLimit, int heightLimit) {

        for (int z = dots;   z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                return false;
            }
        }

        if (y[0] >= heightLimit) {
            return false;
        }

        if (y[0] < 0) {
            return false;
        }

        if (x[0] >= widthLimit) {
            return false;
        }

        if (x[0] < 0) {
            return false;
        }

        return true;
    }

    /**
     * Este método incrementa a quantidade de 'Dots' da cobra
     */
    public void incrementDots() { dots += 1; }

    public int getDots() {
        return dots;
    }

    public void setDots(int dots) {
        this.dots = dots;
    }

    public int getDOT_SIZE() {
        return DOT_SIZE;
    }

    public void setDOT_SIZE(int DOT_SIZE) {
        this.DOT_SIZE = DOT_SIZE;
    }

    public Image getBall() {
        return ball;
    }

    public void setBall(Image ball) {
        this.ball = ball;
    }

    public Image getHead() {
        return head;
    }

    public void setHead(Image head) {
        this.head = head;
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

    /**
     * @return int - posição X da cabeça da cobra
     *
     * Este método retorna a posição X da cabeça da cobra
     */
    public int getHeadPositionX() {
        return x[0];
    }

    /**
     * @return int - posição Y da cabeça da cobra
     *
     * Este método retorna a posição Y da cabeça da cobra
     */
    public int getHeadPositionY() {
        return y[0];
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
    }
}
