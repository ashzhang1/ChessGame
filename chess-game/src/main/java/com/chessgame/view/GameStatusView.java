package com.chessgame.view;
import com.chessgame.model.GameState;
import com.chessgame.observer.GameStatusViewObserver;

import javax.swing.*;
import java.awt.*;

public class GameStatusView extends JPanel implements GameStatusViewObserver {
    private final PlayerStatusPanel whitePlayerPanel;
    private final PlayerStatusPanel blackPlayerPanel;

    private final MoveHistoryPanel moveHistoryPanel;

    public GameStatusView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create player panels
        whitePlayerPanel = new PlayerStatusPanel(true);
        blackPlayerPanel = new PlayerStatusPanel(false);

        // Add some padding at the top
        add(Box.createVerticalStrut(20));

        // Add player panels
        add(whitePlayerPanel);
        add(Box.createVerticalStrut(12)); // Spacing between the two panels
        add(blackPlayerPanel);
        add(Box.createVerticalStrut(24));

        // Separator Line
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1)); // Stretch across panel
        add(separator);

        add(Box.createVerticalStrut(20));

        // Create the move history panel
        moveHistoryPanel = new MoveHistoryPanel();
        add(moveHistoryPanel);
    }

    @Override
    public void updatePlayersTurn(boolean isWhiteTurn) {
        whitePlayerPanel.updateTurn(isWhiteTurn);
        blackPlayerPanel.updateTurn(!isWhiteTurn);
    }

    @Override
    public void updatePlayersScore(int whiteScore, int blackScore) {
        whitePlayerPanel.updateScore(whiteScore);
        blackPlayerPanel.updateScore(blackScore);
    }

    @Override
    public void updateMoveHistory(int moveNum, String player, String moveChessNotation, GameState state) {
        String moveText;
        if (state == GameState.IN_PROGRESS) {
            moveText = moveNum + ". " + player + " played " + moveChessNotation;
        }
        else if (state == GameState.CHECKMATE) {
            moveText = String.format("%d. %s played %s <font color='orange'>%s - %s wins</font>",
                    moveNum, player, moveChessNotation, state, player);
            moveText = "<html>" + moveText + "</html>";
        }
        else {
            moveText = String.format("%d. %s played %s <font color='orange'>%s</font>",
                    moveNum, player, moveChessNotation, state);
            moveText = "<html>" + moveText + "</html>";
        }
        moveHistoryPanel.addMove(moveText);
    }

    @Override
    public void onGameReset() {
        whitePlayerPanel.updateScore(0);
        blackPlayerPanel.updateScore(0);
        moveHistoryPanel.clearMoveHistory();
        updatePlayersTurn(true);

    }




}
