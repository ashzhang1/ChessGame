package com.chessgame.model.movement;

import com.chessgame.model.Move;
import com.chessgame.model.Piece;
import com.chessgame.model.Position;

import java.util.List;

public interface IMoveStrategy {

    List<Move> getBasicMoves(Position pos, Piece piece);
}
