package com.chessgame.util;

public final class ChessConstants {
    private ChessConstants() {};

    // Board dimensions
    public static final int BOARD_SIZE = 8;
    public static final int MAX_RANK = 7;
    public static final int MAX_FILE = 7;

    // Rank positions
    public static final int FIRST_RANK = 0;
    public static final int SECOND_RANK = 1;
    public static final int SEVENTH_RANK = 6;
    public static final int EIGHTH_RANK = 7;

    // White piece starting positions
    public static final int WHITE_PIECES_RANK = FIRST_RANK;
    public static final int WHITE_PAWNS_RANK = SECOND_RANK;

    // Black piece starting positions
    public static final int BLACK_PIECES_RANK = EIGHTH_RANK;
    public static final int BLACK_PAWNS_RANK = SEVENTH_RANK;


    // Piece File Positions
    public static final int ROOK_QUEEN_SIDE_FILE = 0;
    public static final int KNIGHT_QUEEN_SIDE_FILE = 1;
    public static final int BISHOP_QUEEN_SIDE_FILE = 2;
    public static final int QUEEN_FILE = 3;
    public static final int KING_FILE = 4;
    public static final int BISHOP_KING_SIDE_FILE = 5;
    public static final int KNIGHT_KING_SIDE_FILE = 6;
    public static final int ROOK_KING_SIDE_FILE = 7;

}
