package com.chessgame.model.movement;

import com.chessgame.model.Board;
import com.chessgame.model.Move;
import com.chessgame.model.Position;

import java.util.ArrayList;
import java.util.List;

public class SlidingPieceValidator implements MoveValidator{
    // This is for the Queen, Rook, Bishop

    @Override
    public List<Move> filterValidMoves(List<Move> basicMoves, Board board) {

        List<Move> filteredMoves = new ArrayList<>();

        for (Move move : basicMoves) {
            Position startSquare = move.getStartPosition();
            Position endSquare = move.getEndPosition();

            if (!board.isPathClear(startSquare, endSquare)) {
                continue;
            }

            if (!board.isSquareOccupied(endSquare)) {
                filteredMoves.add(move);
            }
            else if (board.isSquareOccupied(endSquare) && board.canPieceCapture(startSquare, endSquare)) {
                filteredMoves.add(move);
            }
        }
        return filteredMoves;
    }

    @Override
    public boolean canCapturePosition(Move move, Board board) {
        Position startPos = move.getStartPosition();
        Position endPos = move.getEndPosition();

        // First check if the move is valid for this piece type
        List<Move> basicMoves = move.getMovedPiece().getBasicMoves(startPos);
        boolean isValidDirection = false;
        for (Move basicMove : basicMoves) {
            if (basicMove.getEndPosition().getFile() == endPos.getFile() &&
                    basicMove.getEndPosition().getRank() == endPos.getRank()) {
                isValidDirection = true;
                break;
            }
        }

        if (!isValidDirection) return false;

        // Then check if path is clear
        return board.isPathClear(startPos, endPos);
    }


}
