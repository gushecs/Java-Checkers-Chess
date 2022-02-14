package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import checkers.CheckersException;
import checkers.CheckersMatch;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import common.GamePosition;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int game = 3;

		while (game == 3) {
			try {
				UI.clearScreen();
				System.out.println("Choose your game!");
				System.out.println();
				System.out.println("1 - Checkers");
				System.out.println("2 - Chess");
				System.out.println();
				game = sc.nextInt();
				sc.nextLine();
				if (game != 1 && game != 2) {
					game=3;
					throw new InputMismatchException("Invalid entry! Press enter to continue.");
				}
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}

		if (game == 1) {

			CheckersMatch checkersMatch = new CheckersMatch();

			while (!checkersMatch.isThereAWinner()) {

				try {
					CheckersUI.clearScreen();
					checkersMatch.updateCapture();

					if (checkersMatch.getCapture()) {
						
						int playID;
						checkersMatch.listCaptures();
						CheckersUI.printMatch(checkersMatch);
						
						System.out.print("Play number: ");
						playID=sc.nextInt();
						sc.nextLine();
						
						

					} else {

						CheckersUI.printMatch(checkersMatch);

						System.out.println();
						System.out.print("Source: ");
						GamePosition source = CheckersUI.readCheckersPosition(sc);

						boolean[][] possibleMoves = checkersMatch.possibleMoves(source);
						CheckersUI.clearScreen();
						CheckersUI.printBoard(checkersMatch.getPieces(), possibleMoves, checkersMatch);

						System.out.println();
						System.out.print("Target: ");
						GamePosition target = CheckersUI.readCheckersPosition(sc);

						checkersMatch.performCheckersMove(source, target);

					}
				} catch (CheckersException e) {
					System.out.println(e.getMessage());
					sc.nextLine();
				} catch (InputMismatchException e) {
					System.out.println(e.getMessage());
					sc.nextLine();
				}

			}

		} else {
			ChessMatch chessMatch = new ChessMatch();
			List<ChessPiece> captured = new ArrayList<>();

			while (!chessMatch.getCheckMate()) {
				try {
					UI.clearScreen();
					UI.printMatch(chessMatch, captured);

					System.out.println();
					System.out.print("Source: ");
					GamePosition source = UI.readChessPosition(sc);

					boolean[][] possibleMoves = chessMatch.possibleMoves(source);
					UI.clearScreen();
					UI.printBoard(chessMatch.getPieces(), possibleMoves, chessMatch);

					System.out.println();
					System.out.print("Target: ");
					GamePosition target = UI.readChessPosition(sc);

					ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

					if (capturedPiece != null) {
						captured.add(capturedPiece);
					}

					if (chessMatch.getPromoted() != null) {
						System.out.print("Enter piece for promotion (R/N/B/Q): ");
						String type = sc.nextLine().toUpperCase();
						while (!type.equals("Q") && !type.equals("B") && !type.equals("N") && !type.equals("R")) {
							System.out.print("Invalid value!\nEnter piece for promotion (R/N/B/Q): ");
							type = sc.nextLine().toUpperCase();
						}
						chessMatch.replacePromotedPiece(type);
					}

				} catch (ChessException e) {
					System.out.println(e.getMessage());
					sc.nextLine();
				} catch (InputMismatchException e) {
					System.out.println(e.getMessage());
					sc.nextLine();
				}
			}
			UI.clearScreen();
			UI.printMatch(chessMatch, captured);

		}
	}

}
