package com.chessgame.model;

import com.chessgame.model.movement.IMoveStrategy;
import com.chessgame.model.movement.LinearMoveStrategy;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    private final IMoveStrategy moveStrategy;


    public Rook(boolean isWhite) {
        super(isWhite, PieceValue.ROOK.getValue());
        this.moveStrategy = LinearMoveStrategy.getInstance();
    }

    @Override
    public List<Position> getBasicMoves(Position pos) {
        return moveStrategy.getBasicMoves(pos);
    }
}
