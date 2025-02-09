package com.chessgame.view;

import javax.swing.*;
import java.awt.*;

public class MoveHistoryPanel extends JPanel{

    private JPanel contentPanel;
    private JScrollPane scrollPane;

    public MoveHistoryPanel() {

        setLayout(new BorderLayout());

        // Title
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Move History");
        title.setFont(new Font("Arial", Font.PLAIN, 32));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(title, BorderLayout.CENTER);

        // Create a panel for actual move history
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0));

        // Scrollable pane for the moves
        scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Set preferred size for the scroll pane
        scrollPane.setPreferredSize(new Dimension(120, 150));

        // Add components
        add(titlePanel, BorderLayout.NORTH);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void addMove(String moveText) {
        JLabel moveLabel = new JLabel(moveText);
        contentPanel.add(moveLabel);

        // Make sure to revalidate and repaint after adding new components
        contentPanel.revalidate();
        contentPanel.repaint();

        // Ensure scrolling happens after the UI updates
        SwingUtilities.invokeLater(() -> {
            JScrollBar vertical = scrollPane.getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
        });
    }

}
