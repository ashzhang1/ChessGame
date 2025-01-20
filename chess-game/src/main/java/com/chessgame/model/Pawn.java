package com.chessgame.model;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{

    public Pawn(boolean isWhite) {
        super(isWhite, PieceValue.PAWN.getValue());
    }

    @Override
    public List<Position> getBasicMoves(Position pos) {
        List<Position> moves = new ArrayList<Position>();

        // Find what colour the pawn is
        boolean isPawnWhite = this.isWhite;

        // Check if the piece is on starting rank
        int startingRank = isPawnWhite ? 2 : 7;

        // Forward moves
        int forward = isPawnWhite ? 1 : -1;

        // One square forward
        Position oneForward = new Position(pos.getFile(), pos.getRank() + forward);
        if (moveWithinBounds(oneForward)) {
            moves.add(oneForward);
        }

        // Two squares forward from starting position
        if (pos.getRank() == startingRank) {
            Position twoForward = new Position(pos.getFile(), pos.getRank() + (forward * 2));
            if (moveWithinBounds(twoForward)) {
                moves.add(twoForward);
            }
        }

        // Diagonal capture moves
        Position leftDiagonal = new Position(pos.getFile() - 1, pos.getRank() + forward);
        if (moveWithinBounds(leftDiagonal)) {
            moves.add(leftDiagonal);
        }

        Position rightDiagonal = new Position(pos.getFile() + 1, pos.getRank() + forward);
        if (moveWithinBounds(rightDiagonal)) {
            moves.add(rightDiagonal);
        }

        return moves;
    }
}
