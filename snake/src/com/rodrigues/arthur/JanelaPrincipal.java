package com.rodrigues.arthur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaPrincipal extends JFrame implements ActionListener {

    //atributos de dimensões da janela
    private int B_WIDTH = 300;
    private int B_HEIGHT = 300;

    private JPanel painelStatus;
    private JLabel labelStatus;
    private String titulo;

    private Snake snake;
    private Apple apple;

    private Timer timer;
    private final int  DELAY = 140;

    JanelaPrincipal() throws HeadlessException {
        super();
        configuraJanela();
        inicializaAdicionaComponentes();
    }

    void inicia() {
        this.labelStatus.setText("Snake Game");
        this.setVisible(true);
    }

    private void configuraJanela() {

        // tamanho da janela
        this.setSize(B_WIDTH, B_HEIGHT);
        this.setResizable(false); //Não redimensionável

        //janela no centro do monitor
        this.setLocationRelativeTo(null);

        // impõe que programa TERMINE quando janela for fechada
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void inicializaAdicionaComponentes() {

        // area para 'barras de status'
        this.painelStatus = new JPanel();

        // texto inicial da  barra
        this.labelStatus  = new JLabel(this.titulo);

        //importante: adiciona o componente de texto ao painel!
        this.painelStatus.add(labelStatus);

        //perfumaria em geral
        this.painelStatus.setBackground(Color.gray);
        this.painelStatus.setBorder(BorderFactory.createEtchedBorder());

        // IMPORTANTE: adiciona o painel a janela!
        this.add(painelStatus, BorderLayout.SOUTH);
    }

    public void iniciaJogo() {

        snake.setDots(3);
        snake.setLocationDots();

        apple.locateApple();

        this.turnOnTimer();

    }

    public void turnOnTimer() {

        //  we use a timer on a timer to call action perfomed  method fixed delay
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void turnOffTimer() {
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public int getB_WIDTH() {
        return B_WIDTH;
    }

    public int getB_HEIGHT() {
        return B_HEIGHT;
    }
}

