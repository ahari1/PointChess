public class Test {




    public static void main(String args[]){

        PointChess testGame= new PointChess();


        System.out.println(testGame.validCheck("a2","a3",1));
        // System.out.println(testGame.validCheck("a7","a6",1));
        // System.out.println(testGame.validCheck("a3","a5",1));
        // System.out.println(testGame.validCheck("b2","b4",1));
        // System.out.println(testGame.validCheck("a6","a5",1));
        // System.out.println(testGame.validCheck("b4","a5",1));
        System.out.println(testGame.validCheck("d7","d5",1));
        System.out.println(testGame.validCheck("d2","d4",1));
        System.out.println(testGame.validCheck("d5","d4",1));
        System.out.println(testGame.validCheck("c7","c5",1));
        System.out.println(testGame.validCheck("d4","c5",1));
        System.out.println(testGame.validCheck("b7","b5",1));
        System.out.println(testGame.validCheck("c5","b6",1));
        System.out.println(testGame.validCheck("a7","a6",1));
        System.out.println(testGame.validCheck("b6","b7",1));
        System.out.println(testGame.validCheck("a6","a5",1));
        System.out.println(testGame.validCheck("b7","c8",1));

        System.out.println(testGame.validCheck("h7","h6",1));
        System.out.println(testGame.validCheck("e2","e4",1));
        System.out.println(testGame.validCheck("h6","h5",1));
        System.out.println(testGame.validCheck("e4","e5",1));
        System.out.println(testGame.validCheck("h5","h4",1));
        System.out.println(testGame.validCheck("e5","e6",1));
        System.out.println(testGame.validCheck("h4","h3",1));
        System.out.println(testGame.validCheck("e6","f7",1));
        System.out.println(testGame.validCheck("h3","g2",1));
        System.out.println(testGame.validCheck("c2","c4",1));
        System.out.println(testGame.validCheck("g2","h1",1));
        System.out.println(testGame.validCheck("f7","e8",1));

    }
    
}
