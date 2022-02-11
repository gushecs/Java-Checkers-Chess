package checkers;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import common.Color;
import common.GamePosition;

public class CheckersPiece extends Piece {

	private Color color;
	protected List<List<CheckersPiece>> capturedPieces;
	protected List<List<Position>> capturePositions;
	protected List<List<Position>> piecePositions;
	protected int longestStreak;

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

	public List<List<CheckersPiece>> getCapturedPieces() {
		return capturedPieces;
	}

	public List<List<Position>> getCapturePositions() {
		return capturePositions;
	}

	public List<List<Position>> getPiecePositions() {
		return piecePositions;
	}

	public int getLongestStreak() {
		return longestStreak;
	}

	protected boolean isThereOpponentPiece(Position position) {
		CheckersPiece p = (CheckersPiece) getBoard().piece(position);
		return p != null && p.getColor() != color;
	}

	@Override
	public boolean[][] possibleMoves() {
		// TODO Auto-generated method stub
		return null;
	}

	public void capture() {
		boolean[][] ghostPieces = new boolean[getBoard().getRows()][getBoard().getColumns()];
		this.capturedPieces = new ArrayList<>();
		this.capturePositions = new ArrayList<>();
		this.piecePositions = new ArrayList<>();
		this.longestStreak = 0;
		List<CheckersPiece> capturedPieces = new ArrayList<>();
		List<Position> capturePositions = new ArrayList<>();
		List<Position> piecePositions = new ArrayList<>();
		int longestStreak = 0;
		piecePositions.add(position);
		possibleCaptures(ghostPieces, capturedPieces, piecePositions, capturePositions, longestStreak, position);
	}

	public void possibleCaptures(boolean[][] ghostPieces, List<CheckersPiece> capturedPieces,
			List<Position> capturePositions, List<Position> piecePositions, int longestStreak, Position position) {
	}

	public boolean isThereACapture() {
		return false;
	}

}