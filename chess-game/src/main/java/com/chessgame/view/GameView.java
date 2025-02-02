package com.chessgame.view;

import com.chessgame.controller.GameController;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 600;
    private static final int BOARD_SIZE = 600;

    private ChessBoardView chessBoardView;
    private GameController gameController;

    public GameView(GameController controller) {
        this.gameController = controller;
        setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        // Initialize the chess board
        chessBoardView = new ChessBoardView(controller);

        // Chess Board container
        JPanel boardContainer = new JPanel();
        boardContainer.setPreferredSize(new Dimension(BOARD_SIZE, BOARD_SIZE));
        boardContainer.setLayout(new BorderLayout());
        boardContainer.add(chessBoardView, BorderLayout.CENTER);
        add(boardContainer, BorderLayout.WEST);

        // TODO: This space is for the game status view
        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(FRAME_WIDTH - BOARD_SIZE, FRAME_HEIGHT));
        add(rightPanel, BorderLayout.EAST);


        // Game frame size
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        pack();
        setLocationRelativeTo(null);
    }

    public ChessBoardView getChessBoardView() {
        return chessBoardView;
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            GameView gameView = new GameView();
//            gameView.setVisible(true);
//        });
//    }
}