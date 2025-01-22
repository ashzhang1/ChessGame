package com.chessgame.model.movement;

import com.chessgame.model.Board;
import com.chessgame.model.Position;

import java.util.List;

public interface MoveValidator {

    List<Position> filterValidMoves(List<Position> basicMoves, Position start, Board board);
}
