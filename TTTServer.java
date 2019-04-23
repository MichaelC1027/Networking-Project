import java.io.*;
import java.net.*;
import java.util.Scanner;
public class TTTServer
{
    // take in client message, send out regular message
    public static void main(String[] args) throws Exception{
        //socket objects
        ServerSocket serverSocket = new ServerSocket(4444);
        Socket clientSocket = serverSocket.accept();

        //Object Streams needed to send interpret and send messages 
        ObjectInputStream in = new ObjectInputStream(
                clientSocket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(
                clientSocket.getOutputStream());

        //message objects 
        Message fromServer;
        Vector2 serverMove;
        ClientMessage fromUser;
        
        Scanner scan = new Scanner(System.in);

        GameBoard board = new GameBoard();
        
        //Specify symbols
        String clientSymbol = GameBoard.O;
        String serverSymbol = GameBoard.X;
        
        //Establish teams, followed by the first move
        System.out.println("Server is X and Client is O!");
        board.display();
        System.out.println("Please make a move: ");
        String input;       

        do{
            input = scan.nextLine();
            serverMove = TTTProtocol.interpretServerInput(input);
            if(serverMove == null){
                System.out.print("Bad input, please try again: ");
            }
        }while(serverMove == null);
        
        //apply move to the board
        board.applyMove(serverMove,serverSymbol);
        board.display();
         
        fromServer = new Message(serverMove.toString(), board);
        out.writeObject(fromServer);
        
        while((fromUser = (ClientMessage)in.readObject()) != null) {
            System.out.println(TTTProtocol.interpretClientMessage(fromUser,board,clientSymbol));
            
            if(fromUser.getMessage().equals("exit")){
                break;
            }
            
            //check the board for a done game(check the state of the board)
            
            //if done, figure out what to do, otherwise make a move, and send it over
            
            //being here means that we need to make a move
            
        }
        clientSocket.close();
        serverSocket.close();
    }
}
