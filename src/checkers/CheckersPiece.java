package checkers;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import common.Color;
import common.GamePosition;

public class CheckersPiece extends Piece{
	
	private Color color;

	public CheckersPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public GamePosition getCheckersPosition() {
		return GamePosition.fromPosition(position);
	}
	
	protected boolean isThereOpponentPiece(Position position) {
		CheckersPiece p = (CheckersPiece)getBoard().piece(position);
		return p != null && p.getColor() != color;
	}

	@Override
	public boolean[][] possibleMoves() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean[][] possibleCaptures() {
		return null;
	}
	
	public boolean isThereACapture() {
		return false;
	}
	
}