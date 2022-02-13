package checkers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Position;
import common.Color;

public class Man extends CheckersPiece {

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

		Position p = new Position(0, 0);
		int move = 1;

		if (getColor() == Color.WHITE) {
			move = -1;
		}

		p.setValues(position.getRow() + move, position.getColumn() + 1);
		if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() + move, position.getColumn() - 1);
		if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		return mat;
	}

	@Override
	public void possibleCaptures(boolean[][] ghostPieces, List<CheckersPiece> capturedPieces,
			List<Position> capturePositions, List<Position> piecePositions, int longestStreak,Position position) {

		Position p1 = new Position(0, 0);
		Position p2 = new Position(0, 0);

		p1.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (getBoard().positionExists(p1) && isThereOpponentPiece(p1) && !ghostPieces[p1.getRow()][p1.getColumn()]) {
			p2.setValues(position.getRow() + 2, position.getColumn() + 2);
			if (getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
				ghostPieces[p1.getRow()][p1.getColumn()]=true;
				capturedPieces.add((CheckersPiece)getBoard().piece(p1));
				capturePositions.add(p1);
				piecePositions.add(p2);
				longestStreak+=1;
				
				possibleCaptures(ghostPieces,capturedPieces,capturePositions,piecePositions,longestStreak,p2);
				
				if (longestStreak>this.longestStreak) {
					this.capturedPieces = new ArrayList<>();
					this.capturePositions = new ArrayList<>();
					this.piecePositions = new ArrayList<>();
					this.longestStreak=longestStreak;
					List<CheckersPiece> cpl = capturedPieces.stream().collect(Collectors.toList());
					this.capturedPieces.add(cpl);
					List<Position> cposl = capturePositions.stream().collect(Collectors.toList());
					this.capturePositions.add(cposl);
					List<Position> ppl = piecePositions.stream().collect(Collectors.toList());
					this.piecePositions.add(ppl);
				} else if (longestStreak==this.longestStreak) {
					List<CheckersPiece> cpl = capturedPieces.stream().collect(Collectors.toList());
					this.capturedPieces.add(cpl);
					List<Position> cposl = capturePositions.stream().collect(Collectors.toList());
					this.capturePositions.add(cposl);
					List<Position> ppl = piecePositions.stream().collect(Collectors.toList());
					this.piecePositions.add(ppl);
				}
				
				ghostPieces[p1.getRow()][p1.getColumn()]=false;
				capturedPieces.remove((CheckersPiece)getBoard().piece(p1));
				capturePositions.remove(p1);
				piecePositions.remove(p2);
				longestStreak-=1;
			}
		}

		p1.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (getBoard().positionExists(p1) && isThereOpponentPiece(p1) && !ghostPieces[p1.getRow()][p1.getColumn()]) {
			p2.setValues(position.getRow() - 2, position.getColumn() + 2);
			if (getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
				ghostPieces[p1.getRow()][p1.getColumn()]=true;
				capturedPieces.add((CheckersPiece)getBoard().piece(p1));
				capturePositions.add(p1);
				piecePositions.add(p2);
				longestStreak+=1;
				
				possibleCaptures(ghostPieces,capturedPieces,capturePositions,piecePositions,longestStreak,p2);
				
				if (longestStreak>this.longestStreak) {
					this.capturedPieces = new ArrayList<>();
					this.capturePositions = new ArrayList<>();
					this.piecePositions = new ArrayList<>();
					this.longestStreak=longestStreak;
					List<CheckersPiece> cpl = capturedPieces.stream().collect(Collectors.toList());
					this.capturedPieces.add(cpl);
					List<Position> cposl = capturePositions.stream().collect(Collectors.toList());
					this.capturePositions.add(cposl);
					List<Position> ppl = piecePositions.stream().collect(Collectors.toList());
					this.piecePositions.add(ppl);
				} else if (longestStreak==this.longestStreak) {
					List<CheckersPiece> cpl = capturedPieces.stream().collect(Collectors.toList());
					this.capturedPieces.add(cpl);
					List<Position> cposl = capturePositions.stream().collect(Collectors.toList());
					this.capturePositions.add(cposl);
					List<Position> ppl = piecePositions.stream().collect(Collectors.toList());
					this.piecePositions.add(ppl);
				}
				
				ghostPieces[p1.getRow()][p1.getColumn()]=false;
				capturedPieces.remove((CheckersPiece)getBoard().piece(p1));
				capturePositions.remove(p1);
				piecePositions.remove(p2);
				longestStreak-=1;
			}
		}

		p1.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (getBoard().positionExists(p1) && isThereOpponentPiece(p1) && !ghostPieces[p1.getRow()][p1.getColumn()]) {
			p2.setValues(position.getRow() + 2, position.getColumn() - 2);
			if (getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
				ghostPieces[p1.getRow()][p1.getColumn()]=true;
				capturedPieces.add((CheckersPiece)getBoard().piece(p1));
				capturePositions.add(p1);
				piecePositions.add(p2);
				longestStreak+=1;
				
				possibleCaptures(ghostPieces,capturedPieces,capturePositions,piecePositions,longestStreak,p2);
				
				if (longestStreak>this.longestStreak) {
					this.capturedPieces = new ArrayList<>();
					this.capturePositions = new ArrayList<>();
					this.piecePositions = new ArrayList<>();
					this.longestStreak=longestStreak;
					List<CheckersPiece> cpl = capturedPieces.stream().collect(Collectors.toList());
					this.capturedPieces.add(cpl);
					List<Position> cposl = capturePositions.stream().collect(Collectors.toList());
					this.capturePositions.add(cposl);
					List<Position> ppl = piecePositions.stream().collect(Collectors.toList());
					this.piecePositions.add(ppl);
				} else if (longestStreak==this.longestStreak) {
					List<CheckersPiece> cpl = capturedPieces.stream().collect(Collectors.toList());
					this.capturedPieces.add(cpl);
					List<Position> cposl = capturePositions.stream().collect(Collectors.toList());
					this.capturePositions.add(cposl);
					List<Position> ppl = piecePositions.stream().collect(Collectors.toList());
					this.piecePositions.add(ppl);
				}
				
				ghostPieces[p1.getRow()][p1.getColumn()]=false;
				capturedPieces.remove((CheckersPiece)getBoard().piece(p1));
				capturePositions.remove(p1);
				piecePositions.remove(p2);
				longestStreak-=1;
			}
		}

		p1.setValues(position.getRow() - 1, position.getColumn() - 1);
		if (getBoard().positionExists(p1) && isThereOpponentPiece(p1) && !ghostPieces[p1.getRow()][p1.getColumn()]) {
			p2.setValues(position.getRow() - 2, position.getColumn() - 2);
			if (getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2)) {
				ghostPieces[p1.getRow()][p1.getColumn()]=true;
				capturedPieces.add((CheckersPiece)getBoard().piece(p1));
				capturePositions.add(p1);
				piecePositions.add(p2);
				longestStreak+=1;
				
				possibleCaptures(ghostPieces,capturedPieces,capturePositions,piecePositions,longestStreak,p2);
				
				if (longestStreak>this.longestStreak) {
					this.capturedPieces = new ArrayList<>();
					this.capturePositions = new ArrayList<>();
					this.piecePositions = new ArrayList<>();
					this.longestStreak=longestStreak;
					List<CheckersPiece> cpl = capturedPieces.stream().collect(Collectors.toList());
					this.capturedPieces.add(cpl);
					List<Position> cposl = capturePositions.stream().collect(Collectors.toList());
					this.capturePositions.add(cposl);
					List<Position> ppl = piecePositions.stream().collect(Collectors.toList());
					this.piecePositions.add(ppl);
				} else if (longestStreak==this.longestStreak) {
					List<CheckersPiece> cpl = capturedPieces.stream().collect(Collectors.toList());
					this.capturedPieces.add(cpl);
					List<Position> cposl = capturePositions.stream().collect(Collectors.toList());
					this.capturePositions.add(cposl);
					List<Position> ppl = piecePositions.stream().collect(Collectors.toList());
					this.piecePositions.add(ppl);
				}
				
				ghostPieces[p1.getRow()][p1.getColumn()]=false;
				capturedPieces.remove((CheckersPiece)getBoard().piece(p1));
				capturePositions.remove(p1);
				piecePositions.remove(p2);
				longestStreak-=1;
			}
		}

		
	}

	@Override
	public boolean isThereACapture() {

		Position p = new Position(0, 0);

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