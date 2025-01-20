package com.chessgame.model;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    private static final int[][] QUEEN_DIRECTIONS = {
            {0, 1},   // North
            {1, 1},   // North-East
            {1, 0},   // East
            {1, -1},  // South-East
            {0, -1},  // South
            {-1, -1}, // South-West
            {-1, 0},  // West
            {-1, 1}   // North-West
    };

    public Queen(boolean isWhite) {
        super(isWhite, PieceValue.QUEEN.getValue());
    }

    @Override
    public List<Position> getBasicMoves(Position pos) {
        List<Position> moves = new ArrayList<Position>();

        for (int[] direction : QUEEN_DIRECTIONS) {
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
