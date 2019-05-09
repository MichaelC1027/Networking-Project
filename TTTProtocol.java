import java.io.*;
import java.net.*;
import java.util.Scanner;
public class TTTProtocol
{
    //final variable used for checking if client or user attempts 
    //to use same spot on board
    static final String notValid= "Invalid entry";
    
    //translate a message to a string 
    public static String interpretMessage(Message message){
        return message.toString();
    }

    //establish how to handle client input
    public static ClientMessage interpretClientInput(String messageString){
        //check if the message equals exit, if so, then we do not make a vector 2
        if(messageString.equals("exit")){
            Scanner scan = new Scanner(messageString);
            Vector2 theMove = new Vector2(0,0);//sends default move to fill parameters
            return new ClientMessage(messageString,theMove);
        }

        //check if the message is properly formatted: x,y
        if(messageString.matches("[1-3],[1-3]")){
            Scanner scan = new Scanner(messageString);
            int positionOfComma = messageString.indexOf (',');
            String x = messageString.substring(0, positionOfComma);
            String y = messageString.substring(positionOfComma + 1, messageString.length());
            int row = Integer.parseInt(x) - 1;// Casting string of x
            int col = Integer.parseInt(y) - 1;// Casting string of y
            Vector2 theMove =  new Vector2(row,col);
            return new ClientMessage(messageString,theMove);
        }else{
            //if not, return null
            return null;
        }
    }

    //establish how to handle server input
    public static Vector2 interpretServerInput(String messageString){
        //check if the message is properly formatted: x,y
        if(messageString.matches("[1-3],[1-3]")){
            Scanner scan = new Scanner(messageString);
            int positionOfComma = messageString.indexOf (',');
            String x = messageString.substring(0, positionOfComma);
            String y = messageString.substring(positionOfComma + 1, messageString.length());
            int row = Integer.parseInt(x) - 1;// Casting string of x
            int col = Integer.parseInt(y) - 1;// Casting string of y
            Vector2 theMove =  new Vector2(row,col);
            return theMove;
        }else{
            //if not, return null
            return null;
        }        
    }

    //Interpret client message, send back a String
    public static String interpretClientMessage(ClientMessage message, GameBoard board,String symbol){
        String results = message.getMessage();
        //if the message is not exit, apply a move to the board
        if(!results.equals("exit")){
            //create a status variable to store status established in applyMove
            int status = board.applyMove(message.getMove(), symbol);
            
            //If the input is no good 
            if(status == GameBoard.BAD_INPUT){
                message.setMessage(notValid);
            }

            //check status of game, and change results based on status
            if(status == GameBoard.DRAW){
                //if draw, print that is was a draw
                message.setMessage("It's a Draw!");
            }   

            if(status == GameBoard.WIN){
                //if done, print winner
                message.setMessage("The Client won!");
            }
            
        }
        
        // for future, create special object that stores the int status and string results
        
        return message.getMessage();
    }    
}

