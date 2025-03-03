package com.chessgame.commands;

import com.chessgame.controller.GameController;
import com.chessgame.model.Position;

public class CommandHandler {
    private GameController controller;

    public CommandHandler(GameController controller) {
        this.controller = controller;
    }

    public void handleSquareClick(Position position) {
        new SquareClickCommand(position, controller).executeCommand();
    }

    public void handleNewGame() {
        new NewGameCommand(controller).executeCommand();
    }
}
