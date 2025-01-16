package com.chessgame.model;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{

    private static final int[][] BISHOP_DIRECTIONS = {
            {1, 1}, // North-East
            {1, -1}, // South-East
            {-1, -1}, // South-West
            {-1, 1} // North-West
    };

    public Bishop(boolean isWhite) {
        super(isWhite, PieceValue.BISHOP.getValue());
    }

    @Override
    public List<Position> getBasicMoves(Position pos) {
        List<Position> moves = new ArrayList<Position>();

        for (int[] direction : BISHOP_DIRECTIONS) {
            for (int step = 1; step <= 7; step++) {
                int newRank = pos.getRank() + (direction[0] * step);
                int newFile = pos.getFile() + (direction[1] * step);

                if (!moveWithinBounds(new Position(newRank, newFile))) {
                    break;
                }

                moves.add(new Position(newRank, newFile));
            }
        }

        return moves;
    }
}
