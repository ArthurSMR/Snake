package com.rodrigues.arthur;

import javax.swing.*;
import java.awt.*;

public class Snake {

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

    }

    public void loadHeadAndDotImages() {
        ImageIcon iid = new ImageIcon(getClass().getResource("/res/dot.png"));
        ball  = iid.getImage();

        ImageIcon iih = new ImageIcon(getClass().getResource("/res/head.png"));
        head  = iih.getImage();
    }

    /* Esse método fará com que as Dots fiquem organizadas em filas */
    public void setLocationDots() {

        for(int z = 0; z < dots; z++) {
            x[z] = 150 - z * 10;
            y[z] = 150;
        }
    }

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
}
