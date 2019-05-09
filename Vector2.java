/**
 * Vector2 - stores two pieces of information, as ints for right now
 * 
 * NOTE that this class is designed to be used in a tree, and moves will not be written
 * to the tree unless there is no move in the tree with the same x and y.
 * @author Michael Pascale, Modified by Sean Santorufo
 * @version 31 Jan 2019
 */
public class Vector2 implements Comparable<Vector2>, java.io.Serializable{
    //two pieces of data being stored
    private int x, y;

    /**
     * default constructor - only sets x and y information
     * 
     * @param x,y -  two pieces of data
     */
    public Vector2(int x, int y){
        this.x = x;
        this.y = y;
    }

    
    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setX(int newX){
        this.x = newX;
    }

    public void setY(int newY){
        this.y = newY;
    }


    /**
     * equals method
     * 
     * @param that, another Vector2, inputted as an Object
     * @return true if they contain the same values, false otherwise
     */
    public boolean equals(Vector2 that){
        return (this.x == that.x)&&(this.y == that.y);
    }

    /**
     * compareTo
     * 
     * @param that, another Vector2
     * @return 0 if the two Vector2 objects are the same, 1 otherwise
     */
    public int compareTo(Vector2 that){
        return (this.equals(that)) ? 0 : 1;
    }

    /**
     * toString
     * 
     * @return a string with formatted data
     */
    public String toString(){
        return "(" +x+","+y+")";
    }
}