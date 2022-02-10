package checkers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import common.Color;
import common.GamePosition;

public class CheckersMatch {
	
	private Board board;
	private int turn;
	private Color currentPlayer;
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private CheckersPiece promotion;
	private boolean capture;

	public CheckersMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}
	
	public Board getBoard() {
		return board;
	}

	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public CheckersPiece getPromotion() {
		return promotion;
	}
	
	public boolean getCapture() {
		return capture;
	}

	public CheckersPiece[][] getPieces() {
		CheckersPiece[][] mat = new CheckersPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (CheckersPiece) board.piece(i, j);
			}
		}
		return mat;
	}

	public boolean[][] possibleMoves(GamePosition source) {
		Position position = source.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}

	public CheckersPiece performCheckersMove(GamePosition sourcePosition, GamePosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);

		CheckersPiece movedPiece = (CheckersPiece) board.piece(target);

		// promotion
		promotion = null;
		if (movedPiece instanceof Man) {
			if ((movedPiece.getColor() == Color.WHITE && target.getRow() == 0)
					|| (movedPiece.getColor() == Color.BLACK && target.getRow() == 7)) {
				promotion = (CheckersPiece) board.piece(target);
				promotion = replacePromotedPiece();
			}
		}

		nextTurn();

		return (CheckersPiece) capturedPiece;
	}

	public CheckersPiece replacePromotedPiece() {
		if (promotion == null) {
			throw new IllegalStateException("There's no piece to promote!");
		}
		Position pos = promotion.getCheckersPosition().toPosition();
		Piece p = board.removePiece(pos);
		piecesOnTheBoard.remove(p);
		
		CheckersPiece newPiece = new CheckersKing(board,promotion.getColor());
		board.placePiece(newPiece, pos);
		piecesOnTheBoard.add(newPiece);
		return newPiece;
	}

	private Piece makeMove(Position sourcePosition, Position targetPosition) {
		CheckersPiece p = (CheckersPiece) board.removePiece(sourcePosition);
		Piece capturedPiece = board.removePiece(targetPosition);
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
		}
		board.placePiece(p, targetPosition);

		return capturedPiece;
	}

	private void undoMove(Position source, Position target, Piece capturedPiece) {
		CheckersPiece p = (CheckersPiece) board.removePiece(target);
		board.placePiece(p, source);
		if (capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			piecesOnTheBoard.add(capturedPiece);
		}

	}

	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new CheckersException("There is no piece on source position!");
		}
		if (currentPlayer != ((CheckersPiece) board.piece(position)).getColor()) {
			throw new CheckersException("Selected piece doesn't belong to the current player!");
		}
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new CheckersException("There is no possible move for the chosen piece!");
		}
	}

	private void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) {
			throw new CheckersException("The chosen piece can't move to the target position!");
		}
	}

	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private void placeNewPiece(char column, int row, CheckersPiece piece) {
		board.placePiece(piece, new GamePosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}

	private void initialSetup() {
		placeNewPiece('a', 1, new Man(board, Color.WHITE));
		placeNewPiece('c', 1, new Man(board, Color.WHITE));
		placeNewPiece('e', 1, new Man(board, Color.WHITE));
		placeNewPiece('g', 1, new Man(board, Color.WHITE));
		placeNewPiece('b', 2, new Man(board, Color.WHITE));
		placeNewPiece('d', 2, new Man(board, Color.WHITE));
		placeNewPiece('f', 2, new Man(board, Color.WHITE));
		placeNewPiece('h', 2, new Man(board, Color.WHITE));
		placeNewPiece('a', 3, new Man(board, Color.WHITE));
		placeNewPiece('c', 3, new Man(board, Color.WHITE));
		placeNewPiece('e', 3, new Man(board, Color.WHITE));
		placeNewPiece('g', 3, new Man(board, Color.WHITE));

		placeNewPiece('b', 8, new Man(board, Color.BLACK));
		placeNewPiece('d', 8, new Man(board, Color.BLACK));
		placeNewPiece('f', 8, new Man(board, Color.BLACK));
		placeNewPiece('h', 8, new Man(board, Color.BLACK));
		placeNewPiece('a', 7, new Man(board, Color.BLACK));
		placeNewPiece('c', 7, new Man(board, Color.BLACK));
		placeNewPiece('e', 7, new Man(board, Color.BLACK));
		placeNewPiece('g', 7, new Man(board, Color.BLACK));
		placeNewPiece('b', 6, new Man(board, Color.BLACK));
		placeNewPiece('d', 6, new Man(board, Color.BLACK));
		placeNewPiece('f', 6, new Man(board, Color.BLACK));
		placeNewPiece('h', 6, new Man(board, Color.BLACK));

	}

}
