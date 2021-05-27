package educanet.Logic;


import java.util.ArrayList;

public class Board {

    //------------- VARIABLES --------------------

    // [X, Y, playerID]
    private final ArrayList<String[]> boardXY = new ArrayList<>();
    private String[][] boardArray;

    //--------------------------------------------
    //                  METHODS

    public void play(String playerID, String X, String Y) {
        boardXY.add(new String[]{X,Y,playerID});
    }

    public ArrayList<String[]> getBoardList() {
        return boardXY;
    }

//------------------------------------------------
//                  DO NOT USE

    public String[][] getBoardArray() {
        return boardArray;
    }




}
