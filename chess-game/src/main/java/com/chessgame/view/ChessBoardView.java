package com.chessgame.view;

import com.chessgame.controller.GameController;
import com.chessgame.model.Board;
import com.chessgame.model.Piece;
import com.chessgame.model.Position;
import com.chessgame.observer.BoardViewObserver;
import com.chessgame.util.ChessConstants;
import com.chessgame.util.PieceImageLoader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoardView extends JPanel implements BoardViewObserver {

    private static final Map<String, String> PIECE_CODES = new HashMap<>();
    static {
        // White pieces
        PIECE_CODES.put("pawn", "P");
        PIECE_CODES.put("rook", "R");
        PIECE_CODES.put("knight", "N");
        PIECE_CODES.put("bishop", "B");
        PIECE_CODES.put("queen", "Q");
        PIECE_CODES.put("king", "K");
    }


    private final SquarePanel[][] squares;
    private final Color lightSquareColor;
    private final Color darkSquareColor;
    private final Color highlightedSquareColor;
    private GameController gameController;  // Add this field


    public ChessBoardView(GameController controller) {
        this.lightSquareColor = new Color(230, 215, 240);
        this.darkSquareColor = new Color(150, 120, 180);
        this.highlightedSquareColor = new Color(242, 242, 136);
        this.squares = new SquarePanel[ChessConstants.BOARD_SIZE][ChessConstants.BOARD_SIZE];
        this.gameController = controller;
        setupBoard();
    }

    private void setupBoard() {
        setLayout(new GridLayout(ChessConstants.BOARD_SIZE, ChessConstants.BOARD_SIZE));

        // Adding all 64 squares to the grid
        for (int row = 0; row < ChessConstants.BOARD_SIZE; row++) {
            for (int col = 0; col < ChessConstants.BOARD_SIZE; col++) {
                squares[row][col] = new SquarePanel(row, col);
                add(squares[row][col]);
            }
        }
    }

    private void handleSquareClick(Position position) {
        gameController.handleSquareClick(position);  // We'll create this method next
    }

    public void updateBoard(Board board) {
        for (int rank = 0; rank < ChessConstants.BOARD_SIZE; rank++) {
            for (int file = 0; file < ChessConstants.BOARD_SIZE; file++) {
                int displayRank = ChessConstants.BOARD_SIZE - 1 - rank;
                Position pos = new Position(file, rank);
                Piece piece = board.getPieceAt(pos);

                if (piece == null) {
                    squares[displayRank][file].clearPiece();
                } else {
                    String color = piece.getIsWhite() ? "w" : "b";
                    String pieceCode = PIECE_CODES.get(piece.getClass().getSimpleName().toLowerCase());
                    squares[displayRank][file].addPieceImage(color, pieceCode);
                }
            }
        }
    }

    @Override
    public void onSquareSelected(Position position, List<Position> validMoves) {

        System.out.println("ChessBoardView: Highlighting squares for position: " + position.getFile() + "," + position.getRank());
        System.out.println("Number of valid moves: " + validMoves.size());


        // First clear any existing highlights
        onSelectionCleared();

        // Then highlight valid moves
        for (Position movePos : validMoves) {
            int displayRow = 7 - movePos.getRank();
            // Add bounds checking
            if (displayRow >= 0 && displayRow < 8 && movePos.getFile() >= 0 && movePos.getFile() < 8) {
                squares[displayRow][movePos.getFile()].highlightSquare();
            }
        }
    }

    @Override
    public void onSelectionCleared() {
        System.out.println("ChessBoardView: Clearing highlights");

        for (int row = 0; row < ChessConstants.BOARD_SIZE; row++) {
            for (int col = 0; col < ChessConstants.BOARD_SIZE; col++) {
                if (squares[row][col].isHighlighted) {
                    squares[row][col].removeHighlight();
                }
            }
        }
    }


    private class SquarePanel extends JPanel {
        private final int row;
        private final int col;
        private ImageIcon piece;
        private JLabel pieceLabel;
        private boolean isHighlighted;

        public SquarePanel(int row, int col) {
            this.row = row;
            this.col = col;
            this.isHighlighted = false;

            setupSquare();
            setupMouseListener();
        }

        public boolean hasPiece() {
            return piece != null;
        }

        private void setupSquare() {
            setPreferredSize(new Dimension(50, 50));
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            setBackground(isLightSquare() ? lightSquareColor : darkSquareColor);
            setLayout(null);
        }

        private void setupMouseListener() {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int gameRow = 7 - row;
                    Position clickedPosition = new Position(col, gameRow);
                    handleSquareClick(clickedPosition); // Tell ChessBoardView about the click
                }
            });
        }

        private void handleSquareClick(Position position) {
            System.out.println("Square clicked at position: " + position.getFile() + "," + position.getRank());
            // We need to add this method to ChessBoardView
            ChessBoardView.this.handleSquareClick(position);
        }

        private boolean isLightSquare() {
            return (row + col) % 2 == 0;
        }

        public String getChessNotation() {
            char file = (char) ('A' + col);
            int rank = 8 - row;
            return String.format("%c%d", file, rank);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Set the background color based on highlight state
            if (isHighlighted) {
                setBackground(highlightedSquareColor);
            } else {
                setBackground(isLightSquare() ? lightSquareColor : darkSquareColor);
            }

            // Resize all squares to maintain square shape
            int size = Math.min(getWidth(), getHeight()) / ChessConstants.BOARD_SIZE;
            for (int row = 0; row < ChessConstants.BOARD_SIZE; row++) {
                for (int col = 0; col < ChessConstants.BOARD_SIZE; col++) {
                    squares[row][col].setPreferredSize(new Dimension(size, size));
                }
            }
            revalidate();
        }

        public void addPieceImage(String colour, String pieceType) {

            // Some other piece on the square already, have to clear out first
            if (this.hasPiece()) {
                clearPiece();
            }

            this.piece = PieceImageLoader.getPieceImage(colour, pieceType);
            ImageIcon resizedPiece = PieceImageLoader.resizeImage(piece, getWidth(), getHeight());

            pieceLabel = new JLabel(resizedPiece);
            pieceLabel.setSize(getWidth(), getHeight());
            pieceLabel.setLocation(0, 0);

            // Add to panel
            setLayout(null);
            add(pieceLabel);

            revalidate();
            repaint();
        }

        public void clearPiece() {
            if (hasPiece()) {
                remove(pieceLabel);
                pieceLabel = null;
                piece = null;

                revalidate();
                repaint();
            }

        }

        public void setHighlighted(boolean highlighted) {
            this.isHighlighted = highlighted;
        }

        public void highlightSquare() {
            setHighlighted(true);
            repaint();
        }

        public void removeHighlight() {
            setHighlighted(false);
            setBackground(isLightSquare() ? lightSquareColor : darkSquareColor);
            repaint();
        }
    }
}
