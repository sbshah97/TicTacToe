import java.util.*;
import java.lang.*;

public class TicTacToe {
	/**
	*
	*/
	public static final int EMPTY = 0;
	public static final int CROSS = 1;
	public static final int OH = 2;

	/**
	*
	*/
	public static final int PLAYING = 0 ;
	public static final int DRAW = 1;
	public static final int CROSS_WON = 2;
	public static final int OH_WON = 3;

	/**
	*
	*/
	public static final int ROWS = 3, COLUMNS = 3;
	public static int[][] board = new int[ROWS][COLUMNS];
	public static int currentState, currentRow, currentColumn, currentPlayer;
	static Scanner scan;

	/**
	*
	*/
	public static void main(String args[]) {
		scan = new Scanner(System.in);
		//Initialise Game
		initGame();

		//Iterate around a while loop to 
		//play the game once
		do {

			playerMove(currentPlayer);
			
			updatePlayer(currentPlayer, currentRow, currentColumn);
			
			printBoard();

			winStatus(currentState);

			currentPlayer = (currentPlayer == CROSS) ? OH:CROSS;
		
		}while(currentState == PLAYING);
	}
		
	public static void initGame() {
		for(int i = 0 ; i < ROWS ; i ++)
			for(int j = 0 ; j < COLUMNS; j ++)
				board[i][j] = EMPTY;	

		currentState = PLAYING;
		currentPlayer = CROSS;	
	}

	public static void playerMove(int currentPlayer) {
		System.out.println("Enter your choice between 1- 3");
		int row = scan.nextInt() - 1;
		int column = scan.nextInt() - 1;

		board[row][column] = currentPlayer;

	} 

	public static void updatePlayer (int currentPlayer, int currentRow, int currentColumn) {
		if(winGame(currentPlayer, currentRow, currentColumn)) 
			currentState = (currentPlayer == CROSS) ? CROSS_WON : OH_WON;
		else if(drawGame()) 
			currentState = DRAW;			
	}

	public static boolean winGame (int currentPlayer, int currentRow, int currentColumn) {
		return (board[currentRow][0] == currentPlayer         
               && board[currentRow][1] == currentPlayer
               && board[currentRow][2] == currentPlayer
          || board[0][currentColumn] == currentPlayer      
               && board[1][currentColumn] == currentPlayer
               && board[2][currentColumn] == currentPlayer
          || currentRow == currentColumn            
               && board[0][0] == currentPlayer
               && board[1][1] == currentPlayer
               && board[2][2] == currentPlayer
          || currentRow + currentColumn == 2  
               && board[0][2] == currentPlayer
               && board[1][1] == currentPlayer
               && board[2][0] == currentPlayer);
	}

	public static boolean drawGame () {
		for (int i = 0 ; i < ROWS ; i ++) {
			for (int j = 0; j < COLUMNS ; j ++) {
				if(board[i][j] == EMPTY) {
					return false;
				}
			}
		}
		return true;
	}

	public static void printBoard () {
		for(int i = 0 ; i < ROWS ; i ++) {
            System.out.print("\t");
			for(int j = 0 ; j < COLUMNS ; j ++) {
				printCell(board[i][j]);
				if(j != COLUMNS - 1)
					System.out.print("| ");
			}
			System.out.println();
		}
	}

	public static void printCell(int x) {
		switch (x) {
			case EMPTY:
				System.out.print("- ");
				break;
			case CROSS:
				System.out.print("X ");
				break;
			case OH:
				System.out.print("O ");
				break;
		}
	}

	public static void winStatus (int currentState) {
		if(currentState == CROSS_WON)
			System.out.println("Cross has won the game");

		else if(currentState == OH_WON)
			System.out.println("Oh has won the game");

		else if(currentState == DRAW)
			System.out.println("The game is drawn");
	}


	

}














