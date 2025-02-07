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

    // Finds the board position given a piece
    public Optional<Position> getPiecePosition(Piece piece) {
        for (int rank = 0; rank < BOARD_SIZE; rank++) {
            for (int file = 0; file < BOARD_SIZE; file++) {
                Piece boardPiece = board[rank][file];
                if (boardPiece != null && piece.equals(boardPiece)) {
                    return Optional.of(new Position(file, rank));
                }
            }
        }
        return Optional.empty();
    }

    // Finds a piece given a position
    public Piece getPieceAt(Position position) {
        return board[position.getRank()][position.getFile()];
    }

    public boolean isPathClear(Position start, Position end) {

        /*
        * Integer.compare(a, b)
        * -1 if a < b
        *  0 if a == b
        *  1 if a > b
        * */
        int fileDirection = Integer.compare(end.getFile(), start.getFile());
        int rankDirection = Integer.compare(end.getRank(), start.getRank());

        Position curPos = start;
        Position nextPos = new Position(curPos.getFile() + fileDirection, curPos.getRank() + rankDirection);

        // Check all squares up to (but not including) end square
        while (nextPos.getFile() != end.getFile() || nextPos.getRank() != end.getRank()) {
            if (this.isSquareOccupied(nextPos)) return false;
            curPos = nextPos;
            nextPos = new Position(curPos.getFile() + fileDirection, curPos.getRank() + rankDirection);
        }
        return true;
    }

    public boolean isSquareOccupied(Position position) {
        return getPieceAt(position) != null;
    }

    public void setPieceAt(Position position, Piece piece) {
        board[position.getRank()][position.getFile()] = piece;
    }

    public boolean canPieceCapture(Position from, Position to) {

        Piece fromPiece = board[from.getRank()][from.getFile()];
        Piece toPiece = board[to.getRank()][to.getFile()];

        //returns true if there is a piece, and they're opposite colours
        return fromPiece != null && toPiece != null && fromPiece.isWhite != toPiece.isWhite;
    }

    public List<Move> getValidMoves(Piece piece, List<Move> moves) {

//        System.out.println("Starting with moves: " + moves.size());

        List<Move> validMoves = piece.moveValidator.filterValidMoves(moves, this);
//        System.out.println("After move validator: " + validMoves.size());

        // Filter out moves that leave king in check
        List<Move> finalValidMoves = new ArrayList<>();

        for (Move move : validMoves) {
            makeMove(move);
            if (!isKingInCheck(move.getMovedPiece().isWhite)) {
                finalValidMoves.add(move);
            }
            undoMove(move);
        }

//        System.out.println("After king check filter: " + finalValidMoves.size());
        return finalValidMoves;
    }

    public void makeMove(Move move) {

        // Get the movement positions
        Position startPos = move.getStartPosition();
        Position endPos = move.getEndPosition();

        // Get moving piece
        Piece movingPiece = this.getPieceAt(startPos);

        // Check if destination has piece (potential capture)
        Piece pieceAtDestination = this.getPieceAt(endPos);
        if (pieceAtDestination != null) {
            move.setMoveType(MoveType.CAPTURE);
            pieceAtDestination.setCaptured(true);
            move.setCapturedPiece(Optional.of(pieceAtDestination));
        }

        // Update board
        setPieceAt(startPos, null);  // Clear start position
        setPieceAt(endPos, movingPiece);  // Place piece in new position
    }

    public void undoMove(Move move) {

        // Get the movement positions
        Position startPos = move.getStartPosition();
        Position endPos = move.getEndPosition();

        // Get this piece to move back to og position
        Piece movedPiece = this.getPieceAt(endPos);

        // Move piece back to start
        board[startPos.getRank()][startPos.getFile()] = movedPiece;

        // If there was a capture, restore the captured piece
        if (move.getMoveType() == MoveType.CAPTURE) {
            Piece capturedPiece = move.getCapturedPiece().get();

            capturedPiece.setCaptured(false);  // Put the captured piece back into game
            setPieceAt(endPos, capturedPiece);
        } else {
            setPieceAt(endPos, null); // This wasn't a capture move
        }
    }

    public boolean isKingInCheck(boolean isWhiteKing) {

        // First we need to find the correct king
        Position kingPos = null;
        for (int rank = 0; rank < BOARD_SIZE; rank++) {
            for (int file = 0; file < BOARD_SIZE; file++) {
                Piece piece = board[rank][file];
                if (piece instanceof King && piece.isWhite == isWhiteKing) {
                    kingPos = new Position(file, rank);
                    break; // King is found
                }
            }
            if (kingPos != null) break; // Break outer loop too
        }

        // Then check if any enemy piece can capture king
        for (int rank = 0; rank < BOARD_SIZE; rank++) {
            for (int file = 0; file < BOARD_SIZE; file++) {
                Piece piece = board[rank][file];

                // Skip empty squares, same color pieces, and the king itself
                if (piece == null || piece.isWhite == isWhiteKing) {
                    continue;
                }

                Position piecePos = new Position(file, rank);
                Move kingCaptureMove = new Move(
                        piecePos,
                        kingPos,
                        piece,
                        Optional.of(getPieceAt(kingPos)), // The king is the captured piece
                        MoveType.CAPTURE
                );

                if (piece.moveValidator.canCapturePosition(kingCaptureMove, this)) {
                    return true; // King is in check
                }
            }
        }
        return false; // No piece can attack the king

    }

    public boolean hasValidMoves(boolean isWhite) {

        List<Move> moves = new ArrayList<>();

        // Iterate over the board and find pieces that belong to player
        for (int rank = 0; rank < BOARD_SIZE; rank++) {
            for (int file = 0; file < BOARD_SIZE; file++) {
                Position pos = new Position(file, rank);
                Piece piece = board[rank][file];
                if (piece != null && piece.isWhite == isWhite) {
                    moves.addAll(getValidMoves(piece, piece.getBasicMoves(pos)));
                }
            }
        }

        return moves.size() != 0;
    }

}
