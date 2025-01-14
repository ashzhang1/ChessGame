package com.chessgame.model;

public class Position {
    private int rank;
    private int file;

    // Constructor
    public Position(int rank, int file) {
        this.rank = rank;
        this.file = file;
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


}
