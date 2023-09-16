import java.util.*;
import Pieces.*;
public class PointChess {

    private Piece[][] boardP;
    private String[][] boardS;
    private int playerTurn=-1;//white is negative, black is positive





    public PointChess(){
        boardP= new Piece[8][8];
        boardS= new String[8][8];
        boardSetup();
        printBoard();


    }



    private void boardSetup(){

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                boardS[i][j]="-";
            }
        }
        
        for(int i=0;i<8;i++){
            boardP[1][i]= new Pawn(true);
            boardP[6][i]= new Pawn(false);

            boardS[1][i]="♟︎";
            boardS[6][i]="♙";
        }

        //Rook Setup
        boardP[0][0]= new Rook(true);
        boardP[7][0]= new Rook(false);
        boardP[0][7]= new Rook(true);
        boardP[7][7]= new Rook(false);

        boardS[0][0]= "♜";
        boardS[7][0]= "♖";
        boardS[0][7]= "♜";
        boardS[7][7]= "♖";





    }

    public void printBoard(){

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                System.out.print(boardS[i][j]+" ");
            }
            System.out.println();
        }
    }
// public class validMove {

    // private static final int pawn=1;
    // private static final int rook=2;
    // private static final int knight=3;
    // private static final int bishop=4;
    // private static final int queen=5;

    // private static final int king=6;


    public boolean validCheck(String start, String end, int piece){
        
        int startCol=((int)start.charAt(0))-97;
        int startRow=Math.abs(Integer.parseInt(start.substring(1))-8);
    
        int endCol=((int)end.charAt(0))-97;
        int endRow=Math.abs(Integer.parseInt(end.substring(1))-8);

        if(!boundaryCheck(startCol, startRow, endCol, endRow)) {
            return false;
        }


        switch(piece){
            case 1:
                boolean made= pawnMove(startRow,startCol, endRow, endCol);
                if(made){
                    printBoard();
                    return true;
                }
            
            // case 2:
            //     return rookMove(start,end);

            // case 3:

            //     return knightMove(start,end);
            
            // case 4:

            //     return bishopMove(start,end);

            // case 5:

            //     return queenMove(start,end);
            
            case 6:
                return true;//kingMove(start,end);
        }
        return false;

    
    }

    private boolean boundaryCheck(int startRow, int startCol, int endRow, int endCol){
        if(startRow>7 || startCol>7 || endRow>7 || endCol>7){
            return false;
        }
        if(startRow<0 || startCol<0 || endRow<0 || endCol<0){
            return false;
        }

        return true;
    }



    private boolean pawnMove(int startRow, int startCol, int endRow, int endCol) {
        
        Pawn curr;
        if(!(boardP[startRow][startCol] instanceof Pawn)){
            return false;
        } else{
            curr=(Pawn)boardP[startRow][startCol];
        }


        //linear
        if(playerTurn*(endCol-startCol)==0){
            int stepCount=playerTurn*(endRow-startRow);
            if(stepCount==1)
            {
                if(boardP[endRow][endCol]==null){
                    boardP[endRow][endCol]=curr;
                    boardP[startRow][startCol]=null;
                    boardS[endRow][endCol]=boardS[startRow][startCol];
                    boardS[startRow][startCol]="-";
                    curr.twoSteps=false;
                } else{
                    return false;
                }
            } else if(stepCount==2){

            }



        }

        //capture
        playerTurn=playerTurn*-1;
        System.out.println("\n\n");
        return true;
    }


    private boolean kingMove(int stRow, int stCol, int endRow, int endCol){


        return true;
        
    }



//}







    
}
