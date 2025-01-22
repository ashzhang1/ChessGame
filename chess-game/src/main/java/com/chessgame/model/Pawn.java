package com.chessgame.model;

import com.chessgame.model.movement.PawnValidator;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{

    public Pawn(String id, boolean isWhite) {
        
        super(id, isWhite, PieceValue.PAWN.getValue(), new PawnValidator());
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
        if (oneForward.moveWithinBounds()) {
            moves.add(oneForward);
        }

        // Two squares forward from starting position
        if (pos.getRank() == startingRank) {
            Position twoForward = new Position(pos.getFile(), pos.getRank() + (forward * 2));
            if (twoForward.moveWithinBounds()) {
                moves.add(twoForward);
            }
        }

        // Diagonal capture moves
        Position leftDiagonal = new Position(pos.getFile() - 1, pos.getRank() + forward);
        if (leftDiagonal.moveWithinBounds()) {
            moves.add(leftDiagonal);
        }

        Position rightDiagonal = new Position(pos.getFile() + 1, pos.getRank() + forward);
        if (rightDiagonal.moveWithinBounds()) {
            moves.add(rightDiagonal);
        }

        return moves;
    }
}
