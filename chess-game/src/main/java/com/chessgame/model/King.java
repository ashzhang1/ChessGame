package com.chessgame.model;

import com.chessgame.model.movement.NonSlidingPieceValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public King(String id, boolean isWhite) {
        super(id, isWhite, PieceValue.KING.getValue(), new NonSlidingPieceValidator());
    }

    @Override
    public List<Move> getBasicMoves(Position pos) {
        List<Move> moves = new ArrayList<>();

        for (int[] move : KING_MOVES) {

            int newFile = pos.getFile() + move[0];
            int newRank = pos.getRank() + move[1];

            if (newFile < 0 || newFile >= 8 || newRank < 0 || newRank >= 8) {
                continue;
            }

            Position newPosition = new Position(newFile, newRank);
            moves.add(new Move(pos, newPosition, this, Optional.empty(), MoveType.NORMAL));
        }


        return moves;
    }
}
