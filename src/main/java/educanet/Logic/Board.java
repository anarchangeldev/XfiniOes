package educanet.Logic;


import java.util.ArrayList;

public class Board {

    //------------- VARIABLES --------------------

    // [X, Y, playerID]
    private static final ArrayList<long[]> boardXY = new ArrayList<>();
    private static long[][] boardArray;

    //--------------------------------------------
    //                  METHODS

    public static void play(long playerID, long X, long Y) {
        boardXY.add(new long[]{X,Y,playerID});
    }

    public static ArrayList<long[]> getBoardList() {
        return boardXY;
    }

//------------------------------------------------
//                  DO NOT USE

    public static long[][] getBoardArray() {
        return boardArray;
    }




}
