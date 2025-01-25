package com.chessgame.model.movement;

import com.chessgame.model.Board;
import com.chessgame.model.Move;
import com.chessgame.model.Position;

import java.util.ArrayList;
import java.util.List;

public class NonSlidingPieceValidator implements MoveValidator{
    // This is used for the King and Knight

    @Override
    public List<Move> filterValidMoves(List<Move> basicMoves, Board board) {

        List<Move> filteredMoves = new ArrayList<>();

        for (Move move : basicMoves) {
            Position startSquare = move.getStartPosition();
            Position endSquare = move.getEndPosition();

            if (!board.isSquareOccupied(endSquare)) {
                filteredMoves.add(move);
            }
            else if (board.isSquareOccupied(endSquare) &&
                    board.canPieceCapture(startSquare, endSquare)) {
                filteredMoves.add(move);
            }
        }

        return filteredMoves;
    }

    @Override
    public boolean canCapturePosition(Move move, Board board) {
        // For Knight/King, we just need to check if this move exists in their basic moves
        // If the piece can move there, it can capture
        Position startSquare = move.getStartPosition();
        Position endSquare = move.getEndPosition();

        // Get all the basic moves this piece could make from its position
        List<Move> basicMoves = move.getMovedPiece().getBasicMoves(startSquare);

        // Check if any of these basic moves can reach our target square
        for (Move basicMove : basicMoves) {
            if (basicMove.getEndPosition().getFile() == endSquare.getFile() &&
                    basicMove.getEndPosition().getRank() == endSquare.getRank()) {
                return true;
            }
        }

        return false;
    }
}
