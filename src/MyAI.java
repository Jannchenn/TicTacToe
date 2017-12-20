// ======================================================================
// FILE:        MyAI.java
//
// AUTHOR:      Anyi Chen
//
// DESCRIPTION:	This file is the AI (Model) part of the program. It 
//				contains the basic knowledge of AI, using MinMax
//				algorithm.
// ======================================================================

import java.util.ArrayList;
import java.util.HashMap;

public class MyAI {
	// ===============================================================
	// =						Declarations
	// ===============================================================
	
	private char[][] b;
	private int hvalue;
	private ArrayList<int[]> avaPosition;
	
	
	// ===============================================================
	// =						Constructors
	// ===============================================================
	
	public MyAI (Board board)
	{
		b = board.getBoard();
		hvalue = 0;
		avaPosition = new ArrayList<int[]> ();
		updatePosition();
	}

	
	// ===============================================================
	// =						Public Function
	// ===============================================================
	
	public void updatePosition()
	{
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				if (b[c][r] == ' ')
					avaPosition.add(getPosNum(r,c));
			}
		}
	}
	
	public int[] getAction() {
		int[] result = new int[2];
		int max = -100000;
		
		for (int i = 0; i < avaPosition.size(); i++) {
			int rowForX = avaPosition.get(i)[0];
			int colForX = avaPosition.get(i)[1];
			int m = minValue( applyX(b, rowForX, colForX), rowForX, colForX );
			if (m > max) {
				max = m;
				result[0] = rowForX;
				result[1] = colForX;
			}
		}
		
		return result;
		
	}
	
	
	// ===============================================================
	// =						Help Functions
	// ===============================================================
	
	private int minValue(char[][] board, int rX, int cX)
	{
		int min = 100000;
		for (int i = 0; i < avaPosition.size(); i++) {
			int rowForO = avaPosition.get(i)[0];
			int colForO = avaPosition.get(i)[1];
				if (board[colForO][rowForO] != 'X') {
					int m = countM( applyO(board, rowForO, colForO) );
					if (m < min) {
						min = m;
					}
				}
		}
		return min;
	}
	
	private char[][] applyX(char[][] board, int rX, int cX)
	{
		char[][] result = new char[3][3];
		for (int r = 0; r < 3; r ++)
			for (int c = 0; c < 3; c ++)
				result[c][r] = board[c][r];
		result[cX][rX] = 'X';
		return result;
	}
	
	private char[][] applyO(char[][] board, int rO, int cO)
	{
		char[][] result = new char[3][3];
		for (int r = 0; r < 3; r ++)
			for (int c = 0; c < 3; c ++)
				result[c][r] = board[c][r];
		result[cO][rO] = 'O';	
		return result;
	}
	
	private int countM(char[][] board)
	{
		int O = 0;
		int X = 0;
		
		X = 		check(board, 0, 0, 0, 1, 0, 2)[0] + 
				check(board, 1, 0, 1, 1, 1, 2)[0] + 
				check(board, 2, 0, 2, 1, 2, 2)[0] +
				check(board, 0, 0, 1, 0, 2, 0)[0] +
				check(board, 0, 1, 1, 1, 2, 1)[0] +
				check(board, 0, 2, 1, 2, 2, 2)[0] +
				check(board, 0, 0, 1, 1, 2, 2)[0] +
				check(board, 0, 2, 1, 1, 2, 0)[0];
		
		O = 		check(board, 0, 0, 0, 1, 0, 2)[1] + 
				check(board, 1, 0, 1, 1, 1, 2)[1] + 
				check(board, 2, 0, 2, 1, 2, 2)[1] +
				check(board, 0, 0, 1, 0, 2, 0)[1] +
				check(board, 0, 1, 1, 1, 2, 1)[1] +
				check(board, 0, 2, 1, 2, 2, 2)[1] +
				check(board, 0, 0, 1, 1, 2, 2)[1] +
				check(board, 0, 2, 1, 1, 2, 0)[1];
		
		return X-O;
	}
	
	private int[] check(char[][] board, int d1, int t1, int d2, int t2, int d3, int t3)
	{
		int O = 0;
		int X = 0;
		int[] result = new int[2];
		
		if (board[d1][t1] == 'X') {
			if (board[d2][t2] == 'X' || board[d2][t2] == ' ') {
				if (board[d3][t3] != 'O')
					X += 1;
			}
		} else if (board[d1][t1] == 'O') {
			if (board[d2][t2] == 'O') {
				if (board[d3][t3] == 'O')
					O = 10000;
				else if (board[d3][t3] == ' ')
					O += 1;
			} else if (board[d2][t2] == ' ') {
				if (board[d3][t3] != 'X')
					O += 1;
			}
		} else if (board[d1][t1] == ' ') {
			if (board[d2][t2] == 'X') {
				if (board[d3][t3] != 'O')
					X += 1;
			} else if (board[d2][t2] == 'O') {
				if (board[d3][t3] != 'X')
					O += 1;
			} else if (board[d2][t2] == ' ') {
				if (board[d3][t3] == 'X')
					X += 1;
				else if (board[d3][t3] == 'O')
					O += 1;
				else if (board[d3][t3] == ' ') {
					X += 1;
					O += 1;
				}				
			}
		}
		
		result[0] = X;
		result[1] = O;
		return result;
	}
	
	private int[] getPosNum(int row, int col)
	{
		int[] result = new int[2];
		result[0] = row;
		result[1] = col;
		return result;
	}
}
