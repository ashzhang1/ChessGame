package com.chessgame.commands;

import com.chessgame.controller.GameController;

public class NewGameCommand implements GameCommand {
    private final GameController controller;

    public NewGameCommand(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void executeCommand() {
        controller.startNewGame();
    }
}
