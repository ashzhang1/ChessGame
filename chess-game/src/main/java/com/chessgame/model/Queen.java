package com.chessgame.model;

import com.chessgame.model.movement.CompositeMoveStrategy;
import com.chessgame.model.movement.IMoveStrategy;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    private final IMoveStrategy moveStrategy;


    public Queen(boolean isWhite) {
        super(isWhite, PieceValue.QUEEN.getValue());
        this.moveStrategy = CompositeMoveStrategy.getInstance();
    }

    @Override
    public List<Position> getBasicMoves(Position pos) {
        return moveStrategy.getBasicMoves(pos);
    }
}
