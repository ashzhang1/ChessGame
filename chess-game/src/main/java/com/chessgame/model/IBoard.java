package com.chessgame.model;

import java.util.*;

public interface IBoard {


    public Optional<Position> getPiecePosition(Piece piece);

    public Piece getPieceAt(Position position);

    public List<Position> getValidMoves(Piece piece, List<Position> moves);

}
