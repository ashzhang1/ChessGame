package com.chessgame.model.movement;

import com.chessgame.model.Position;

import java.util.ArrayList;
import java.util.List;

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
    public List<Position> getBasicMoves(Position pos) {
        List<Position> moves = new ArrayList<Position>();

        for (int[] direction : DIRECTIONS) {
            for (int step = 1; step <= 7; step++) {

                int newFile = pos.getFile() + (direction[0] * step);
                int newRank = pos.getRank() + (direction[1] * step);
                Position newPosition = new Position(newFile, newRank);

                if (!newPosition.moveWithinBounds()) {
                    break;
                }

                moves.add(newPosition);
            }
        }

        return moves;
    }



}
