package com.chessgame.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SelectedPieceState {
    private Optional<Piece> selectedPiece;
    private Optional<Position> selectedPosition;
    private List<Move> validMoves;


    public SelectedPieceState() {
        this.selectedPiece = Optional.empty();
        this.selectedPosition = Optional.empty();
        this.validMoves = new ArrayList<>();
    }

    public void clearSelection() {
        this.selectedPiece = Optional.empty();
        this.selectedPosition = Optional.empty();
        this.validMoves.clear();
    }

    public boolean isSquareValid(Position position) {
        for (Move move : validMoves) {
            if (position.equals(move.getEndPosition())) {
                return true;
            }
        }
        return false;
    }

    public void updateSelection(Piece piece, Position selectedPos, List<Move> moves) {
        this.selectedPiece = Optional.of(piece);
        this.selectedPosition = Optional.of(selectedPos);
        this.validMoves = moves;
    }

    public boolean hasSelectedPiece() {
        return selectedPiece.isPresent();
    }

    public Optional<Position> getSelectedPosition() {
        return selectedPosition;
    }

    public List<Position> getValidMovesPositions() {
        List<Position> endPositions = new ArrayList<>();

        for (Move move : validMoves) {
            endPositions.add(move.getEndPosition());
        }
        return endPositions;
    }
}
