package com.chessgame.model;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{

    private static final int[][] KING_MOVES = {
            {0, 1},   // North
            {1, 1},   // North-East
            {1, 0},   // East
            {1, -1},  // South-East
            {0, -1},  // South
            {-1, -1}, // South-West
            {-1, 0},  // West
            {-1, 1}   // North-West
    };

    public King(boolean isWhite) {
        super(isWhite, PieceValue.KING.getValue());
    }

    @Override
    public List<Position> getBasicMoves(Position pos) {
        List<Position> moves = new ArrayList<Position>();

        for (int[] move : KING_MOVES) {

            int newFile = pos.getFile() + move[0];
            int newRank = pos.getRank() + move[1];

            Position newPosition = new Position(newFile, newRank);
            if (!newPosition.moveWithinBounds()) {
                continue;
            }

            moves.add(new Position(newRank, newFile));
        }


        return moves;
    }
}
