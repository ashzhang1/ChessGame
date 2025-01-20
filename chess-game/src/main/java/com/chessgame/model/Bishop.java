package com.chessgame.model;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{

    private static final int[][] BISHOP_DIRECTIONS = {
        {1, 1},   // North-East (file increase, rank increase)
        {1, -1},  // South-East (file increase, rank decrease)
        {-1, -1}, // South-West (file decrease, rank decrease)
        {-1, 1}   // North-West (file decrease, rank increase)
    };

    public Bishop(boolean isWhite) {
        super(isWhite, PieceValue.BISHOP.getValue());
    }

    @Override
    public List<Position> getBasicMoves(Position pos) {
        List<Position> moves = new ArrayList<Position>();

        for (int[] direction : BISHOP_DIRECTIONS) {
            for (int step = 1; step <= 7; step++) {

                int newFile = pos.getFile() + (direction[0] * step);
                int newRank = pos.getRank() + (direction[1] * step);

                if (!moveWithinBounds(new Position(newFile, newRank))) {
                    break;
                }

                moves.add(new Position(newFile, newRank));
            }
        }

        return moves;
    }
}
