public class PointChess {

    private int[][] board;
    private int playerTurn=-1;




public class validMove{

    private static final int pawn=1;
    private static final int rook=2;
    private static final int knight=3;
    private static final int bishop=4;
    private static final int queen=5;
    private static final int king=6;


    /**
     * @param start
     * @param end
     * @param piece
     * @return
     */
    public static boolean validCheck(String start, String end, int piece){
        
        int startCol=((int)start.charAt(0))-97;
        int startRow=Integer.parseInt(start.substring(1))-1;

        int endCol=((int)end.charAt(0))-97;
        int endRow=Integer.parseInt(end.substring(1))-1;

        if(!boundaryCheck(startCol, startRow, endCol, endRow)) {
            return false;
        }

        System.out.println(pos1);
        System.out.println(pos2);

        switch(piece){
            case 1:
                return pawnMove(start,end);
            
            case 2:
                return rookMove(start,end);

            case 3:

                return knightMove(start,end);
            
            case 4:

                return bishopMove(start,end);

            case 5:

                return queenMove(start,end);
            
            case 6:

                return kingMove(start,end);
        }

    }


    private static boolean boundaryCheck(int startRow, int startCol, int endRow, int endCol){
        if(startRow>7 || startCol>7 || endRow>7 || endCol>7){
            return false;
        }
        if(startRow<0 || startCol<0 || endRow<0 || endCol<0){
            return false;
        }

        return true;
    }



    private static boolean pawnMove(String letter, String num, String end) {
        
        

        return true;
    }


    private static boolean kingMove(int stRow, int stCol, int endRow, int endCol){


        
        
    }



}







    
}
