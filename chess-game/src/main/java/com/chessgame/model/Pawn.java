package com.chessgame.model;

import com.chessgame.model.movement.PawnValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Pawn extends Piece{

    public Pawn(String id, boolean isWhite) {
        
        super(id, isWhite, PieceValue.PAWN.getValue(), new PawnValidator());
    }

    @Override
    public List<Move> getBasicMoves(Position pos) {
        List<Move> moves = new ArrayList<>();

        boolean isPawnWhite = this.isWhite;
        int startingRank = isPawnWhite ? 2 : 7;
        int forward = isPawnWhite ? 1 : -1;

        // One square forward
        Position oneForward = new Position(pos.getFile(), pos.getRank() + forward);
        if (oneForward.moveWithinBounds()) {
            moves.add(new Move(pos, oneForward, this, Optional.empty(), MoveType.NORMAL));
        }

        // Two squares forward from starting position
        if (pos.getRank() == startingRank) {
            Position twoForward = new Position(pos.getFile(), pos.getRank() + (forward * 2));
            if (twoForward.moveWithinBounds()) {
                moves.add(new Move(pos, twoForward, this, Optional.empty(), MoveType.PAWN_DOUBLE));
            }
        }

        // Diagonal capture moves
        Position leftDiagonal = new Position(pos.getFile() - 1, pos.getRank() + forward);
        if (leftDiagonal.moveWithinBounds()) {
            moves.add(new Move(pos, leftDiagonal, this, Optional.empty(), MoveType.NORMAL));
        }

        Position rightDiagonal = new Position(pos.getFile() + 1, pos.getRank() + forward);
        if (rightDiagonal.moveWithinBounds()) {
            moves.add(new Move(pos, rightDiagonal, this, Optional.empty(), MoveType.NORMAL));
        }

        return moves;
    }
}
