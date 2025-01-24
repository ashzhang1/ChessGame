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
}
