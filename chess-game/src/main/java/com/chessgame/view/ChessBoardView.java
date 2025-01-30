package com.chessgame.view;

import com.chessgame.model.Board;
import com.chessgame.model.Piece;
import com.chessgame.model.Position;
import com.chessgame.util.ChessConstants;
import com.chessgame.util.PieceImageLoader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardView extends JPanel {

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


    public ChessBoardView() {
        this.lightSquareColor = new Color(230, 215, 240);
        this.darkSquareColor = new Color(150, 120, 180);
        this.squares = new SquarePanel[ChessConstants.BOARD_SIZE][ChessConstants.BOARD_SIZE];

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

    public void updateBoard(Board board) {
        for (int rank = 0; rank < ChessConstants.BOARD_SIZE; rank++) {
            for (int file = 0; file < ChessConstants.BOARD_SIZE; file++) {
                // Convert to chess coordinates (note: display is flipped vertically)
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


    private class SquarePanel extends JPanel {
        private final int row;
        private final int col;
        private ImageIcon piece;
        private JLabel pieceLabel;

        public SquarePanel(int row, int col) {
            this.row = row;
            this.col = col;

            setupSquare();
        }

        public boolean hasPiece() {
            return piece != null;
        }

        private void setupSquare() {
            setPreferredSize(new Dimension(60, 60));
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            setBackground(isLightSquare() ? lightSquareColor : darkSquareColor);
            setLayout(null);
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
            int size = Math.min(getWidth(), getHeight()) / ChessConstants.BOARD_SIZE;

            // Resize all squares to maintain square shape
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
    }
}
