package com.chessgame.model;

import java.util.List;

public abstract class Piece {
    private boolean isWhite;
    private int value;
    private boolean isCaptured;

    public Piece(boolean isWhite, int value) {
        this.isWhite = isWhite;
        this.value = value;
        this.isCaptured = false;
    }

    public abstract List<Position> getBasicMoves(Position pos);

    public boolean move(Position pos) {
        return true;
    }


}
