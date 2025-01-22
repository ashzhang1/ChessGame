package com.chessgame.model;

import com.chessgame.model.movement.NonSlidingPieceValidator;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    private static final int[][] KNIGHT_MOVES = {
        {1, 2}, {-1, 2},    // Right/left 1, up 2
        {1, -2}, {-1, -2},  // Right/left 1, down 2
        {2, 1}, {2, -1},    // Right 2, up/down 1
        {-2, 1}, {-2, -1}   // Left 2, up/down 1
    };

    public Knight(String id, boolean isWhite) {
        super(id, isWhite, PieceValue.KNIGHT.getValue(), new NonSlidingPieceValidator());
    }

    @Override
    public List<Position> getBasicMoves(Position pos) {
        List<Position> moves = new ArrayList<Position>();

        for (int[] move: KNIGHT_MOVES) {
            int newFile = pos.getFile() + move[0];
            int newRank = pos.getRank() + move[1];

            Position newPosition = new Position(newFile, newRank);
            if (!newPosition.moveWithinBounds()) {
                continue;
            }

            moves.add(new Position(newFile, newRank));
        }

        return moves;
    }
}
