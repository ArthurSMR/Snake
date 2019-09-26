package com.rodrigues.arthur;

import javax.swing.*;
import java.awt.*;

public class Apple {

    private int apple_x;
    private int apple_y;
    private final int RAND_POS = 24;

    private Image appleImage;

    private Snake snake = new Snake();

    public Apple() {
        loadImage();
    }

    /**
     * Este método carrega a imagem da maçã
     */
    public void loadImage() {
        ImageIcon iia = new ImageIcon(getClass().getResource("/res/apple.png"));
        appleImage  = iia.getImage();
    }

    /**
     * Este método gera uma nova posição aleatória para a maçã
     */
    public void locateApple() {

        int r = (int) (Math.random() * RAND_POS);
        apple_x = r * snake.getDOT_SIZE();

        r = (int) (Math.random() * RAND_POS);
        apple_y = r *  snake.getDOT_SIZE();

    }

    // GETTERs and SETTERs
    public int getApple_x() {
        return apple_x;
    }

    public void setApple_x(int apple_x) {
        this.apple_x = apple_x;
    }

    public int getApple_y() {
        return apple_y;
    }

    public void setApple_y(int apple_y) {
        this.apple_y = apple_y;
    }

    public Image getAppleImage() {
        return appleImage;
    }

    public void setAppleImage(Image appleImage) {
        this.appleImage = appleImage;
    }
}
