package com.chessgame.model.movement;

import com.chessgame.model.Board;
import com.chessgame.model.Move;
import com.chessgame.model.Position;

import java.util.List;

public interface MoveValidator {

    List<Move> filterValidMoves(List<Move> basicMoves, Board board);
}
