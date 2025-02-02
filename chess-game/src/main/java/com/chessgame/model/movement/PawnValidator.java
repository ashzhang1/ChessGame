package com.chessgame.model.movement;

import com.chessgame.model.Board;
import com.chessgame.model.Move;
import com.chessgame.model.Piece;
import com.chessgame.model.Position;

import java.util.ArrayList;
import java.util.List;

public class PawnValidator implements MoveValidator{

    @Override
    public List<Move> filterValidMoves(List<Move> basicMoves, Board board) {

        System.out.println("PawnValidator: Filtering " + basicMoves.size() + " moves");

        List<Move> filteredMoves = new ArrayList<>();

        for (Move move : basicMoves) {
            Position startSquare = move.getStartPosition();
            Position endSquare = move.getEndPosition();

            System.out.println("Checking move from " + startSquare.getFile() + "," + startSquare.getRank()
                    + " to " + endSquare.getFile() + "," + endSquare.getRank());

            if (startSquare.isOnSameFile(endSquare)) {
                System.out.println("Forward move");
                //Two-squares forward move: BOTH squares must be empty
                if (startSquare.getRankDistance(endSquare) == 2) {
                    // Get the square between start and end
                    int intermediateRank = (startSquare.getRank() + endSquare.getRank()) / 2;
                    Position squareBetween = new Position(startSquare.getFile(), intermediateRank);

                    // Check both squares are empty
                    if (!board.isSquareOccupied(squareBetween) && !board.isSquareOccupied(endSquare)) {
                        filteredMoves.add(move);
                    }
                }
                else { //Forward moves: square MUST be empty
                    if (!board.isSquareOccupied(endSquare)) {
                        filteredMoves.add(move);
                    }
                }
            }
            else {
                System.out.println("Diagonal move - checking for capture");
                //Diagonal moves: MUST have enemy piece to be valid
                if (board.canPieceCapture(startSquare, endSquare)) {
                    filteredMoves.add(move);
                }
            }
        }
        System.out.println("PawnValidator: Returning " + filteredMoves.size() + " valid moves");
        return filteredMoves;
    }

    @Override
    public boolean canCapturePosition(Move move, Board board) {
        Position startPos = move.getStartPosition();
        Position endPos = move.getEndPosition();
        Piece pawn = move.getMovedPiece();

        // Check if it's a diagonal move
        if (!startPos.isOnSameDiagonal(endPos)) {
            return false;
        }

        // Check if it's one square diagonally
        int rankDistance = Math.abs(startPos.getRankDistance(endPos));
        int fileDistance = Math.abs(startPos.getFileDistance(endPos));
        if (rankDistance != 1 || fileDistance != 1) {
            return false;
        }

        // Check if pawn is moving in correct direction
        int rankDirection = endPos.getRank() - startPos.getRank();

        // White pawns move up (positive rank)
        if (pawn.getIsWhite() && rankDirection < 0) {
            return false;
        }

        // Black pawns move down (negative rank)
        if (!pawn.getIsWhite() && rankDirection > 0) {
            return false;
        }

        return true;
    }
}
