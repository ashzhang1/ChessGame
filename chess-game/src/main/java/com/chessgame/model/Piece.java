package com.chessgame.model;

import java.util.List;

public abstract class Piece {
    protected boolean isWhite;
    protected int value;
    protected boolean isCaptured;

    public Piece(boolean isWhite, int value) {
        this.isWhite = isWhite;
        this.value = value;
        this.isCaptured = false;
    }

    public abstract List<Position> getBasicMoves(Position pos);



    protected boolean moveWithinBounds(Position pos) {
        return pos.getRank() >= 0 && pos.getRank() <= 7 &&
                pos.getFile() >= 0 && pos.getFile() <= 7;
    }
}
