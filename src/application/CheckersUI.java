package application;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import boardgame.Position;
import checkers.CheckersMatch;
import checkers.CheckersPiece;
import common.Color;
import common.GamePosition;

public class CheckersUI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static GamePosition readCheckersPosition(Scanner sc) {
		try {
			String s = sc.nextLine();
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));
			return new GamePosition(column, row);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Error reading CheckersPosition. Valid values are from a1 to h8!");
		}
	}

	public static void printMatch(CheckersMatch checkersMatch) {
		if (checkersMatch.getCapture()) {
			printBoard(checkersMatch.getPieces(), checkersMatch);
		} else {
			boolean[][] mat = new boolean[checkersMatch.getBoard().getRows()][checkersMatch.getBoard().getColumns()];
			printBoard(checkersMatch.getPieces(), mat, checkersMatch);
		}
		System.out.println();
		System.out.println();
		System.out.println("Turn: " + checkersMatch.getTurn());
		if (!checkersMatch.isThereAWinner()) {
			System.out.println("Waiting player " + checkersMatch.getCurrentPlayer());
		} else {
			System.out.println("There's no move left for player " + checkersMatch.getCurrentPlayer() + "!");
			Color winner = (checkersMatch.getCurrentPlayer() == Color.WHITE) ? Color.BLACK : Color.WHITE;
			System.out.println("Winner: " + winner);
		}
	}

	public static void printBoard(CheckersPiece[][] pieces, boolean[][] possibleMoves, CheckersMatch checkersMatch) {
		boolean[][] mat = new boolean[checkersMatch.getBoard().getRows()][checkersMatch.getBoard().getColumns()];
		for (int i = 0; i < pieces.length; i++) {
			System.out.print(ANSI_BLACK_BACKGROUND);
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				int whiteBlackIndex = (i + j) % 2;
				String bg = (whiteBlackIndex == 0) ? ANSI_BLACK_BACKGROUND : ANSI_WHITE_BACKGROUND;
				System.out.print(bg);
				printPiece(pieces[i][j], possibleMoves[i][j], mat[i][j], checkersMatch.getCurrentPlayer(), whiteBlackIndex);
			}
			System.out.print(ANSI_BLACK_BACKGROUND);
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
		System.out.print(ANSI_RESET);
	}

	public static void printBoard(CheckersPiece[][] pieces, CheckersMatch checkersMatch) {
		boolean[][] passingSquares = new boolean[checkersMatch.getBoard().getRows()][checkersMatch.getBoard()
				.getColumns()];
		boolean[][] finalSquares = new boolean[checkersMatch.getBoard().getRows()][checkersMatch.getBoard()
				.getColumns()];
		for (int i = 0; i < checkersMatch.getPiecePositions().size(); i++) {
			List<Position> pos = checkersMatch.getPiecePositions().get(i);
			for (int j = 0; j < pos.size(); j++) {
				passingSquares[pos.get(j).getRow()][pos.get(j).getColumn()] = true;
				if (j == pos.size() - 1) {
					finalSquares[pos.get(j).getRow()][pos.get(j).getColumn()] = true;
				}
			}
		}
		for (int i = 0; i < pieces.length; i++) {
			System.out.print(ANSI_BLACK_BACKGROUND);
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				int whiteBlackIndex = (i + j) % 2;
				String bg = (whiteBlackIndex == 0) ? ANSI_BLACK_BACKGROUND : ANSI_WHITE_BACKGROUND;
				System.out.print(bg);
				printPiece(pieces[i][j], passingSquares[i][j], finalSquares[i][j], checkersMatch.getCurrentPlayer(), whiteBlackIndex);
			}
			System.out.print(ANSI_BLACK_BACKGROUND);
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
		System.out.print(ANSI_RESET);
	}

	private static void printPiece(CheckersPiece piece, boolean background, boolean background2, Color currentPlayer, int whiteBlackIndex) {
		String bgc = " ";
		String pc = (whiteBlackIndex == 0) ? ANSI_WHITE : ANSI_BLACK;
		if (background) {
			bgc = ANSI_PURPLE_BACKGROUND;
			pc = ANSI_BLACK;
			if (background2) {
				bgc = ANSI_GREEN_BACKGROUND;
			}
		}
		if (piece != null) {
			if (!background && piece.getColor() == Color.WHITE && currentPlayer == Color.WHITE) {
				pc = ANSI_BLUE;
			} else if (!background && piece.getColor() == Color.BLACK && currentPlayer == Color.BLACK) {
				pc = ANSI_RED;
			}
			if (!bgc.equals(" ")) {
				System.out.print(bgc + pc + piece + piece.getSecondLetter() + ANSI_RESET);
			} else {
				System.out.print(pc + piece + piece.getSecondLetter() + ANSI_RESET);
			}
		} else {
			if (!bgc.equals(" ")) {
				System.out.print(bgc + "  " + ANSI_RESET);
			} else {
				System.out.print("  " + ANSI_RESET);
			}
		}
	}

}
