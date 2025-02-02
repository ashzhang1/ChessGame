package com.chessgame.controller;

import com.chessgame.model.*;
import com.chessgame.observer.BoardViewObserver;

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
    private SelectedPieceState selectedPieceState;

    private BoardViewObserver observer;

    public GameController() {
        this.isWhiteTurn = true;
        this.moveHistory = new ArrayList<>();
        this.gameBoard = new Board(new PieceFactory());
        this.whitePlayer = new Player(true);
        this.blackPlayer = new Player(false);
        this.currState = GameState.IN_PROGRESS;
        this.selectedPieceState = new SelectedPieceState();
    }

    public void registerObserver(BoardViewObserver observer) {
        this.observer = observer;
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
        System.out.println("Getting moves for piece: " + piece);

        if (piece == null || piece.getIsWhite() != isWhiteTurn) {
            System.out.println("Invalid piece or wrong turn");
            return new ArrayList<>();
        }

        List<Move> basicMoves = piece.getBasicMoves(position);
        System.out.println("Basic moves: " + basicMoves.size());

        List<Move> validMoves = gameBoard.getValidMoves(piece, basicMoves);
        System.out.println("Valid moves after filtering: " + validMoves.size());

        return validMoves;
    }

    public void handleSquareClick(Position clickedPosition) {
        System.out.println("GameController handling click at: " + clickedPosition.getFile() + "," + clickedPosition.getRank());

        // No piece is currently selected
        if (!selectedPieceState.hasSelectedPiece()) {

            // Get piece at clicked position
            Piece clickedPiece = gameBoard.getPieceAt(clickedPosition);
            System.out.println("Clicked piece: " + clickedPiece);

            // Check if it's a valid piece --> piece must be own player's piece
            if (clickedPiece != null && clickedPiece.getIsWhite() == isWhiteTurn) {

                // Get valid moves for this piece
                List<Move> validMoves = getValidMovesForPiece(clickedPosition);

                // Update selection state
                selectedPieceState.updateSelection(clickedPiece, clickedPosition, validMoves);

                // Tell view to highlight valid squares
                // TODO: We'll need to add this method to ChessBoardView
                List<Position> endPositions = new ArrayList<>();
                for (Move move: validMoves) {
                    endPositions.add(move.getEndPosition());
                }
                observer.onSquareSelected(clickedPosition, endPositions);
            }
        }

        // If a piece is already selected, then player is clicking on desired move square
        else {

            if (selectedPieceState.hasSelectedPiece()) {

                // Process move if the clicked position is valid move square
                if (selectedPieceState.isSquareValid(clickedPosition)) {
                    processMove(selectedPieceState.getSelectedPosition().get(), clickedPosition);
                }

                // Invalid move, clear selection
                selectedPieceState.clearSelection();


                // Tell view to clear highlights
                // TODO: We'll need to add this method to ChessBoardView
                observer.onSelectionCleared();

            }

        }
    }


}
