import java.util.Scanner;

public class TTTGame 
{
	//I feel we should use this for only board related methods whle protocol handles the players turns
	
	
	//the variables we will need 
	static boolean win  = false;
	static String winner = "";
	static String symbol = ""; //symbol is basically "X" and "O"
	static Scanner scan = new Scanner(System.in);
	static String input; //we will take the input as a string then make it into ints
	
	//the small tic-tac-toe board 
	static String[][] smallBoard =
	{
		{"","",""},
		{"","",""},
		{"","",""}
	};
	
	//the big tic-tac-toe board
	static String[][] bigBoard = 
	{
		{"","",""},
		{"","",""},
		{"","",""}
	};
	
	//this is where we will print the board
	public static void PrintBoard()
	{
		System.out.println();
		System.out.println("_" + smallBoard[0][0] + "_|_" + smallBoard[0][1] + "_|_" + smallBoard[0][2] + "_ \n" 
						 + "_" + smallBoard[0][0] + "_|_" + smallBoard[0][1] + "_|_" + smallBoard[0][2] + "_ \n"
						 + "_" + smallBoard[0][0] + "_|_" + smallBoard[0][1] + "_|_" + smallBoard[0][2] + "_");
		System.out.println();
	}
	
	//this method will clear the small boards 
	//TODO: Make the clear method run into a 'for' loop for all the board when we get the big board working 
	public static void SBClear()
	{
		for(int i = 0; i < smallBoard.length; i++)
		{
			for(int j = 0; j < smallBoard.length; j++)
			{
				smallBoard[i][j] = "";
			}
		}
	}
	
	//this method will clear the big board
	//
	public static void BBClear()
	{
		for(int i = 0; i < bigBoard.length; i++)
		{
			for(int j = 0; j < bigBoard.length; j++)
			{
				bigBoard[i][j] = "";
			}
		}
	}
	
	//TODO: Make a PlayGame Method for the smaller boards
	public static void SBPlayGame()
	{
		
	}
	
	//TODO: Make a PlayGame Method for the big board
	public static void BBPlayGame()
	{
		
	}
	
	//this method will Check if you win on the small board
	//TODO: Make this Method better for the server
	public static void SBCheckWin()
	{
		
	}
	
	//this method will Check if you win on the big board
	//TODO: Make this Method better for the server
	public static void BBCheckWin()
	{
			
	}
	
	
}
