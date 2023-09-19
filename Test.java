import java.util.Scanner;
public class Test {




    public static void main(String args[]){
        Scanner next= new Scanner(System.in);
        PointChess testGame= new PointChess();
        String response;
        int player=-1;
        int returns=5;

        while(returns!=-1){
            System.out.println("Enter valid move, separating positions by comma.");
            try{
                response=next.next();
                String[] vals=response.split(",");
                String init=vals[0];
                String dest=vals[1];
                returns=testGame.validCheck(init, dest);
                player*=-1;
            } catch (Exception e){
                System.out.println("Invalid! Try again. ");
            }
            
        }

        if(player>0){
            System.out.println("Congrats! Black wins!");
        }else{
            System.out.println("Congrats! White wins!");
        }



        // System.out.println(testGame.validCheck("a2","a3"));
        // System.out.println(testGame.validCheck("d7","d5"));
        // System.out.println(testGame.validCheck("d2","d4"));
        // System.out.println(testGame.validCheck("d5","d4"));
        // System.out.println(testGame.validCheck("c7","c5"));
        // System.out.println(testGame.validCheck("d4","c5"));
        // System.out.println(testGame.validCheck("b7","b5"));
        // System.out.println(testGame.validCheck("c5","b6"));
        // System.out.println(testGame.validCheck("a7","a6"));
        // System.out.println(testGame.validCheck("b6","b7"));
        // System.out.println(testGame.validCheck("a6","a5"));
        // System.out.println(testGame.validCheck("b7","c8"));

        // System.out.println(testGame.validCheck("h7","h6"));
        // System.out.println(testGame.validCheck("e2","e4"));
        // System.out.println(testGame.validCheck("h6","h5"));
        // System.out.println(testGame.validCheck("e4","e5"));
        // System.out.println(testGame.validCheck("h5","h4"));
        // System.out.println(testGame.validCheck("e5","e6"));
        // System.out.println(testGame.validCheck("h4","h3"));
        // System.out.println(testGame.validCheck("e6","f7"));
        // System.out.println(testGame.validCheck("h3","g2"));
        // System.out.println(testGame.validCheck("c2","c4"));
        // System.out.println(testGame.validCheck("g2","h1"));
        // System.out.println(testGame.validCheck("f7","e8"));

    }
    
}
