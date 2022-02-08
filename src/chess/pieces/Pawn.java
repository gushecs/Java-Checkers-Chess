package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import common.Color;

public class Pawn extends ChessPiece{
	
	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch=chessMatch;
	}
	
	@Override
	public String toString() {
		return "P";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);
		int move=1;
		
		if (getColor()==Color.WHITE) {
			move=-1;
		}
		
		p.setValues(position.getRow() + move , position.getColumn());
		if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(position.getRow() +(2*move), position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getMoveCount()==0) {
				mat[p.getRow()][p.getColumn()] = true;
			}
		}
		p.setValues(position.getRow() + move, position.getColumn() - 1);
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		p.setValues(position.getRow() + move, position.getColumn() + 1);
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//en passant
		
		if (3.5+((double)move*.5)==(double)position.getRow()) {
			Position left = new Position(position.getRow(),position.getColumn()-1);
			if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left)==chessMatch.getEnPassantVulnerable()) {
				mat[left.getRow()+move][left.getColumn()]=true;
			}
			Position right = new Position(position.getRow(),position.getColumn()+1);
			if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right)==chessMatch.getEnPassantVulnerable()) {
				mat[right.getRow()+move][right.getColumn()]=true;
			}
		}
		
		return mat;

	}

	
	
}
