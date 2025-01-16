package com.chessgame.model;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{

    private static final int[][] KING_MOVES = {
            {1, 0},
            {1, 1},
            {0, 1},
            {-1, 1},
            {-1, 0},
            {-1, -1},
            {0, -1},
            {1, -1}
    };

    public King(boolean isWhite) {
        super(isWhite, PieceValue.KING.getValue());
    }

    @Override
    public List<Position> getBasicMoves(Position pos) {
        List<Position> moves = new ArrayList<Position>();

        for (int[] move : KING_MOVES) {

            int newRank = pos.getRank() + move[0];
            int newFile = pos.getFile() + move[1];

            if (!moveWithinBounds(new Position(newRank, newFile))) {
                continue;
            }

            moves.add(new Position(newRank, newFile));
        }


        return moves;
    }
}
