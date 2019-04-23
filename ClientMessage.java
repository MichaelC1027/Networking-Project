
public class ClientMessage implements java.io.Serializable
{
    private String message;
    private Vector2 move;
    
    public ClientMessage(String theMessage, Vector2 theMove){
        this.message = theMessage;
        this.move = theMove;
    }
    
    public String getMessage(){
        return this.message;
    }
    
    public void setMessage(String theMessage){
        this.message = theMessage;
    }
    
    public Vector2 getMove(){
        return this.move;
    }
    
    public void setMove(Vector2 theMove){
        this.move = theMove;
    }
    
    public String toString(){
        String results = message + "\n";
        results += this.move.toString();
        return results;
    }
}
