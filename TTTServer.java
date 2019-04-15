
public class TTTServer 
{
    public static void main(String[] args) throws IOException {
        
        if (args.length != 1) {
            System.err.println("Usage: java KnockKnockServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        try (
            //Listen on specific port for a connection request 
            ServerSocket serverSocket = new ServerSocket(portNumber);
            
            //Server accepts connection request
            Socket clientSocket = serverSocket.accept();
            
            //Shows output to client 
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
                
            //Recieve input from client
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        ) {
        
            String inputLine, outputLine;
            
            // Initiate conversation with client
            TTTProtocol ttt = new TTTProtocol();
            outputLine = ttt.processInput(null);
            
            //Send initial message to client 
            out.println(outputLine);
            
            //Receive response from client
            while ((inputLine = in.readLine()) != null) {
                //Determine server's reply
                outputLine = ttt.processInput(inputLine);
                //Sending server's reply to the client
                out.println(outputLine);
                
                //Repeat steps until break condition 
                if (outputLine.equals("Bye."))
                    break;
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
