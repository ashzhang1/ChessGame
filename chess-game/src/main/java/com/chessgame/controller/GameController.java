package com.chessgame.controller;

import com.chessgame.model.*;
import com.chessgame.observer.BoardViewObserver;
import com.chessgame.observer.GameStatusViewObserver;

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
    private BoardViewObserver boardViewObserverbserver;
    private GameStatusViewObserver gameStatusViewObserver;

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

    public void registerBoardViewObserver(BoardViewObserver boardViewObserver) {
        this.boardViewObserverbserver = boardViewObserver;
    }

    public void registerBoardStatusViewObserver(GameStatusViewObserver gameStatusViewObserver) {
        this.gameStatusViewObserver = gameStatusViewObserver;
    }

    public void startNewGame() {
        this.isWhiteTurn = true;
        this.moveHistory.clear();
        this.gameBoard = new Board(new PieceFactory());
        this.whitePlayer.reset();
        this.blackPlayer.reset();
        this.currState = GameState.IN_PROGRESS;
        boardViewObserverbserver.onGameReset(gameBoard);
        gameStatusViewObserver.onGameReset();
    }

    public boolean isGameOver() {
        return (currState == GameState.CHECKMATE || currState == GameState.STALEMATE);
    }

    public Player getCurrentPlayer() {
        return isWhiteTurn ? whitePlayer : blackPlayer;
    }

    public Player getWhitePlayer() { return whitePlayer; }

    public Player getBlackPlayer() { return blackPlayer; }

    public Player getWinner(Move winningMove) {
        if (!isGameOver()) {
            throw new IllegalStateException("Cannot get winner - game is not over");
        }

        Piece winningPiece = winningMove.getMovedPiece();
        return winningPiece.getIsWhite() ? whitePlayer : blackPlayer;
    }

    public void switchTurn() {
        isWhiteTurn = !isWhiteTurn;
        gameStatusViewObserver.updatePlayersTurn();
    }

    public void processMove(Position from, Position to) {

        Piece piece = gameBoard.getPieceAt(from);
        Move move = new Move(from, to, piece, Optional.empty(), MoveType.NORMAL);

        executeMoveLogic(move);
        updateGameState(move);
        updateMoveHistory(move);
    }

    public void executeMoveLogic(Move move) {
        gameBoard.makeMove(move);
        boardViewObserverbserver.onPieceMoved(move);

        if (move.getMoveType() == MoveType.CAPTURE) {
            updatePlayersScore(move);
        }
    }

    public void updatePlayersScore(Move move) {

        Piece capturedPiece = move.getCapturedPiece().get();
        Player scoringPlayer = capturedPiece.getIsWhite() ? blackPlayer : whitePlayer;

        scoringPlayer.updateScore(capturedPiece.getValue());
        gameStatusViewObserver.updatePlayersScore();
    }

    public void updateMoveHistory(Move move) {
        moveHistory.add(move);

        int moveNum = (moveHistory.size() + 2 - 1) / 2;
        String player = move.getMovedPiece().getIsWhite() ? whitePlayer.toString() : blackPlayer.toString();
        String chessNotationMove = move.getChessNotation(currState);

        gameStatusViewObserver.updateMoveHistory(moveNum, player, chessNotationMove, currState);
    }

    public void updateGameState(Move move) {

        // Check if the opposition is in check and has any valid moves
        boolean isInCheck = gameBoard.isKingInCheck(!isWhiteTurn);
        boolean hasValidMoves = gameBoard.hasValidMoves(!isWhiteTurn);

        if (isInCheck) {
            if (!hasValidMoves) {
                currState = GameState.CHECKMATE;

                // Disable the board
                boardViewObserverbserver.disableBoard();
            }
            else {
                currState = GameState.CHECK;
                switchTurn();
            }
        }
        else if (!hasValidMoves) {
            currState = GameState.STALEMATE;

            // Disable the board
            boardViewObserverbserver.disableBoard();
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
            boardViewObserverbserver.onSquareSelected(clickedPosition, currentValidMoves.stream()
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
        boardViewObserverbserver.onSelectionCleared();
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
