import java.util.*;
import Pieces.*;
public class PointChess {

    private Piece[][] boardP;
    private String[][] boardS;
    private int playerTurn=-1;//white is negative, black is positive
    private String enPassantAval;
    private Scanner console= new Scanner(System.in);


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

        //knight setup
        boardP[0][1]= new Knight(true);
        boardP[7][1]= new Knight(false);
        boardP[0][6]= new Knight(true);
        boardP[7][6]= new Knight(false);

        boardS[0][1]= "♞";
        boardS[7][1]= "♘";
        boardS[0][6]= "♞";
        boardS[7][6]= "♘";


        //bishop setup
        boardP[0][2]= new Bishop(true);
        boardP[7][2]= new Bishop(false);
        boardP[0][5]= new Bishop(true);
        boardP[7][5]= new Bishop(false);

        boardS[0][2]= "♝";
        boardS[7][2]= "♗";
        boardS[0][5]= "♝";
        boardS[7][5]= "♗";

        //queen+king setup

        boardP[0][3]= new Queen(true);
        boardP[7][3]= new Queen(false);
        boardP[0][4]= new King(true);
        boardP[7][4]= new King(false);

        boardS[0][3]= "♛";
        boardS[7][3]= "♕";
        boardS[0][4]= "♚";
        boardS[7][4]= "♔";



    }

    public void printBoard(){

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                System.out.print(boardS[i][j]+" ");
            }
            System.out.println();
        }
    }

    public int validCheck(String start, String end){



        int made=0;
        int startCol=((int)start.charAt(0))-97;
        int startRow=Math.abs(Integer.parseInt(start.substring(1))-8);

        int endCol=((int)end.charAt(0))-97;
        int endRow=Math.abs(Integer.parseInt(end.substring(1))-8);

        if(!boundaryCheck(startCol, startRow, endCol, endRow)) {
            return 0;
        }

        Piece pieces= boardP[startRow][startCol];
        if(pieces==null){
            return 0;
        }

        boolean currColor=pieces.color;

        if((currColor && playerTurn<0) || (!currColor && playerTurn>0)){
            System.out.println("It's not your turn!");
            return 0;
        }


        if(pieces instanceof Pawn){
            made= pawnMove(startRow,startCol, endRow, endCol);
        }else if(pieces instanceof King){
            made= kingMove(startRow, startCol, endRow, endCol);
        }else if(pieces instanceof Knight){
            made= knightMove(startRow, startCol, endRow, endCol);
        }else if(pieces instanceof Bishop){
            made= bishopMove(startRow, startCol, endRow, endCol);
        }else if(pieces instanceof Queen){
            made= queenMove(startRow, startCol, endRow, endCol);
        }else{
            made= rookMove(startRow, startCol, endRow, endCol);
        }

        if(made==1 ||made==-1){
            printBoard();
        }
        return made;


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

    private int pawnMove(int startRow, int startCol, int endRow, int endCol) {

        Pawn curr;
        if(!(boardP[startRow][startCol] instanceof Pawn)){
            return 0;
        } else{
            curr=(Pawn)boardP[startRow][startCol];
        }

        if(startRow==endRow && startCol== endCol){
            return 0;
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


                    /*
                     *
                     * TODO
                     * Check for promotion
                     *
                     *
                     */

                     if(endRow==0 || endRow==7){
                    String response="";
                    while(!response.equals("q") && !response.equals("r") && !response.equals("b") && !response.equals("k")){
                        System.out.println("What would you like to promote to? (Q)ueen, (R)ook, (K)night, (B)ishop");
                        response= console.next();
                        if(!response.equals("q") && !response.equals("r") && !response.equals("b") && !response.equals("k")){
                            System.out.println("Invalid input, please try again!");
                        }

                    }
                }
                } else{
                    return 0;
                }
                enPassantAval="";
            } else if(stepCount==2){

                if(curr.twoSteps && boardP[endRow][endCol]==null && boardP[endRow-playerTurn][endCol]==null){
                    boardP[endRow][endCol]=curr;
                    boardP[startRow][startCol]=null;
                    boardS[endRow][endCol]=boardS[startRow][startCol];
                    boardS[startRow][startCol]="-";
                    curr.twoSteps=false;
                    enPassantAval=Integer.toString(endRow)+","+Integer.toString(endCol);
                } else{
                    return 0;
                }



            }
        //capturing
        } else if ((playerTurn*(endRow-startRow))==1 && Math.abs(endCol-startCol)==1){

            //normal capture
            if(boardP[endRow][endCol]!=null){
                boolean currColor=curr.color;
                boolean captureColor=boardP[endRow][endCol].color;
                if(currColor==captureColor){
                    return 0;
                }
                Piece prev=boardP[endRow][endCol];
                boardP[endRow][endCol]=curr;
                boardP[startRow][startCol]=null;
                boardS[endRow][endCol]=boardS[startRow][startCol];
                boardS[startRow][startCol]="-";
                if(prev instanceof King){
                    return -1;
                }

                /* TODO
                 *
                 * Need to add promotion part
                 *
                 *
                 */


                if(endRow==0 || endRow==7){
                    String response="";
                    while(!response.equals("q") && !response.equals("r") && !response.equals("b") && !response.equals("k")){
                        System.out.println("What would you like to promote to? (Q)ueen, (R)ook, (K)night, (B)ishop");
                        response= console.next();
                        if(!response.equals("q") && !response.equals("r") && !response.equals("b") && !response.equals("k")){
                            System.out.println("Invalid input, please try again!");
                        }

                    }
                    boolean col=false;
                    if(playerTurn>0){
                        col=true;
                    }
                    if(response.equals("q")){
                        boardP[endRow][endCol]=new Queen(col);
                        if(playerTurn>0){
                            boardS[endRow][endCol]="♛";
                        } else{
                            boardS[endRow][endCol]="♕";
                        }

                    }else if (response.equals("r")){
                        boardP[endRow][endCol]=new Rook(col);
                        if(playerTurn>0){
                            boardS[endRow][endCol]="♜";
                        } else{
                            boardS[endRow][endCol]="♖";
                        }
                    }else if (response.equals("k")){
                        boardP[endRow][endCol]=new Knight(col);
                        if(playerTurn>0){
                            boardS[endRow][endCol]="♞";
                        } else{
                            boardS[endRow][endCol]="♘";
                        }
                    }else{
                        boardP[endRow][endCol]=new Bishop(col);
                        if(playerTurn>0){
                            boardS[endRow][endCol]="♝";
                        } else{
                            boardS[endRow][endCol]="♗";
                        }
                    }
                }
            //enpassant
            } else{

                //right capture
                if(endCol-startCol>0 && boardP[startRow][startCol+1] instanceof Pawn ){
                    int possRow=(int)enPassantAval.charAt(0)-48;
                    int possCol=(int)enPassantAval.charAt(2)-48;
                    if(possRow!=startRow || possCol-1!=startCol|| possCol!=endCol){
                        return 0;
                    }
                    boardP[endRow][endCol]=curr;
                    boardP[startRow][startCol]=null;
                    boardP[startRow][endCol]=null;
                    boardS[endRow][endCol]=boardS[startRow][startCol];
                    boardS[startRow][startCol]="-";
                    boardS[startRow][endCol]="-";

                //left capture
                }else if(endCol-startCol<0 && boardP[startRow][startCol-1] instanceof Pawn ){

                    int possRow=(int)enPassantAval.charAt(0)-48;
                    int possCol=(int)enPassantAval.charAt(2)-48;
                    if(possRow!=startRow || possCol+1!=startCol|| possCol!=endCol){
                        return 0;
                    }
                    boardP[endRow][endCol]=curr;
                    boardP[startRow][startCol]=null;
                    boardP[startRow][endCol]=null;
                    boardS[endRow][endCol]=boardS[startRow][startCol];
                    boardS[startRow][startCol]="-";
                    boardS[startRow][endCol]="-";

                } else{
                    return 0;
                }
            }

            enPassantAval="";
        }

        //capture
        playerTurn=playerTurn*-1;
        System.out.println("\n\n");
        return 1;
    }

    private int kingMove(int stRow, int stCol, int endRow, int endCol){

        King curr;
        Piece other=boardP[endRow][endCol];
        if(!(boardP[stRow][stCol] instanceof King)){
            return 0;
        } else{
            curr=(King)boardP[stRow][stCol];
        }
        if(Math.abs(stRow-endRow)>1|| Math.abs(stCol-endCol)>1 ||(other!=null &&
         curr.color==other.color || (stRow-endRow==0 && stCol-endCol==0))){
            return 0;
        }

        if(other instanceof King){
            return -1;
        }


        boardP[endRow][endCol]=curr;
        boardP[stRow][stCol]=null;
        boardS[endRow][endCol]=boardS[stRow][stCol];
        boardS[stRow][stCol]="-";
        enPassantAval="";
        playerTurn=playerTurn*-1;
        return 1;
    }

    private int knightMove(int startRow, int startCol, int endRow, int endCol){
        Knight curr;
        Piece other=boardP[endRow][endCol];

        if(!(boardP[startRow][startCol] instanceof Knight)){
            return 0;
        } else{
            curr=(Knight)boardP[startRow][startCol];
        }
        if(other!=null && other.color==curr.color){
            return 0;
        }
        int rowDiff=Math.abs(endRow-startRow);
        int colDiff=Math.abs(endCol-startCol);
        if(rowDiff>2 || colDiff>2 ||rowDiff==colDiff || rowDiff<1 || colDiff<1 ||
        (rowDiff==0 && colDiff==0)){
            return 0;
        }

        if(rowDiff==1){


            int totalPoints=0;

            int route1=0;
            int route2=0;

            Piece route1First=boardP[endRow][(startCol+endCol)/2];
            Piece route1Second=boardP[endRow][startCol];

            Piece route2First=boardP[startRow][(startCol+endCol)/2];
            Piece route2Second=boardP[startRow][endCol];

            if(route1First!=null){route1+=route1First.points;}
            if(route1Second!=null){route1+=route1First.points;}
            totalPoints=route1;
            if(route1>3){
                if(route2First!=null){
                    route2+=route2First.points;
                }
                if(route2Second!=null){
                    route2+=route2Second.points;
                }
                totalPoints=route2;
            }
            if(totalPoints>3){
                return 2;
            }else{

                boardP[endRow][endCol]=curr;
                boardS[endRow][endCol]=boardS[startRow][startCol];
                boardP[startRow][startCol]=null;
                boardS[startRow][startCol]="-";
            }

        }else{ //rowDiff==2


            /**
             *
             * toCheck:
             *
             *
             * [startCol][average of the two] and [startCol][endRow]
             *
             *
             */



             /*
              *
              the other way: [endCol][average of the two] and [endCol][startRow]


              */


            int totalPoints=0;

            int route1=0;
            int route2=0;

            Piece route1First=boardP[(endRow+startRow)/2][startCol];
            Piece route1Second=boardP[endRow][startCol];

            Piece route2First=boardP[(startRow+endRow)/2][endCol];
            Piece route2Second=boardP[startRow][endCol];

            if(route1First!=null){route1+=route1First.points;}
            if(route1Second!=null){route1+=route1First.points;}
            totalPoints=route1;
            if(route1>3){
                if(route2First!=null){
                    route2+=route2First.points;
                }
                if(route2Second!=null){
                    route2+=route2Second.points;
                }
                totalPoints=route2;
            }
            if(totalPoints>3){
                return 2;
            }else{

                boardP[endRow][endCol]=curr;
                boardS[endRow][endCol]=boardS[startRow][startCol];
                boardP[startRow][startCol]=null;
                boardS[startRow][startCol]="-";
            }



        }



        if(other instanceof King){
            return -1;
        }
        enPassantAval="";
        playerTurn*=-1;



        return 1;



    }

    private int rookMove(int startRow, int startCol, int endRow, int endCol){
        Rook curr;
        Piece other=boardP[endRow][endCol];
        if(!(boardP[startRow][startCol] instanceof Rook)){
            return 0;
        } else{
            curr=(Rook) boardP[startRow][startCol];
        }
        if(other!=null && other.color==curr.color){
            return 0;
        }
        int rowDiff=endRow-startRow;
        int colDiff=endCol-startCol;
        if((colDiff!=0 && rowDiff!=0) || (colDiff==0 && rowDiff==0)){
            return 0;
        } else if(colDiff!=0){ //horizontal movement
            int counter=1;
            int points=0;
            if(endCol<startCol){
                counter=-1;
            }
            for( int i=startCol; i!=endCol; i=i+counter){
                Piece currP= boardP[startRow][i];
                if(currP instanceof Pawn && currP.color==curr.color){
                    return 3;
                }
                if(currP!=null && currP.color!=curr.color){
                    points+=boardP[startRow][i].points;
                }
            }
            if(points>5){
                return 2;
            } else{
                boardP[endRow][endCol]=curr;
                boardP[startRow][startCol]=null;
                boardS[endRow][endCol]=boardS[startRow][startCol];
                boardS[startRow][startCol]="-";
            }

        } else{ //vertical movement
            int counter=1;
            int points=0;
            if(endRow<startRow){counter=-1;}
            for(int i=startRow;i!=endRow;i+=counter){
                Piece currP=boardP[i][startCol];
                if(currP instanceof Pawn && currP.color==curr.color){
                    return 3;
                } else if(currP!=null && currP.color!=curr.color){
                    points+=boardP[i][startCol].points;
                }
            }
            if(points>5){ return 2;} else{
                boardP[endRow][endCol]=curr;
                boardP[startRow][startCol]=null;
                boardS[endRow][endCol]=boardS[startRow][startCol];
                boardS[startRow][startCol]="-";
            }


        }
        if(other instanceof King){
            return -1;
        }
        playerTurn*=-1;
        return 1;
    }

    private int bishopMove(int startRow, int startCol, int endRow, int endCol){
        Bishop curr;
        Piece other=boardP[endRow][endCol];

        if(!(boardP[startRow][startCol] instanceof Bishop)){
            return 0;
        } else{
            curr=(Bishop) boardP[startRow][startCol];
        }
        if(other!=null && other.color==curr.color){
            return 0;
        }

        int rowChange= endRow-startRow;
        int colChange= endCol-startCol;

        int actRow=startRow;
        int actCol= startCol;

        if(Math.abs(rowChange)!=Math.abs(colChange)){
            return 0;
        }

        int rowCounter=0;
        int colCounter=0;

        if(rowChange>0){
            rowCounter++;
        } else{
            rowCounter--;
        }
        if (colChange>0){
            colCounter++;
        } else{
            colCounter--;
        }
        int totalPoints=0;
        while(startRow!=endRow && startCol!=endCol){
            if(boardP[startRow][startCol]!= null && boardP[startRow][startCol].color!=curr.color){
                totalPoints+=boardP[startRow][startCol].points;
            }
            if(boardP[startRow][startCol] instanceof Pawn && boardP[startRow][startCol].color==curr.color){
                return 3;
            }
            startRow+=rowCounter;
            startCol+=colCounter;
            if(totalPoints>curr.points){
                return 2;
            }
        }

        boardP[endRow][endCol]=curr;
        boardP[actRow][actCol]=null;
        boardS[endRow][endCol]=boardS[actRow][actCol];
        boardS[actRow][actCol]="-";


        if(other instanceof King){
            return -1;
        }
        playerTurn*=-1;
        return 1;
    }

    private int queenMove(int startRow, int startCol, int endRow, int endCol){

        Queen curr;
        Piece other=boardP[endRow][endCol];
        if(!(boardP[startRow][startCol] instanceof Queen)){
            return 0;
        } else{
            curr=(Queen) boardP[startRow][startCol];
        }

        if(other!=null && other.color==curr.color){
            return 0;
        }

        int rowChange= endRow-startRow;
        int colChange= endCol-startCol;

        int actRow=startRow;
        int actCol= startCol;
        if(Math.abs(rowChange)!=Math.abs(colChange)){
            if((rowChange!=0 && colChange!=0)    ){
                return 0;
            }
        }
        int rowCounter=0;
        int colCounter=0;

        if(rowChange>0){
            rowCounter++;
        } else if(rowChange<0){
            rowCounter--;
        }
        if (colChange>0){
            colCounter++;
        } else if(colChange<0){
            colCounter--;
        }
        int totalPoints=0;
        while(startRow!=endRow || startCol!=endCol){
            if(boardP[startRow][startCol]!= null && boardP[startRow][startCol].color!=curr.color){
                totalPoints+=boardP[startRow][startCol].points;
            }
            if(boardP[startRow][startCol] instanceof Pawn && boardP[startRow][startCol].color==curr.color){
                return 3;
            }
            startRow+=rowCounter;
            startCol+=colCounter;
            if(totalPoints>curr.points){
                return 2;
            }
        }

        boardP[endRow][endCol]=curr;
        boardP[actRow][actCol]=null;
        boardS[endRow][endCol]=boardS[actRow][actCol];
        boardS[actRow][actCol]="-";

        if(other instanceof King){
            return -1;
        }
        playerTurn*=-1;
        return 1;
    }

}
