
public class Message implements java.io.Serializable
{
    private String message;
    private GameBoard board;
    
    public Message(String theMessage, GameBoard theBoard){
        this.message = theMessage;
        this.board = theBoard;
    }
    
    public String toString(){
        String results = message + "\n";
        results += this.board.toString();
        return results;
    }
}
