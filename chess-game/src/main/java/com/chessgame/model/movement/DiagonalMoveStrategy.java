package com.chessgame.model.movement;

import com.chessgame.model.Move;
import com.chessgame.model.MoveType;
import com.chessgame.model.Piece;
import com.chessgame.model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DiagonalMoveStrategy implements IMoveStrategy{

    private static final int[][] DIRECTIONS = {
            {1, 1},   // North-East (file increase, rank increase)
            {1, -1},  // South-East (file increase, rank decrease)
            {-1, -1}, // South-West (file decrease, rank decrease)
            {-1, 1}   // North-West (file decrease, rank increase)
    };


    private static final DiagonalMoveStrategy INSTANCE = new DiagonalMoveStrategy();

    private DiagonalMoveStrategy() {}

    public static DiagonalMoveStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Move> getBasicMoves(Position pos, Piece piece) {
        List<Move> moves = new ArrayList<>();

        for (int[] direction : DIRECTIONS) {
            for (int step = 1; step <= 7; step++) {

                int newFile = pos.getFile() + (direction[0] * step);
                int newRank = pos.getRank() + (direction[1] * step);

                if (newFile < 0 || newFile >= 8 || newRank < 0 || newRank >= 8) {
                    break;  // Stop this direction if we're out of bounds
                }

                Position newPosition = new Position(newFile, newRank);
                moves.add(new Move(pos, newPosition, piece, Optional.empty(), MoveType.NORMAL));
            }
        }

        return moves;
    }



}
