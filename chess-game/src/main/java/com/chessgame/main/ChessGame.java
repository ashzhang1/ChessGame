package com.chessgame.main;

import com.chessgame.controller.GameController;
import com.chessgame.view.GameView;

import javax.swing.*;

public class ChessGame {
    private GameController gameController;
    private GameView gameView;

    public ChessGame() {
        this.gameController = new GameController();
        this.gameView = new GameView(this.gameController);
        this.gameController.registerBoardViewObserver(gameView.getChessBoardView());
        this.gameController.registerBoardStatusViewObserver(gameView.getGameStatusView());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChessGame game = new ChessGame();
            game.gameView.setVisible(true);
            game.gameController.startNewGame();

            // Set up board view with initial piece positions
            game.gameView.getChessBoardView().updateBoard(game.gameController.getBoard());
        });
    }
}
