package com.chessgame.observer;

import com.chessgame.model.Board;

public interface GameObserver {
    void onGameStateChanged(Board board);
}
