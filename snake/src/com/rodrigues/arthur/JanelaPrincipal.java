package com.rodrigues.arthur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaPrincipal extends JFrame implements ActionListener {

    //atributos de dimensões da janela
    private int B_WIDTH = 300;
    private int B_HEIGHT = 300;

    private Board board;


    private JMenuBar menuBar;
    private JMenu menuIniciar;
    private JMenuItem menuItemIniciar;
    private JMenuItem menuItemPauseResume;
    private JMenuItem menuItemHelp;



    JanelaPrincipal() throws HeadlessException {
        super();
        configuraJanela();
        criaAdicionaMenu();
        adicionaOuvinteMenus(this);
    }

    /**
     * Este método inicia o jogo chamando o método initGame() da classe Board
     */
    void inicia() {
        board.initGame();
    }

    /**
     * Este método cria e adiciona o menu e seus itens
     */
    private void criaAdicionaMenu() {
        menuIniciar = new JMenu("Game");

        menuItemIniciar = new JMenuItem("Start");
        menuItemPauseResume = new JMenuItem("Pause / Resume");
        menuItemHelp = new JMenuItem("Help");
        menuIniciar.add(menuItemIniciar);
        menuIniciar.add(menuItemPauseResume);
        menuIniciar.add(menuItemHelp);

        menuBar = new JMenuBar();
        menuBar.add(menuIniciar);

        this.setJMenuBar(menuBar);
        this.board = new Board();
        this.add(board);

    }

    /**
     * Este método configura o tamanho da janela, define o título e determina o método padrão de fechamento do JFrame.
     */
    private void configuraJanela() {

        // tamanho da janela
        this.setSize(B_WIDTH, B_HEIGHT);
        this.setResizable(false); //Não redimensionável

        //janela no centro do monitor
        this.setLocationRelativeTo(null);

        setTitle("Snake");
        setLocationRelativeTo(null);
        // impõe que programa TERMINE quando janela for fechada
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /**
     * @param ouvinte
     *
     * Este método adiciona um listener para escutar as ações do menu
     */
    void adicionaOuvinteMenus(ActionListener ouvinte) {
        for (Component menuPrincipal : this.getJMenuBar().getComponents()) {
            if (menuPrincipal  instanceof JMenu) {
                adicionaOuvinteItemMenu(ouvinte, (JMenu) menuPrincipal);
            }
        }
    }

    /**
     * @param ouvinte
     * @param menuPrincipal
     *
     * Este método adiciona um listener para escutar as ações dos itens do menu
     */
    private  void adicionaOuvinteItemMenu(ActionListener ouvinte, JMenu menuPrincipal) {
        for (Component alvo : menuPrincipal.getMenuComponents()) {
            if(alvo instanceof JMenuItem) {
                ((JMenuItem)alvo).addActionListener(ouvinte);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == menuItemIniciar) {
            this.inicia();
        } else if (e.getSource() == menuItemPauseResume) {
            board.pauseResumeGame();
        } else if (e.getSource() == menuItemHelp) {
            JOptionPane.showMessageDialog(null, "Use keyboard arrows to play the game.");
        }
    }
}

