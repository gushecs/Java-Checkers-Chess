package application;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import common.GamePosition;

public class Program {

	public static void main(String[] args) {
		
		ChessMatch chessMatch = new ChessMatch();
		Scanner sc = new Scanner(System.in);
		List<ChessPiece> captured = new ArrayList<>();
		
		while (!chessMatch.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch,captured);
			
				System.out.println();
				System.out.print("Source: ");
				GamePosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(),possibleMoves,chessMatch);
			
				System.out.println();
				System.out.print("Target: ");
				GamePosition target = UI.readChessPosition(sc);
			
				ChessPiece capturedPiece= chessMatch.performChessMove(source,target);
				
				if (capturedPiece != null) {
					captured.add(capturedPiece);
				}
				
				if (chessMatch.getPromoted() != null) {
					System.out.print("Enter piece for promotion (R/N/B/Q): ");
					String type=sc.nextLine().toUpperCase();
					while (!type.equals("Q") && !type.equals("B") && !type.equals("N") && !type.equals("R")) {
						System.out.print("Invalid value!\nEnter piece for promotion (R/N/B/Q): ");
						type=sc.nextLine().toUpperCase();
					}
					chessMatch.replacePromotedPiece(type);
				}
				
			} catch (ChessException e){
				System.out.println(e.getMessage());
				sc.nextLine();
			} catch (InputMismatchException e){
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);
		
	}

}
