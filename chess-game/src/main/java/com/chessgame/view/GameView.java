package com.chessgame.view;

import com.chessgame.controller.GameController;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 600;
    private static final int BOARD_SIZE = 600;

    private ChessBoardView chessBoardView;
    private GameStatusView gameStatusView;
    private GameController gameController;

    public GameView(GameController controller) {
        this.gameController = controller;
        setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        // Initialise the chess board
        chessBoardView = new ChessBoardView(controller);

        // Chess Board container
        JPanel boardContainer = new JPanel();
        boardContainer.setPreferredSize(new Dimension(BOARD_SIZE, BOARD_SIZE));
        boardContainer.setLayout(new BorderLayout());
        boardContainer.add(chessBoardView, BorderLayout.CENTER);
        add(boardContainer, BorderLayout.WEST);

        // Initialise the game status
        gameStatusView = new GameStatusView(controller);

        JPanel statusContainer = new JPanel();
        statusContainer.setPreferredSize(new Dimension(FRAME_WIDTH - BOARD_SIZE, FRAME_HEIGHT));
        statusContainer.add(gameStatusView);
        add(statusContainer, BorderLayout.EAST);


        // New game button
        


        // Game frame size
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        pack();
        setLocationRelativeTo(null);
    }

    public ChessBoardView getChessBoardView() {
        return chessBoardView;
    }

    public GameStatusView getGameStatusView() {return  gameStatusView;}

}