package com.chessgame.model;

import com.chessgame.model.movement.NonSlidingPieceValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public PieceValue getValue() { return PieceValue.KNIGHT;}

    @Override
    public List<Move> getBasicMoves(Position pos) {
        List<Move> moves = new ArrayList<>();

        for (int[] move: KNIGHT_MOVES) {
            int newFile = pos.getFile() + move[0];
            int newRank = pos.getRank() + move[1];

            if (newFile < 0 || newFile >= 8 || newRank < 0 || newRank >= 8) {
                continue;  // Stop
            }

            Position newPosition = new Position(newFile, newRank);
            moves.add(new Move(pos, newPosition, this, Optional.empty(), MoveType.NORMAL));
        }

        return moves;
    }
}
