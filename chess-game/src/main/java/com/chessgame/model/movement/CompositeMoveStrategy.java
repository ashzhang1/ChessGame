package com.chessgame.model.movement;

import com.chessgame.model.Move;
import com.chessgame.model.Piece;
import com.chessgame.model.Position;

import java.util.ArrayList;
import java.util.List;

public class CompositeMoveStrategy implements IMoveStrategy {
    private List<IMoveStrategy> strategies;

    private static final CompositeMoveStrategy INSTANCE = new CompositeMoveStrategy(
            List.of(LinearMoveStrategy.getInstance(), DiagonalMoveStrategy.getInstance())
    );

    private CompositeMoveStrategy(List<IMoveStrategy> strategies) {
        this.strategies = strategies;
    }

    public static CompositeMoveStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Move> getBasicMoves(Position pos, Piece piece) {
        List<Move> moves = new ArrayList<>();

        // Combine moves from all strategies
        for (IMoveStrategy strategy : strategies) {
            moves.addAll(strategy.getBasicMoves(pos, piece));
        }

        return moves;
    }
}
