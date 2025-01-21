package com.chessgame.model;

import java.util.List;

public abstract class Piece {
    protected final String id;
    protected boolean isWhite;
    protected int value;
    protected boolean isCaptured;

    public Piece(String id, boolean isWhite, int value) {
        this.id = id;
        this.isWhite = isWhite;
        this.value = value;
        this.isCaptured = false;
    }

    public abstract List<Position> getBasicMoves(Position pos);

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
