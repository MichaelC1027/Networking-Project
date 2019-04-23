import java.io.*;
import java.net.*;
import java.util.Scanner;
public class TTTProtocol
{
    
    
    public static String interpretMessage(Message message){
        return message.toString();
    }


    public static ClientMessage interpretClientInput(String messageString){
        //check if the message equals exit, if so, then we do not make a vector 2
        if(messageString.equals("exit")){
            System.out.println("no new vector");
        }
        //check if the message is properly formatted: x,y
        if(messageString.matches("[1-3].,[1-3].")){
            Scanner scan = new Scanner(messageString);
            int positionOfComma = messageString.indexOf (',');
            String x = messageString.substring(0, positionOfComma);
            String y = messageString.substring(positionOfComma + 1, messageString.length());
            int row = Integer.parseInt(x);// Casting string of x
            int col = Integer.parseInt(y);// Casting string of y
            Vector2 theMove =  new Vector2(row,col);
            return new ClientMessage(messageString,theMove);
        }else{
            //if not, return null
            return null;
        }

    }
    
    
    public static Vector2 interpretServerInput(String messageString){
        //check if the message is properly formatted: x,y
        if(messageString.matches("[1-3].,[1-3].")){
            Scanner scan = new Scanner(messageString);
            int positionOfComma = messageString.indexOf (',');
            String x = messageString.substring(0, positionOfComma);
            String y = messageString.substring(positionOfComma + 1, messageString.length());
            int row = Integer.parseInt(x);// Casting string of x
            int col = Integer.parseInt(y);// Casting string of y
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
            board.applyMove(message.getMove(), symbol);
        }
        return results;
    }
    
}
