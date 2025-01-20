package com.chessgame.model.movement;

import com.chessgame.model.Position;

import java.util.List;

public interface IMoveStrategy {

    List<Position> getBasicMoves(Position pos);
}
