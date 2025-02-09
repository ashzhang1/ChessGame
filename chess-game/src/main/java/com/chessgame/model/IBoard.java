package com.chessgame.model;

import java.util.*;

public interface IBoard {

    Optional<Position> getPiecePosition(Piece piece);

    Piece getPieceAt(Position position);

    List<Move> getValidMoves(Piece piece, List<Move> moves);

}
