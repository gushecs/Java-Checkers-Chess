package checkers;

import java.util.List;

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

		// NW

		p.setValues(position.getRow() - 1, position.getColumn() - 1);

		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() - 1);
		}

		// SW

		p.setValues(position.getRow() + 1, position.getColumn() - 1);

		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() - 1);
		}

		// NE

		p.setValues(position.getRow() - 1 , position.getColumn() + 1);

		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() + 1);
		}

		// SE

		p.setValues(position.getRow() + 1, position.getColumn() + 1);

		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() + 1);
		}

		return mat;
	}
	
	@Override
	public boolean isThereACapture() {

		Position p = new Position(0, 0);

		// NW

		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			p.setValues(p.getRow() - 1, p.getColumn() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			p.setValues(p.getRow() - 1, p.getColumn() - 1);
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				return true;
			}
		}

		// SW

		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			p.setValues(p.getRow() + 1, p.getColumn() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			p.setValues(p.getRow() + 1, p.getColumn() - 1);
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				return true;
			}
		}

		// NE

		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			p.setValues(p.getRow() - 1, p.getColumn() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			p.setValues(p.getRow() - 1, p.getColumn() + 1);
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				return true;
			}
		}

		// SE

		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			p.setValues(p.getRow() + 1, p.getColumn() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			p.setValues(p.getRow() + 1, p.getColumn() + 1);
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void possibleCaptures(boolean[][] ghostPieces, List<CheckersPiece> capturedPieces,
			List<Position> capturePositions, List<Position> piecePositions, int longestStreak, Position position) {
		// TODO Auto-generated method stub
		
	}
}
