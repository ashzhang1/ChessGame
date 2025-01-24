package com.chessgame.model;

import java.util.*;

public interface IBoard {


    public Optional<Position> getPiecePosition(Piece piece);

    public Piece getPieceAt(Position position);

    public List<Move> getValidMoves(Piece piece, List<Move> moves);

}
