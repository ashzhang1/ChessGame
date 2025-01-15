package com.chessgame.model;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{

    public Pawn(boolean isWhite) {
        super(isWhite, PieceValue.PAWN.getValue());
    }

    public List<Position> getBasicMoves(Position pos) {
        List<Position> moves = new ArrayList<Position>();

        // Find what colour the pawn is
        boolean isPawnWhite = this.isWhite;

        // Check if the piece is on starting rank
        // This will affect their possible forward moves
        int startingRank = isPawnWhite ? 2 : 7;

        // Forward moves
        int forward = isPawnWhite ? 1 : -1;

        Position oneForward = new Position(pos.getRank() + forward, pos.getFile()); // one move forward
        moves.add(oneForward);

        if (pos.getRank() == startingRank) {
            Position twoForward = new Position(pos.getRank() + (forward * 2), pos.getFile()); // two moves forward
            moves.add(twoForward);
        }

        // Diagonal capture moves
        Position leftDiagonal = new Position(pos.getRank() + forward, pos.getFile() - 1);
        Position rightDiagonal = new Position(pos.getRank() + forward, pos.getFile() + 1);
        moves.add(leftDiagonal);
        moves.add(rightDiagonal);

        return moves;
    }
}
