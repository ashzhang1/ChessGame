package com.chessgame.main;

import com.chessgame.controller.GameController;
import com.chessgame.view.ChessBoardView;
import com.chessgame.view.GameView;

import javax.swing.*;

public class ChessGame {
    private GameController gameController;
    private GameView gameView;

    public ChessGame() {
        this.gameController = new GameController();
        this.gameView = new GameView(this.gameController);
        this.gameController.registerObserver(gameView.getChessBoardView());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChessGame game = new ChessGame();
            game.gameView.setVisible(true);
            game.gameController.startNewGame();

            // Update board view with initial piece positions
            game.gameView.getChessBoardView().updateBoard(game.gameController.getBoard());
        });
    }
}
