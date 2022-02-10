package checkers;

import boardgame.Board;
import boardgame.Position;
import common.Color;

public class CheckersKing extends CheckersPiece{

	public CheckersKing(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "{"; // to change
	}
	
	public String getSecondLetter() {
		return "}";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		return mat;
	}
}
