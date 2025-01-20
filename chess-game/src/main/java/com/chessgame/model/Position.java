package com.chessgame.model;

public class Position {
    private int file;
    private int rank;

    // Constructor
    public Position(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    // Getters
    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }

    public boolean isOnSameFile(Position pos) {
        // Check if the column values are the same
        if (pos == null) return false;

        return this.file == pos.getFile();
    }

    public boolean isOnSameRank(Position pos) {
        // Check if the rank values are the same

        if (pos == null) return false;

        return this.rank == pos.getRank();
    }

    public boolean isOnSameDiagonal(Position pos) {
        // Same diagonal = abs difference between rank is equal to the abs difference between files.

        if (pos == null) return false;

        return Math.abs(this.rank - pos.getRank()) == Math.abs(this.file - pos.getFile());
    }

    public boolean moveWithinBounds() {
        return file >= 0 && file <= 7 && rank >= 0 && rank <= 7;
    }
}
