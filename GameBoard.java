import java.util.Arrays;
/**
 * Stores a 2d array of strings which is the game board
 * detects wins
 * 
 * @author Michael Pascale, Modified by Sean Santorufo
 * @version 5 Feb 2019
 */
public class GameBoard implements java.io.Serializable{
    public static final String EMPTY = "_";
    public static final String X = "X";
    public static final String O = "O";
    
    public static final int WIN = 1;
    public static final int DRAW = 0;
    public static final int BAD_INPUT = -2;
    public static final int CONTINUE = -1;
    
    private int moveCount;
    
    private String[][] board;
    /**
     * constructor, initialize board
     */
    public GameBoard(){
        board = new String[3][3];
        //format [x][y]
        
        moveCount = 0;
        
        //initialize the contents of the board
        for(int i = 0; i < board.length;i++){
            for(int j = 0; j < board[i].length;j++){
                board[i][j] = EMPTY;
            }
        }
    }
    
    /**
     * display - shows the board in a way that is easy to see
     */
    public void display(){
        System.out.println("\n  1|2|3");
        System.out.println("1|"+ board[0][0]+"|"+board[1][0]+"|"+board[2][0]);
        System.out.println("2|"+ board[0][1]+"|"+board[1][1]+"|"+board[2][1]);
        System.out.println("3|"+ board[0][2]+"|"+board[1][2]+"|"+board[2][2]);
    }
    
    public String toString(){
        String results = "\n  1|2|3\n";
        results += "1|"+ board[0][0]+"|" + board[1][0] + "|" + board[2][0];
        results += "\n" + "2|"+ board[0][1]+"|"+board[1][1]+"|"+board[2][1];
        results += "\n" + "3|"+ board[0][2]+"|"+board[1][2]+"|"+board[2][2];
        return results;
    }
    
    /**
     * apply move - put move on screen, and detect if there has been a win
     * or a draw
     * 
     * @return 0 if the game is a draw, 1 if a win, and -1 if the game is still on
     * Also could return -2 if input was incorrect
     */
    public int applyMove(Vector2 move, String s){
        int status;
        //check if input may be out of bounds
        if(move.getX() < 0 || move.getY() < 0){
            status = GameBoard.BAD_INPUT;
            return status;
        }
        if(board[move.getX()][move.getY()].equals(EMPTY)){
            board[move.getX()][move.getY()] = s;
            moveCount++;
        }else{
            return GameBoard.BAD_INPUT;
        }
                
        //check for win condition
        //vertical
        for(int i = 0; i < board.length;i++){
            //vertical three in a row
            if(board[i][0].equals(s) && board[i][1].equals(s) && board[i][2].equals(s)){
                status = GameBoard.WIN;
                return status;
            }
            //horizontal three in a row
            if(board[0][i].equals(s) && board[1][i].equals(s) && board[2][i].equals(s)){
                status = GameBoard.WIN;
                return status;
            }
        }
        
        //check for diagonals
        if(board[0][0].equals(s) && board[1][1].equals(s) && board[2][2].equals(s)){
            status = GameBoard.WIN;
            return status;
        }
        if(board[0][2].equals(s) && board[1][1].equals(s) && board[2][0].equals(s)){
                status = GameBoard.WIN;
                return status;
        }
        
        //check for draws
        if(moveCount == 9){
            return GameBoard.DRAW;
        }
        
        
        //continue if no win or draw
        return GameBoard.CONTINUE;
    }
    
    public GameBoard copyBoard(){
        GameBoard theBoard = new GameBoard();
        for(int i = 0; i < theBoard.board.length; i++){
            for(int j = 0; j < theBoard.board[i].length;j++){
                theBoard.board[i][j] = this.board[i][j];
            }
        }
        
        theBoard.moveCount = this.moveCount;
        
        return theBoard;
    }
}