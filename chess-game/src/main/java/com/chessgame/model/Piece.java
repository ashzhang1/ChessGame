package com.chessgame.model;

import com.chessgame.model.movement.MoveValidator;

import java.util.List;

public abstract class Piece {
    protected final String id;
    protected boolean isWhite;
    protected int value;
    protected boolean isCaptured;

    protected final MoveValidator moveValidator;

    public Piece(String id, boolean isWhite, int value, MoveValidator moveValidator) {
        this.id = id;
        this.isWhite = isWhite;
        this.value = value;
        this.isCaptured = false;
        this.moveValidator = moveValidator;
    }

    public void setCaptured(boolean isCaptured) {
        this.isCaptured = isCaptured;
    }

    public boolean getIsWhite() {
        return isWhite;
    }

    public abstract PieceValue getValue();

    public abstract List<Move> getBasicMoves(Position pos);

    public abstract String getAbbreviation();

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Piece)) return false;
        Piece piece = (Piece) o;
        return this.id.equals(piece.id);
    }

}
