package com.chessgame.model;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    private static final int[][] KNIGHT_MOVES = {
            {2, 1}, {2, -1},    // Up 2, right/left 1
            {-2, 1}, {-2, -1},  // Down 2, right/left 1
            {1, 2}, {1, -2},    // Up 1, right/left 2
            {-1, 2}, {-1, -2}   // Down 1, right/left 2
    };

    public Knight(boolean isWhite) {
        super(isWhite, PieceValue.KNIGHT.getValue());
    }

    @Override
    public List<Position> getBasicMoves(Position pos) {
        List<Position> moves = new ArrayList<Position>();

        for (int[] move: KNIGHT_MOVES) {
            int newRank = pos.getRank() + move[0];
            int newFile = pos.getFile() + move[1];

            if (!moveWithinBounds(new Position(newRank, newFile))) {
                break;
            }

            moves.add(new Position(newRank, newFile));
        }

        return moves;
    }
}
