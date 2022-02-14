package checkers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Position;
import common.Color;

public class CheckersKing extends CheckersPiece {

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

		p.setValues(position.getRow() - 1, position.getColumn() + 1);

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
	public void possibleCaptures(boolean[][] ghostPieces, List<CheckersPiece> capturedPieces,
			List<Position> piecePositions, int longestStreak, Position position) {

		Position p1 = new Position(0, 0);
		Position p2 = new Position(0, 0);

		// NW

		p1.setValues(position.getRow() - 1, position.getColumn() - 1);
		while (getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {
			p1.setValues(p1.getRow() - 1, p1.getColumn() - 1);
		}
		if (getBoard().positionExists(p1) && isThereOpponentPiece(p1) && !ghostPieces[p1.getRow()][p1.getColumn()]) {
			p2.setValues(p1.getRow() - 1, p1.getColumn() - 1);
			if (getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
				ghostPieces[p1.getRow()][p1.getColumn()] = true;
				capturedPieces.add((CheckersPiece) getBoard().piece(p1));
				longestStreak += 1;
				while (getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
					Position p3 = new Position(p2.getRow(), p2.getColumn());
					piecePositions.add(p3);
					
					possibleCaptures(ghostPieces,capturedPieces,piecePositions,longestStreak,p2);
					
					if (longestStreak>this.longestStreak) {
						this.capturedPieces = new ArrayList<>();
						this.piecePositions = new ArrayList<>();
						this.longestStreak=longestStreak;
						List<CheckersPiece> cpl = capturedPieces.stream().collect(Collectors.toList());
						this.capturedPieces.add(cpl);
						List<Position> ppl = piecePositions.stream().collect(Collectors.toList());
						this.piecePositions.add(ppl);
					} else if (longestStreak==this.longestStreak) {
						List<CheckersPiece> cpl = capturedPieces.stream().collect(Collectors.toList());
						this.capturedPieces.add(cpl);
						List<Position> ppl = piecePositions.stream().collect(Collectors.toList());
						this.piecePositions.add(ppl);
					}
					piecePositions.remove(p3);
					p2.setValues(p2.getRow() - 1, p2.getColumn() - 1);
				}
				ghostPieces[p1.getRow()][p1.getColumn()]=false;
				capturedPieces.remove((CheckersPiece)getBoard().piece(p1));
				longestStreak-=1;
			}
		}

		// SW

		p1.setValues(position.getRow() + 1, position.getColumn() - 1);
		while (getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {
			p1.setValues(p1.getRow() + 1, p1.getColumn() - 1);
		}
		if (getBoard().positionExists(p1) && isThereOpponentPiece(p1) && !ghostPieces[p1.getRow()][p1.getColumn()]) {
			p2.setValues(p1.getRow() + 1, p1.getColumn() - 1);
			if (getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
				ghostPieces[p1.getRow()][p1.getColumn()] = true;
				capturedPieces.add((CheckersPiece) getBoard().piece(p1));
				longestStreak += 1;
				while (getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
					Position p3 = new Position(p2.getRow(), p2.getColumn());
					piecePositions.add(p3);
					
					possibleCaptures(ghostPieces,capturedPieces,piecePositions,longestStreak,p2);
					
					if (longestStreak>this.longestStreak) {
						this.capturedPieces = new ArrayList<>();
						this.piecePositions = new ArrayList<>();
						this.longestStreak=longestStreak;
						List<CheckersPiece> cpl = capturedPieces.stream().collect(Collectors.toList());
						this.capturedPieces.add(cpl);
						List<Position> ppl = piecePositions.stream().collect(Collectors.toList());
						this.piecePositions.add(ppl);
					} else if (longestStreak==this.longestStreak) {
						List<CheckersPiece> cpl = capturedPieces.stream().collect(Collectors.toList());
						this.capturedPieces.add(cpl);
						List<Position> ppl = piecePositions.stream().collect(Collectors.toList());
						this.piecePositions.add(ppl);
					}
					piecePositions.remove(p3);
					p2.setValues(p2.getRow() + 1, p2.getColumn() - 1);
				}
				ghostPieces[p1.getRow()][p1.getColumn()]=false;
				capturedPieces.remove((CheckersPiece)getBoard().piece(p1));
				longestStreak-=1;
			}
		}

		// NE

		p1.setValues(position.getRow() - 1, position.getColumn() + 1);
		while (getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {
			p1.setValues(p1.getRow() - 1, p1.getColumn() + 1);
		}
		if (getBoard().positionExists(p1) && isThereOpponentPiece(p1) && !ghostPieces[p1.getRow()][p1.getColumn()]) {
			p2.setValues(p1.getRow() - 1, p1.getColumn() + 1);
			if (getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
				ghostPieces[p1.getRow()][p1.getColumn()] = true;
				capturedPieces.add((CheckersPiece) getBoard().piece(p1));
				longestStreak += 1;
				while (getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
					Position p3 = new Position(p2.getRow(), p2.getColumn());
					piecePositions.add(p3);
					
					possibleCaptures(ghostPieces,capturedPieces,piecePositions,longestStreak,p2);
					
					if (longestStreak>this.longestStreak) {
						this.capturedPieces = new ArrayList<>();
						this.piecePositions = new ArrayList<>();
						this.longestStreak=longestStreak;
						List<CheckersPiece> cpl = capturedPieces.stream().collect(Collectors.toList());
						this.capturedPieces.add(cpl);
						List<Position> ppl = piecePositions.stream().collect(Collectors.toList());
						this.piecePositions.add(ppl);
					} else if (longestStreak==this.longestStreak) {
						List<CheckersPiece> cpl = capturedPieces.stream().collect(Collectors.toList());
						this.capturedPieces.add(cpl);
						List<Position> ppl = piecePositions.stream().collect(Collectors.toList());
						this.piecePositions.add(ppl);
					}
					piecePositions.remove(p3);
					p2.setValues(p2.getRow() - 1, p2.getColumn() + 1);
				}
				ghostPieces[p1.getRow()][p1.getColumn()]=false;
				capturedPieces.remove((CheckersPiece)getBoard().piece(p1));
				longestStreak-=1;
			}
		}

		// SE

		p1.setValues(position.getRow() + 1, position.getColumn() + 1);
		while (getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {
			p1.setValues(p1.getRow() + 1, p1.getColumn() + 1);
		}
		if (getBoard().positionExists(p1) && isThereOpponentPiece(p1) && !ghostPieces[p1.getRow()][p1.getColumn()]) {
			p2.setValues(p1.getRow() + 1, p1.getColumn() + 1);
			if (getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
				ghostPieces[p1.getRow()][p1.getColumn()] = true;
				capturedPieces.add((CheckersPiece) getBoard().piece(p1));
				longestStreak += 1;
				while (getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
					Position p3 = new Position(p2.getRow(), p2.getColumn());
					piecePositions.add(p3);
					
					possibleCaptures(ghostPieces,capturedPieces,piecePositions,longestStreak,p2);
					
					if (longestStreak>this.longestStreak) {
						this.capturedPieces = new ArrayList<>();
						this.piecePositions = new ArrayList<>();
						this.longestStreak=longestStreak;
						List<CheckersPiece> cpl = capturedPieces.stream().collect(Collectors.toList());
						this.capturedPieces.add(cpl);
						List<Position> ppl = piecePositions.stream().collect(Collectors.toList());
						this.piecePositions.add(ppl);
					} else if (longestStreak==this.longestStreak) {
						List<CheckersPiece> cpl = capturedPieces.stream().collect(Collectors.toList());
						this.capturedPieces.add(cpl);
						List<Position> ppl = piecePositions.stream().collect(Collectors.toList());
						this.piecePositions.add(ppl);
					}
					piecePositions.remove(p3);
					p2.setValues(p2.getRow() + 1, p2.getColumn() + 1);
				}
				ghostPieces[p1.getRow()][p1.getColumn()]=false;
				capturedPieces.remove((CheckersPiece)getBoard().piece(p1));
				longestStreak-=1;
			}
		}

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
}
