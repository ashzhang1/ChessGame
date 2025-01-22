package com.chessgame.model.movement;

import com.chessgame.model.Board;
import com.chessgame.model.Position;

import java.util.ArrayList;
import java.util.List;

public class PawnValidator implements MoveValidator{

    @Override
    public List<Position> filterValidMoves(List<Position> basicMoves, Position start, Board board) {

        List<Position> filteredMoves = new ArrayList<>();

        for (Position endSquare : basicMoves) {


            if (start.isOnSameFile(endSquare)) {
                //Two-squares forward move: BOTH squares must be empty
                if (start.getRankDistance(endSquare) == 2) {
                    // Get the square between start and end
                    int intermediateRank = (start.getRank() + endSquare.getRank()) / 2;
                    Position squareBetween = new Position(start.getFile(), intermediateRank);

                    // Check both squares are empty
                    if (!board.isSquareOccupied(squareBetween) && !board.isSquareOccupied(endSquare)) {
                        filteredMoves.add(endSquare);
                    }
                }
                else { //Forward moves: square MUST be empty
                    if (!board.isSquareOccupied(endSquare)) {
                        filteredMoves.add(endSquare);
                    }
                }
            }
            else {
                //Diagonal moves: MUST have enemy piece to be valid
                if (board.canPieceCapture(start, endSquare)) {
                    filteredMoves.add(endSquare);
                }
            }
        }

        return filteredMoves;
    }
}
