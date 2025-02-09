package com.chessgame.observer;

public interface GameStatusViewObserver {

    void updatePlayersTurn();

    void updatePlayersScore();

    void updateMoveHistory(int moveNum, String player, String moveChessNotation);

//    void updateGameStatus();
}
