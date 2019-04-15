import java.net.*;
import java.io.*;

public class TTTClient 
{
    public static void main(String[] args) throws IOException {
        
        if (args.length != 2) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
            //Initiate a connection request to server's IPaddress, port 
            Socket tttSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(tttSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(tttSocket.getInputStream()));
        ) {
            BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;
            
            //Receive message for server
            while ((fromServer = in.readLine()) != null) {
                //Print message from server
                System.out.println("Server: " + fromServer);
                
                //Repeat steps until break condition 
                if (fromServer.equals("Bye."))
                    break;
                
                //Read message from client 
                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    //Send response to server 
                    out.println(fromUser);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        }
    }
}
