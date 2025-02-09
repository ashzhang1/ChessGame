package com.chessgame.view;

import javax.swing.*;
import java.awt.*;

public class PlayerStatusPanel extends JPanel {
    private static final int SQUARE_SIZE = 40;
    private static final int CIRCLE_SIZE = 30;

    private JLabel playerLabel;
    private JLabel scoreLabel;
    private JPanel turnIndicator;
    private JPanel colourSquare;
    private boolean isWhitePlayer;

    public PlayerStatusPanel(boolean isWhitePlayer) {
        this.isWhitePlayer = isWhitePlayer;
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

        // Sub-component panels
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.setPreferredSize(new Dimension(100, 35));

        JPanel squarePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        squarePanel.setPreferredSize(new Dimension(50, 50));

        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        scorePanel.setPreferredSize(new Dimension(120, 35));

        JPanel indicatorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        indicatorPanel.setPreferredSize(new Dimension(50, 50));

        // Player Label (White/Black)
        playerLabel = new JLabel(isWhitePlayer ? "White" : "Black");
        playerLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        namePanel.add(playerLabel);

        // Colour Square
        colourSquare = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(SQUARE_SIZE, SQUARE_SIZE);
            }
        };
        colourSquare.setBackground(isWhitePlayer ? Color.WHITE : Color.BLACK);
        colourSquare.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        squarePanel.add(colourSquare);

        // Score Label --> initially 0
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        scorePanel.add(scoreLabel);

        // Turn Indicator (green circle)
        turnIndicator = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(CIRCLE_SIZE, CIRCLE_SIZE);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (isVisible()) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
                    g2d.setColor(Color.GREEN);
                    g2d.fillOval(0, 0, CIRCLE_SIZE, CIRCLE_SIZE);
                }
            }
        };
        turnIndicator.setOpaque(false);
        turnIndicator.setVisible(isWhitePlayer);
        indicatorPanel.add(turnIndicator);

        // Make all panels transparent
        namePanel.setOpaque(false);
        squarePanel.setOpaque(false);
        scorePanel.setOpaque(false);
        indicatorPanel.setOpaque(false);

        // Add all panels
        add(namePanel);
        add(squarePanel);
        add(scorePanel);
        add(indicatorPanel);
    }

    public void updateScore(int newScore) {
        scoreLabel.setText("Score: " + newScore);
    }

    public void updateTurn(boolean isCurrentTurn) {
        turnIndicator.setVisible(isCurrentTurn);
    }
}
