package com.chessgame.observer;

import com.chessgame.model.Board;
import com.chessgame.model.Move;
import com.chessgame.model.Position;

import java.util.List;

public interface BoardViewObserver {
    void onSquareSelected(Position position, List<Position> validMoves);
    void onSelectionCleared();

    void onPieceMoved(Move move);

    void disableBoard();

    void onGameReset(Board board);
}
