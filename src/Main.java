// ======================================================================
// FILE:        Main.java
//
// AUTHOR:      Anyi Chen
//
// DESCRIPTION:	This file is the entry point for the program. It will 
// 				allow users to input ONE information: who starts first.
//				If the user's input is 1, then the user will play first;
//				Otherwise, AI will make the first movement.
// ======================================================================

import java.util.*;

public class Main {
	// ===============================================================
	// =					Help Functions
	// ===============================================================
	
	private static void checkInputError(Scanner in, String errormsg, String promptmsg)
	{
		while(!in.hasNextInt()) {
			System.out.println(errormsg);
			System.out.print(promptmsg);
		    in.next();
		}
	}
	
	private static int[] inputRowCol(Scanner in, int row, int col)
	{
		int[] result = new int[2];
		System.out.println();
		System.out.print("Enter the row number you want to put in (1-3): ");
		checkInputError(in, "The input must be an integer, please enter again.", "Enter the row number you want to put in (1-3): ");
		row = in.nextInt();
		while(row > 3) {
			System.out.println("The input must be in 1 - 3, please enter again.");
			System.out.print("Enter the row number you want to put in (1-3): ");
			checkInputError(in, "The input must be an integer, please enter again.", "Enter the row number you want to put in: ");
			row = in.nextInt();
		}
		result[0] = row;
		
		System.out.print("Enter the column number you want to put in (1-3): ");
		checkInputError(in, "The input must be an integer, please enter again.", "Enter the column number you want to put in (1-3): ");
		col = in.nextInt();
		while(col > 3) {
			System.out.println("The input must be in 1 - 3, please enter again.");
			System.out.print("Enter the column number you want to put in (1-3): ");
			checkInputError(in, "The input must be an integer, please enter again.", "Enter the column number you want to put in: ");
			col = in.nextInt();
		}
		result[1] = col;

		return result;
	}
	
	private static boolean empty(Board board) {
		for (int r = 0; r < 3; r++)
			for (int c = 0; c < 3; c++)
				if (board.getBoard()[c][r] == ' ')
					return true;
		return false;
	}
	
	
	// ===============================================================
	// =					Main Function
	// ===============================================================
	
	public static void main(String[] args)
	{
		System.out.println("Do you want to play first?"); 
		System.out.print("If yes, enter 1; if no, enter any other integers: ");
		
		boolean user = true;
		
		Scanner in = new Scanner(System.in);
		while(!in.hasNextInt()) {
			System.out.println("The input is invalid, please enter again.");
			System.out.print("If yes, enter 1; if no, enter any other integer: ");
		    in.next();
		}
		int num = in.nextInt();
		if (num != 1) 
			user = false;
		
		
		Board	board	= new Board(user);

		
		while(board.getWinner().equals("None") && empty(board)) {
			System.out.println();
			board.display();
			
			if (board.getTurn() == "User") {
				int col = 100;
				int row = 100;
			
				int[] rowcol = inputRowCol(in, row, col);
				row = rowcol[0];
				col = rowcol[1];
				while(!board.getBoardAvaliable(col, row)) {
					System.out.println("This position has been already occupied, please enter again.");
					rowcol = inputRowCol(in, row, col);
					row = rowcol[0];
					col = rowcol[1];
				}
				board.updateBoard(row,col,1);
			} else {
				MyAI	 myAI = new MyAI(board);
				int[] rowcol = myAI.getAction();
				board.updateBoard(rowcol[0]+1,rowcol[1]+1,2);		
			}
			board.checkWinner();
		}
		
		System.out.println();
		board.display();
		System.out.println("Winner is: " + board.getWinner());
		
	}

}
