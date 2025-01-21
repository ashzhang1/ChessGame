package com.chessgame.model;

import static com.chessgame.util.ChessConstants.*;

public class PieceFactory {

    public PieceFactory(){}

    public Piece[][] createInitialBoard() {
        Piece[][] board = new Piece[BOARD_SIZE][BOARD_SIZE];

        // Create white pieces
        board[WHITE_PIECES_RANK][ROOK_QUEEN_SIDE_FILE] = new Rook("WR1", true);
        board[WHITE_PIECES_RANK][KNIGHT_QUEEN_SIDE_FILE] = new Knight("WN1", true);
        board[WHITE_PIECES_RANK][BISHOP_QUEEN_SIDE_FILE] = new Bishop("WB1", true);
        board[WHITE_PIECES_RANK][QUEEN_FILE] = new Queen("WQ1", true);
        board[WHITE_PIECES_RANK][KING_FILE] = new King("WK1", true);
        board[WHITE_PIECES_RANK][BISHOP_KING_SIDE_FILE] = new Bishop("WB2", true);
        board[WHITE_PIECES_RANK][KNIGHT_KING_SIDE_FILE] = new Knight("WN2", true);
        board[WHITE_PIECES_RANK][ROOK_KING_SIDE_FILE] = new Rook("WR2", true);

        // Create white pawns
        for(int file = 0; file < BOARD_SIZE; file++) {
            board[WHITE_PAWNS_RANK][file] = new Pawn("WP".concat(String.valueOf(file + 1)), true);
        }

        // Create black pieces
        board[BLACK_PIECES_RANK][ROOK_QUEEN_SIDE_FILE] = new Rook("BR1", false);
        board[BLACK_PIECES_RANK][KNIGHT_QUEEN_SIDE_FILE] = new Knight("BN1", false);
        board[BLACK_PIECES_RANK][BISHOP_QUEEN_SIDE_FILE] = new Bishop("BB1", false);
        board[BLACK_PIECES_RANK][QUEEN_FILE] = new Queen("BQ1", false);
        board[BLACK_PIECES_RANK][KING_FILE] = new King("BK1", false);
        board[BLACK_PIECES_RANK][BISHOP_KING_SIDE_FILE] = new Bishop("BB2", false);
        board[BLACK_PIECES_RANK][KNIGHT_KING_SIDE_FILE] = new Knight("BN2", false);
        board[BLACK_PIECES_RANK][ROOK_KING_SIDE_FILE] = new Rook("BR2", false);


        // Create black pawns (rank 6)
        for(int file = 0; file < BOARD_SIZE; file++) {
            board[BLACK_PAWNS_RANK][file] = new Pawn("BP".concat(String.valueOf(file + 1)), false);
        }

        return board;
    }

    public Piece promotePawn(Pawn pawn) {
        return new Queen(pawn.id, pawn.isWhite);
    }
}
