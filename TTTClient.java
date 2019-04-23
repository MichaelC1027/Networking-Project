import java.io.*;
import java.net.*;
import java.util.Scanner;
public class TTTClient {
    public static void main(String[] args) throws Exception {

        String hostName = "localhost";
        int portNumber = 4444;

        //Initiate a connection request to server's IPaddress, port 
        Socket clientSocket = new Socket(hostName, portNumber);
        //Object Streams needed to send interpret and send messages 
        ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(
                clientSocket.getInputStream());

        //these will be the objects
        Message fromServer;
        ClientMessage fromUser;
        
        Scanner scan = new Scanner(System.in);
        
        //Receive message for server
        while ((fromServer = (Message)in.readObject()) != null) {
            //interpret the incoming message
            String results = TTTProtocol.interpretMessage(fromServer);
            
            //display results of interpretation
            System.out.println(results);
            
            System.out.print("Please make a move: ");
            //wait on user input
            do{
                String move = scan.nextLine();
                fromUser = TTTProtocol.interpretClientInput(move);
                if(fromUser == null){
                    System.out.print("Bad input, please try again: ");
                }
            }while(fromUser == null);
            
            //getting here means that the move was successfully instantiated
            
            //create a message from the input
            //send the message over
            out.writeObject(fromUser);
               
            //if the message is exit, then break out of the loop
            if(fromUser.getMessage().equals("exit")){
                break;
            }
            
        }
        
        clientSocket.close();
    }
}
