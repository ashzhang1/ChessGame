package com.chessgame.model;

import com.chessgame.model.movement.DiagonalMoveStrategy;
import com.chessgame.model.movement.IMoveStrategy;
import com.chessgame.model.movement.LinearMoveStrategy;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{
    private final IMoveStrategy moveStrategy;
    public Bishop(String id, boolean isWhite) {

        super(id, isWhite, PieceValue.BISHOP.getValue());
        this.moveStrategy = DiagonalMoveStrategy.getInstance();
    }

    @Override
    public List<Position> getBasicMoves(Position pos) {
        return moveStrategy.getBasicMoves(pos);
    }
}
