package com.chessgame.commands;

import com.chessgame.controller.GameController;
import com.chessgame.model.Position;

public class SquareClickCommand implements GameCommand {
    private final Position position;
    private final GameController controller;

    public SquareClickCommand(Position position, GameController controller) {
        this.position = position;
        this.controller = controller;
    }

    @Override
    public void executeCommand() {
        controller.handleSquareClick(position);
    }
}
