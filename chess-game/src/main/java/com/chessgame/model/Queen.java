package com.chessgame.model;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    private static final int[][] QUEEN_DIRECTIONS = {
            {1, 0},
            {1, 1},
            {0, 1},
            {-1, 1},
            {-1, 0},
            {-1, -1},
            {0, -1},
            {1, -1}
    };

    public Queen(boolean isWhite) {
        super(isWhite, PieceValue.QUEEN.getValue());
    }

    @Override
    public List<Position> getBasicMoves(Position pos) {
        List<Position> moves = new ArrayList<Position>();

        for (int[] direction : QUEEN_DIRECTIONS) {
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
