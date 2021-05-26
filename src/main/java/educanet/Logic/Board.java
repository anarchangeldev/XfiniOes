package educanet.Logic;


import java.util.ArrayList;

public class Board {

    //------------- VARIABLES --------------------

    // [X, Y, playerID]
    private final ArrayList<long[]> boardXY = new ArrayList<>();
    private long[][] boardArray;

    //--------------------------------------------
    //                  METHODS

    public void play(long playerID, long X, long Y) {
        boardXY.add(new long[]{X,Y,playerID});
    }

    public ArrayList<long[]> getBoardList() {
        return boardXY;
    }

//------------------------------------------------
//                  DO NOT USE

    public long[][] getBoardArray() {
        return boardArray;
    }




}
