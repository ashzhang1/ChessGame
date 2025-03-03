package com.chessgame.observer;

import com.chessgame.model.GameState;

public interface GameStatusViewObserver {

    void updatePlayersTurn(boolean isWhiteTurn);

    void updatePlayersScore(int whiteScore, int blackScore);

    void updateMoveHistory(int moveNum, String player, String moveChessNotation, GameState state);
    void onGameReset();
}
