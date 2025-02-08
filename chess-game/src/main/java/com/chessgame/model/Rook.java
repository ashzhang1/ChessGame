package com.chessgame.model;

import com.chessgame.model.movement.IMoveStrategy;
import com.chessgame.model.movement.LinearMoveStrategy;
import com.chessgame.model.movement.SlidingPieceValidator;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    private final IMoveStrategy moveStrategy;


    public Rook(String id, boolean isWhite) {
        super(id, isWhite, PieceValue.ROOK.getValue(), new SlidingPieceValidator());
        this.moveStrategy = LinearMoveStrategy.getInstance();
    }

    @Override
    public PieceValue getValue() {return PieceValue.ROOK;}

    @Override
    public List<Move> getBasicMoves(Position pos) {
        return moveStrategy.getBasicMoves(pos, this);
    }
}
