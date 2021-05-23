package educanet;


import java.util.ArrayList;
import java.util.Collections;

public class Board {

    //------------- VARIABLES --------------------

    // [X, Y, playerID]
    private static final ArrayList<int[]> boardXY = new ArrayList<>();
    private static int[][] boardArray;

    //--------------------------------------------
    //                  METHODS

    public static void play(int playerID, int X, int Y) {
        boardXY.add(new int[]{X,Y,playerID});
    }

    public static ArrayList<int[]> getBoardList() {
        return boardXY;
    }

//------------------------------------------------
//                  DO NOT USE

    public static int[][] getBoardArray() {
        return boardArray;
    }




}
