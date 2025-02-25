package com.chessgame.model;

public class Position {
    private int file;
    private int rank;

    // Constructor
    public Position(int file, int rank) {
        if (!isValidCoordinate(file) || !isValidCoordinate(rank)) {
            throw new IllegalArgumentException(
                    String.format("Invalid position coordinates: file=%d, rank=%d", file, rank)
            );
        }

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

    private boolean isValidCoordinate(int coordinate) {
        return coordinate >= 0 && coordinate < 8;
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

    public int getFileDistance(Position pos) {
        return pos.getFile() - this.file;
    }

    public int getRankDistance(Position pos) {
        return pos.getRank() - this.rank;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Position) {
            return ((Position) o).getFile() == this.file
                    && ((Position) o).getRank() == this.rank;
        }
        return false;
    }

}
