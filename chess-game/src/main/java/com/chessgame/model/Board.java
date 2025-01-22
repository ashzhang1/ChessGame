package com.chessgame.model;

import static com.chessgame.util.ChessConstants.*;

import java.util.ArrayList;
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

    public boolean isPathClear(Position start, Position end) {

        int fileDirection = Integer.compare(end.getFile(), start.getFile());
        int rankDirection = Integer.compare(end.getRank(), start.getRank());

        Position curPos = start;
        while (curPos.getFile() != end.getFile() || curPos.getRank() != end.getRank()) {
            curPos = new Position(curPos.getFile() + fileDirection, curPos.getRank() + rankDirection);

            if (this.isSquareOccupied(curPos)) return false;
        }
        return true;
    }

    public boolean isSquareOccupied(Position position) {
        return board[position.getRank()][position.getFile()] != null;
    }

    public boolean canPieceCapture(Position from, Position to) {

        Piece fromPiece = board[from.getRank()][from.getFile()];
        Piece toPiece = board[to.getRank()][to.getFile()];

        //returns true if there is a piece, and they're opposite colours
        return fromPiece != null && toPiece != null && fromPiece.isWhite != toPiece.isWhite;
    }

    public List<Position> getValidMoves(Piece piece, List<Position> moves) {

        List<Position> validMoves = new ArrayList<>();
        Optional<Position> startPos = this.getPiecePosition(piece);
        if (startPos.isPresent()) {
            validMoves = piece.moveValidator.filterValidMoves(
                    moves,
                    startPos.get(),
                    this
            );
        }

        // Filter out moves that leave king in check




        return null;
    }

}
