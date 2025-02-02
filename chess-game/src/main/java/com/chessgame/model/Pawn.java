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
        int startingRank = isPawnWhite ? 1 : 6;
        int forward = isPawnWhite ? 1 : -1;

        System.out.println("Calculating moves for " + (isPawnWhite ? "white" : "black") + " pawn at " + pos.getFile() + "," + pos.getRank());

        // One square forward
        int newRank = pos.getRank() + forward;
        if (newRank >= 0 && newRank < 8) {
            Position oneForward = new Position(pos.getFile(), newRank);
            moves.add(new Move(pos, oneForward, this, Optional.empty(), MoveType.NORMAL));
            System.out.println("Added one forward to " + pos.getFile() + "," + newRank);
        }

        // Two squares forward from starting position
        if (pos.getRank() == startingRank) {
            int twoSquaresRank = pos.getRank() + (forward * 2);
            if (twoSquaresRank >= 0 && twoSquaresRank < 8) {
                Position twoForward = new Position(pos.getFile(), twoSquaresRank);
                moves.add(new Move(pos, twoForward, this, Optional.empty(), MoveType.PAWN_DOUBLE));
                System.out.println("Added two forward to " + pos.getFile() + "," + twoSquaresRank);
            }
        }

        // Diagonal capture moves
        int file = pos.getFile();

        // Left diagonal
        int leftFile = file - 1;
        if (leftFile >= 0 && newRank >= 0 && newRank < 8) {
            Position leftDiagonal = new Position(leftFile, newRank);
            moves.add(new Move(pos, leftDiagonal, this, Optional.empty(), MoveType.NORMAL));
            System.out.println("Added left diagonal to " + leftFile + "," + newRank);
        }

        // Right diagonal
        int rightFile = file + 1;
        if (rightFile < 8 && newRank >= 0 && newRank < 8) {
            Position rightDiagonal = new Position(rightFile, newRank);
            moves.add(new Move(pos, rightDiagonal, this, Optional.empty(), MoveType.NORMAL));
            System.out.println("Added right diagonal to " + rightFile + "," + newRank);
        }

        System.out.println("Total moves generated: " + moves.size());
        return moves;
    }
}
