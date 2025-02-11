package com.chessgame.observer;

import com.chessgame.model.GameState;

public interface GameStatusViewObserver {

    void updatePlayersTurn();

    void updatePlayersScore();

    void updateMoveHistory(int moveNum, String player, String moveChessNotation, GameState state);
    void onGameReset();
}
