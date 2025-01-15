package com.chessgame.model;

public enum PieceValue {
    PAWN(1),
    KNIGHT(3),
    BISHOP(3),
    ROOK(5),
    QUEEN(9),
    KING(0);

    private final int value;

    PieceValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
