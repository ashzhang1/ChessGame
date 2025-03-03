package com.chessgame.view;

import com.chessgame.commands.CommandHandler;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 600;
    private static final int BOARD_SIZE = 600;

    private ChessBoardView chessBoardView;
    private GameStatusView gameStatusView;
    private CommandHandler commandHandler;

    public GameView(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
        setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        // Initialise the chess board
        chessBoardView = new ChessBoardView(commandHandler);

        // Chess board container
        JPanel boardContainer = new JPanel();
        boardContainer.setPreferredSize(new Dimension(BOARD_SIZE, BOARD_SIZE));
        boardContainer.setLayout(new BorderLayout());
        boardContainer.add(chessBoardView, BorderLayout.CENTER);
        add(boardContainer, BorderLayout.WEST);

        // Game status container
        JPanel statusContainer = new JPanel();
        statusContainer.setLayout(new BoxLayout(statusContainer, BoxLayout.Y_AXIS));
        statusContainer.setPreferredSize(new Dimension(FRAME_WIDTH - BOARD_SIZE, FRAME_HEIGHT));

        // Game status view on top
        gameStatusView = new GameStatusView();
        JPanel statusWrapper = new JPanel();
        statusWrapper.add(gameStatusView);
        statusContainer.add(statusWrapper);

        // This pushes components apart
        statusContainer.add(Box.createVerticalGlue());

        // Start new game button
        JPanel buttonWrapper = new JPanel();
        JButton newGameButton = new JButton("Start New Game");
        newGameButton.setFont(new Font("Arial", Font.BOLD, 20));
        newGameButton.setPreferredSize(new Dimension(200, 52));
        buttonWrapper.add(newGameButton);

        // Add button and bottom padding
        statusContainer.add(buttonWrapper);
        statusContainer.add(Box.createVerticalStrut(30));

        // Add the status container to the main frame
        add(statusContainer, BorderLayout.EAST);

        // Start new game button action
        newGameButton.addActionListener(e -> {
            this.commandHandler.handleNewGame();
        });

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