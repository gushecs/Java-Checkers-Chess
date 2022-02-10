package checkers;

import boardgame.Board;
import boardgame.Position;
import common.Color;

public class Man extends CheckersPiece{

	public Man(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "("; // to change
	}
	
	public String getSecondLetter() {
		return ")";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0,0);
		int move = 1;

		if (getColor() == Color.WHITE) {
			move = -1;
		}
		
		p.setValues(position.getRow()+move, position.getColumn()+1);
		if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()]=true;
		}
		
		p.setValues(position.getRow()+move, position.getColumn()-1);
		if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()]=true;
		}

		return mat;
	}
	
	@Override
	public boolean isThereACapture() {

		Position p = new Position(0,0);
		
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			p.setValues(position.getRow() + 2, position.getColumn() + 2);
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				return true;
			}
		}
		
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			p.setValues(position.getRow() - 2, position.getColumn() + 2);
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				return true;
			}
		}
		
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			p.setValues(position.getRow() + 2, position.getColumn() - 2);
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				return true;
			}
		}
		
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			p.setValues(position.getRow() - 2, position.getColumn() - 2);
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				return true;
			}
		}

		return false;
	}
	
}