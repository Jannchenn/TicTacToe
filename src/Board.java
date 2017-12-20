// ======================================================================
// FILE:        Board.java
//
// AUTHOR:      Anyi Chen
//
// DESCRIPTION:	This file is the view part of the project. It will update
//				and display the game board.
// ======================================================================

import java.util.Scanner;

public class Board {
	// ===============================================================
	// =						Declarations
	// ===============================================================
	
	private char[][]	board;		
	private boolean	first;
	private String	winner;
	private String	turn;
	
	
	// ===============================================================
	// =						Constructors
	// ===============================================================
	
	public Board (boolean user)
	{
		board = new char[3][3];
		winner = "None";
		first = user;
		if (first) turn = "User";
		else turn = "Computer";
		
		for ( int r = 0; r < 3; ++r )
			for ( int c = 0; c < 3; ++c )
				board[c][r] = ' ';
	}
	
	
	// ===============================================================
	// =					Public Function
	// ===============================================================	
	
	public boolean getBoardAvaliable(int col, int row)
	{
		return board[col-1][row-1]==' ';
	}
	
	public char[][] getBoard()
	{
		return board;
	}
	
	public String getTurn() 
	{
		return turn;
	}
	
	public String getWinner()
	{
		return winner;
	}
	
	public void checkWinner() {
		if (		board[0][0] == 'O' && board[0][1] == 'O' && board[0][2] == 'O' || 
				board[1][0] == 'O' && board[1][1] == 'O' && board[1][2] == 'O' || 
				board[2][0] == 'O' && board[2][1] == 'O' && board[2][2] == 'O' ||
				board[0][0] == 'O' && board[1][0] == 'O' && board[2][0] == 'O' ||
				board[0][1] == 'O' && board[1][1] == 'O' && board[2][1] == 'O' ||
				board[0][2] == 'O' && board[1][2] == 'O' && board[2][2] == 'O' ||
				board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O' ||
				board[0][2] == 'O' && board[1][1] == 'O' && board[2][0] == 'O')
			winner = "User";
		else 
		if (		board[0][0] == 'X' && board[0][1] == 'X' && board[0][2] == 'X' || 
				board[1][0] == 'X' && board[1][1] == 'X' && board[1][2] == 'X' || 
				board[2][0] == 'X' && board[2][1] == 'X' && board[2][2] == 'X' ||
				board[0][0] == 'X' && board[1][0] == 'X' && board[2][0] == 'X' ||
				board[0][1] == 'X' && board[1][1] == 'X' && board[2][1] == 'X' ||
				board[0][2] == 'X' && board[1][2] == 'X' && board[2][2] == 'X' ||
				board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X' ||
				board[0][2] == 'X' && board[1][1] == 'X' && board[2][0] == 'X')
			winner = "Computer";
		
	}
	
	public void updateBoard(int row, int col, int t)
	{
		if (t == 1) {
			board[col-1][row-1] = 'O';
			turn = "Computer";
		} else {
			board[col-1][row-1] = 'X';
			turn = "User";
		}
	}

	public void display()
	{
		System.out.println("Turn: " + turn );
		for ( int r = 0; r < 5; ++r ) {
			for ( int c = 0; c < 5; ++c ) {
				if(r%2 == 1 && c%2 == 1)
					System.out.print(" + ");
				else if(r%2 == 0 && c%2 == 0) {
					if(board[c/2][r/2] == ' ')
						System.out.print("   ");
					else if(board[c/2][r/2] == 'O')
						System.out.print(" X ");
					else if(board[c/2][r/2] == 'X')
						System.out.print(" O ");
				}
				else if (c%2 == 1)
					System.out.print(" | ");
				else 
					System.out.print(" — ");
			}
			System.out.println();
		}
	}
	
}
