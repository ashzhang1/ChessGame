package com.chessgame.model;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece{

    private static final int[][] ROOK_DIRECTIONS = {
            {1, 0}, // North
            {0, 1}, // East
            {-1, 0}, // South
            {0, -1} // West
    };

    public Rook(boolean isWhite) {
        super(isWhite, PieceValue.ROOK.getValue());
    }

    @Override
    public List<Position> getBasicMoves(Position pos) {
        List<Position> moves = new ArrayList<Position>();

        for (int[] direction : ROOK_DIRECTIONS) {
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
