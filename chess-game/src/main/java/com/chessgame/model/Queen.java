package com.chessgame.model;

import com.chessgame.model.movement.CompositeMoveStrategy;
import com.chessgame.model.movement.IMoveStrategy;
import com.chessgame.model.movement.SlidingPieceValidator;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    private final IMoveStrategy moveStrategy;


    public Queen(String id, boolean isWhite) {
        super(id, isWhite, PieceValue.QUEEN.getValue(), new SlidingPieceValidator());
        this.moveStrategy = CompositeMoveStrategy.getInstance();
    }

    @Override
    public List<Move> getBasicMoves(Position pos) {
        return moveStrategy.getBasicMoves(pos, this);
    }
}
