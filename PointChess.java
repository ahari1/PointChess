public class PointChess {

    private int[][] board;




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
        
        int pos1=((int)start.charAt(0))-97;
        int pos2=Integer.parseInt(start.substring(1))-1;

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




    private static boolean pawnMove(String start, String end) {
        
        

        return true;
    }



}







    
}
