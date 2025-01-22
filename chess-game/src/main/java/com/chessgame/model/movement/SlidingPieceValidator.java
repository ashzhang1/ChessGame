package com.chessgame.model.movement;

import com.chessgame.model.Board;
import com.chessgame.model.Position;

import java.util.ArrayList;
import java.util.List;

public class SlidingPieceValidator implements MoveValidator{
    // This is for the Queen, Rook, Bishop

    @Override
    public List<Position> filterValidMoves(List<Position> basicMoves, Position start, Board board) {

        List<Position> filteredMoves = new ArrayList<>();

        for (Position endSquare : basicMoves) {
            if (!board.isPathClear(start, endSquare)) {
                continue;
            }

            if (!board.isSquareOccupied(endSquare)) {
                filteredMoves.add(endSquare);
            }
            else if (board.isSquareOccupied(endSquare) && board.canPieceCapture(start, endSquare)) {
                filteredMoves.add(endSquare);
            }
        }
        return filteredMoves;
    }
}
