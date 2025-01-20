package com.chessgame.model.movement;

import com.chessgame.model.Position;
import java.util.List;
import java.util.ArrayList;

public class LinearMoveStrategy implements IMoveStrategy {
    private static final int[][] DIRECTIONS = {
            {0, 1},  // North
            {1, 0},  // East
            {0, -1}, // South
            {-1, 0}  // West
    };

    private static final LinearMoveStrategy INSTANCE = new LinearMoveStrategy();

    private LinearMoveStrategy() {}

    public static LinearMoveStrategy getInstance() {
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