package com.chessgame.controller;

import com.chessgame.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameController {
    private boolean isWhiteTurn;
    private List<Move> moveHistory;
    private Board gameBoard;
    private Player whitePlayer;
    private Player blackPlayer;
    private GameState currState;

    public GameController() {
        this.isWhiteTurn = true;
        this.moveHistory = new ArrayList<>();
        this.gameBoard = new Board(new PieceFactory());
        this.whitePlayer = new Player(true);
        this.blackPlayer = new Player(false);
        this.currState = GameState.IN_PROGRESS;
    }

    public Board getBoard() {
        return gameBoard;
    }

    public void startNewGame() {
        this.isWhiteTurn = true;
        this.moveHistory.clear();
        this.gameBoard = new Board(new PieceFactory());
        this.whitePlayer.reset();
        this.blackPlayer.reset();
    }

    public boolean isGameOver() {
        if (currState == GameState.CHECKMATE || currState == GameState.STALEMATE) {
            return true;
        }
        return false;
    }

    public Player getCurrentPlayer() {
        return isWhiteTurn ? whitePlayer : blackPlayer;
    }

    public Player getWinner() {

        if (!isGameOver()) {
            throw new IllegalStateException("Cannot get winner - game is not over");
        }

        Move lastMove = moveHistory.get(moveHistory.size() - 1);
        Piece lastMovedPiece = lastMove.getMovedPiece();
        return lastMovedPiece.getIsWhite() ? whitePlayer : blackPlayer;

    }

    public void switchTurn() {
        isWhiteTurn = !isWhiteTurn;
    }

    public void processMove(Position from, Position to) {
        Piece piece = gameBoard.getPieceAt(from);
        Move move = new Move(from, to, piece, Optional.empty(), MoveType.NORMAL);
        gameBoard.makeMove(move);
        moveHistory.add(move);
        switchTurn();
    }


    public void undoLastMove() {
        Move lastMove = moveHistory.get(moveHistory.size() - 1);
        gameBoard.undoMove(lastMove);
    }


    public List<Move> getValidMovesForPiece(Position position) {
        Piece piece = gameBoard.getPieceAt(position);
        if (piece == null || piece.getIsWhite() != isWhiteTurn) {
            return new ArrayList<>();
        }
        return gameBoard.getValidMoves(piece, piece.getBasicMoves(position));
    }


}
