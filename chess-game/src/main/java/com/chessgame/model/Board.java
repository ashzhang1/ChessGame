package com.chessgame.model;

import static com.chessgame.util.ChessConstants.*;

import java.util.List;
import java.util.Optional;

public class Board implements IBoard{
    private Piece[][] board;

    public Board(PieceFactory factory) {
        this.board = factory.createInitialBoard();
    }


    public Optional<Position> getPiecePosition(Piece piece) {
        for (int rank = 0; rank < BOARD_SIZE; rank++) {
            for (int file = 0; file < BOARD_SIZE; file++) {
                Piece boardPiece = board[rank][file];
                if (boardPiece != null && piece.equals(boardPiece)) {
                    return Optional.of(new Position(rank, file));
                }
            }
        }
        return Optional.empty();
    }

    public Piece getPieceAt(Position position) {
        int file = position.getFile();
        int rank = position.getRank();

        return board[rank][file];
    }

    public List<Position> getValidMoves(Piece piece, List<Position> moves) {
        return null;
    }

}
