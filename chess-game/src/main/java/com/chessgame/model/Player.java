package com.chessgame.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.chessgame.util.ChessConstants.BOARD_SIZE;

public class Player {

    private boolean isWhite;
    private List<Piece> capturedPieces;
    private int score;

    public Player(boolean isWhite) {
        this.isWhite = isWhite;
        capturedPieces = new ArrayList<Piece>();
        score = 0;
    }

    // getters
    public int getScore() {
        return score;
    }

    public List<Piece> getCapturedPieces() {
        return List.copyOf(capturedPieces);
    }

    public Piece getLastCapturedPiece() {
        return capturedPieces.get(capturedPieces.size() - 1);
    }

    public List<Piece> getActivePieces(Board board) {
        List<Piece> activePieces = new ArrayList<>();
        for (int rank = 0; rank < BOARD_SIZE; rank++) {
            for (int file = 0; file < BOARD_SIZE; file++) {
                Position piecePosition = new Position(file, rank);
                Piece boardPiece = board.getPieceAt(piecePosition);
                if (boardPiece != null && boardPiece.isWhite == this.isWhite) {
                   activePieces.add(boardPiece);
                }
            }
        }
        return List.copyOf(activePieces);
    }

    public boolean isMyPiece(Piece piece) {
        return piece.isWhite == this.isWhite;
    }

    public void addCapturedPiece(Piece piece) {
        capturedPieces.add(piece);
    }

    public void updateScore(PieceValue val) {
        score += val.getValue();
    }

    public void reset() {
        capturedPieces.clear();
        score = 0;
    }


}
