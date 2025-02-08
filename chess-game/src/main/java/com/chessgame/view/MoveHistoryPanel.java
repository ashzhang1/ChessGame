package com.chessgame.view;

import javax.swing.*;
import java.awt.*;

public class MoveHistoryPanel extends JPanel{

    public MoveHistoryPanel() {

        setLayout(new BorderLayout());

        // Title
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Move History");
        title.setFont(new Font("Arial", Font.PLAIN, 32));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(title, BorderLayout.CENTER);

        // Create a panel for actual move history
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0));

        // Scrollable pane for the moves
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Set preferred size for the scroll pane
        scrollPane.setPreferredSize(new Dimension(120, 150));

        // Add components
        add(titlePanel, BorderLayout.NORTH);

        add(scrollPane, BorderLayout.CENTER);
    }

}
