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

    public MoveType getMoveType() {
        return moveType;
    }

    public Optional<Piece> getCapturedPiece() {
        return capturedPiece;
    }

    public Piece getMovedPiece() {
        return movedPiece;
    }


    //setters
    public void setCapturedPiece(Optional<Piece> piece) {
        capturedPiece = piece;
    }

    public void setMoveType(MoveType type) {
        moveType = type;
    }
}
