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
            if(serverMove == null || !input.matches("[1-3],[1-3]")){
                System.out.print("Bad input, please try again: ");
            }
        }while(serverMove == null);

        //apply move to the board
        board.applyMove(serverMove,serverSymbol);
        board.display();

        //send out server object 
        fromServer = new Message(serverMove.toString(), board);
        out.writeObject(fromServer);
        out.flush();

        while((fromUser = (ClientMessage)in.readObject()) != null) {
            //interpret the incoming message
            String results = TTTProtocol.interpretClientMessage(fromUser,board,clientSymbol);

            //display results of interpretation
            System.out.println(results);

            //If the input is no good 
            if(results.equals(TTTProtocol.notValid)){
                fromServer = new Message(TTTProtocol.notValid, board.copyBoard());
                out.writeObject(fromServer);
                out.flush();
            }else{

                //check the board for a done according to the client
                if(results.equals("It's a Draw!")){
                    out.writeObject(null);
                    System.out.println("It's a Draw!");
                    break;
                }

                if(results.equals("The Client won!")){
                    out.writeObject(null);
                    System.out.println("The Client won!");
                    break;
                }

                //establish break condition
                if(results.equals("exit")){
                    break;
                }

                //being here means that we need to make a move after client
                board.display();

                // Make a move prompt
                System.out.print("Please make a move: ");

                //since status of board is continue we make another move 
                do{
                    input = scan.nextLine();
                    serverMove = TTTProtocol.interpretServerInput(input);
                    if(serverMove == null || !input.matches("[1-3],[1-3]")){
                        System.out.print("Bad input, please try again: ");
                    }
                }while(serverMove == null);            

                //apply move to the board
                int serverResults = board.applyMove(serverMove,serverSymbol);

                //update message for new board
                fromServer = new Message(serverMove.toString(), board.copyBoard());

                //print the board
                System.out.println(fromServer);

                //If the input is no good 
                if(serverResults == GameBoard.BAD_INPUT){
                    while(serverResults == GameBoard.BAD_INPUT){ 
                        System.out.print("Bad input, please try again: ");
                        do{
                            input = scan.nextLine();
                            serverMove = TTTProtocol.interpretServerInput(input);
                            if(serverMove == null || !input.matches("[1-3],[1-3]")){
                                System.out.print("Bad input, please try again: ");
                            }
                        }while(serverMove == null);            

                        //apply move to the board
                        serverResults = board.applyMove(serverMove,serverSymbol);

                        //update message for new board
                        fromServer = new Message(serverMove.toString(), board.copyBoard());

                        //print the board
                        System.out.println(fromServer);
                    }
                }

                //check status of game, and change results based on status
                if(serverResults == GameBoard.DRAW){
                    System.out.println("It's a Draw!");
                    out.writeObject(null);
                    break;
                }   

                if(serverResults == GameBoard.WIN){
                    System.out.println("The Server won!");
                    out.writeObject(null);
                    break;
                }            

                //send out server object
                out.writeObject(fromServer);
                out.flush();
            }
        }
        serverSocket.close();
        clientSocket.close();
    }
}
