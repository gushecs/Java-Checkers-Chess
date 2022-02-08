package common;

import boardgame.Position;
import chess.ChessException;

public class GamePosition {

	private char column;
	private int row;
	
	public GamePosition(char column, int row) {
		if (column<'a' || column>'h' || row<1 || row>8) {
			throw new ChessException("Error instantiating ChessPosition. Valid position is from column a to h, from row 1 to 8!");
		}
		this.column = column;
		this.row = row;
	}

	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}
	
	public Position toPosition() {
		return new Position(8-row,column-'a');
	}
	
	public static GamePosition fromPosition(Position position) {
		return new GamePosition((char)('a'+position.getColumn()),8-position.getRow());
	}

	@Override
	public String toString() {
		return "" + column + row;
	}
	
}
