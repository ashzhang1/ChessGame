package com.chessgame.controller;

import com.chessgame.model.*;
import com.chessgame.observer.BoardViewObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GameController {
    private boolean isWhiteTurn;
    private List<Move> moveHistory;
    private Board gameBoard;
    private Player whitePlayer;
    private Player blackPlayer;
    private GameState currState;
    private Position selectedPosition;
    private List<Move> currentValidMoves;
    private BoardViewObserver observer;

    public GameController() {
        this.isWhiteTurn = true;
        this.moveHistory = new ArrayList<>();
        this.gameBoard = new Board(new PieceFactory());
        this.whitePlayer = new Player(true);
        this.blackPlayer = new Player(false);
        this.currState = GameState.IN_PROGRESS;
        this.currentValidMoves = new ArrayList<>();
    }

    public Board getBoard() {
        return gameBoard;
    }

    public void registerObserver(BoardViewObserver observer) {
        this.observer = observer;
    }


    public void startNewGame() {
        this.isWhiteTurn = true;
        this.moveHistory.clear();
        this.gameBoard = new Board(new PieceFactory());
        this.whitePlayer.reset();
        this.blackPlayer.reset();
        this.currState = GameState.IN_PROGRESS;
        observer.onGameReset(gameBoard);
    }

    public boolean isGameOver() {
        return (currState == GameState.CHECKMATE || currState == GameState.STALEMATE);
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
        executeMoveLogic(from, to);
        updateGameState();
    }

    public void executeMoveLogic(Position from, Position to) {
        Piece piece = gameBoard.getPieceAt(from);
        Move move = new Move(from, to, piece, Optional.empty(), MoveType.NORMAL);

        gameBoard.makeMove(move);
        observer.onPieceMoved(move);
        moveHistory.add(move);
    }

    public void updateGameState() {

        // Check if the opposition is in check and has any valid moves
        boolean isInCheck = gameBoard.isKingInCheck(!isWhiteTurn);
        boolean hasValidMoves = gameBoard.hasValidMoves(!isWhiteTurn);

        if (isInCheck) {
            if (!hasValidMoves) {
                currState = GameState.CHECKMATE;
                Player winner = getWinner();
                System.out.println("CHECKMATE");
                // Update game status on the UI
                // Show winner on the UI
                // Disable the board
                observer.disableBoard();
            }
            else {
                currState = GameState.CHECK;
                // Update game status on the UI
                switchTurn();
            }
        }
        else if (!hasValidMoves) {
            currState = GameState.STALEMATE;
            // Update game status on the UI
            // Disable the board
        } else {
            currState = GameState.IN_PROGRESS;
            switchTurn();
        }
    }

    public void undoLastMove() {
        Move lastMove = moveHistory.get(moveHistory.size() - 1);
        gameBoard.undoMove(lastMove);
    }

    public List<Move> getValidMovesForPiece(Position position) {
        Piece piece = gameBoard.getPieceAt(position);
        System.out.println("Getting moves for piece: " + piece);

        // Invalid square or piece
        if (piece == null || piece.getIsWhite() != isWhiteTurn) {
            return new ArrayList<>();
        }

        // First get the basic moves for that piece
        List<Move> basicMoves = piece.getBasicMoves(position);

        // Then filter out the invalid moves
        return gameBoard.getValidMoves(piece, basicMoves);
    }

    public void handleSquareClick(Position clickedPosition) {
        Piece clickedPiece = gameBoard.getPieceAt(clickedPosition);

        // Case 1: Clicking on own piece
        if (clickedPiece != null && clickedPiece.getIsWhite() == isWhiteTurn) {

            // Update selection and show new valid moves
            selectedPosition = clickedPosition;
            currentValidMoves = getValidMovesForPiece(clickedPosition);
            observer.onSquareSelected(clickedPosition, currentValidMoves.stream()
                    .map(Move::getEndPosition)
                    .collect(Collectors.toList()));
            return;
        }

        // Case 2: Clicking on valid destination square
        if (selectedPosition != null && isValidMove(clickedPosition)) {
            processMove(selectedPosition, clickedPosition);
            clearSelection();
            return;
        }

        // Case 3: Clicking on invalid square
        clearSelection();
        observer.onSelectionCleared();
    }

    private boolean isValidMove(Position endPosition) {
        return currentValidMoves.stream()
                .anyMatch(move -> move.getEndPosition().equals(endPosition));
    }

    // This function is used after a piece has moved or if player clicks on invalid square
    private void clearSelection() {
        selectedPosition = null;
        currentValidMoves.clear();
    }


}
