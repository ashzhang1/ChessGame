package com.chessgame.model;

import java.util.Optional;

public class Move {
    private Position startPosition;
    private Position endPosition;
    private Piece movedPiece;
    private Optional<Piece> capturedPiece;
    private MoveType moveType;

    public Move(Position startPosition, Position endPosition, Piece movedPiece,
                Optional<Piece> capturedPiece, MoveType moveType) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.movedPiece = movedPiece;
        this.capturedPiece = capturedPiece;
        this.moveType = moveType;
    }

    //getters
    public Position getStartPosition() {
        return startPosition;
    }

    public Position getEndPosition() {
        return endPosition;
    }
}
