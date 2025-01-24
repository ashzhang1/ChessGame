package com.chessgame.model.movement;

import com.chessgame.model.Board;
import com.chessgame.model.Move;
import com.chessgame.model.Position;

import java.util.ArrayList;
import java.util.List;

public class PawnValidator implements MoveValidator{

    @Override
    public List<Move> filterValidMoves(List<Move> basicMoves, Board board) {

        List<Move> filteredMoves = new ArrayList<>();

        for (Move move : basicMoves) {
            Position startSquare = move.getStartPosition();
            Position endSquare = move.getEndPosition();

            if (startSquare.isOnSameFile(endSquare)) {
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
                //Diagonal moves: MUST have enemy piece to be valid
                if (board.canPieceCapture(startSquare, endSquare)) {
                    filteredMoves.add(move);
                }
            }
        }

        return filteredMoves;
    }
}
