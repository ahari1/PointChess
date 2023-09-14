package Pieces;

public class Pawn extends Piece{

    public boolean twoSteps;
    
    //private 


    public Pawn(boolean color){
        
        this.points=1;
        this.color=color;
        twoSteps=true;        
    }
    
}
