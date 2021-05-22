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

    //returns the contents of the position, if the position hasn't been played yet, returns null
    public static int[] findPos(int x, int y) {
        for (int[] position : boardXY) {
            if(position[0] == x && position[1] == y) {
                return position;
            }
        }
        return null;
    }

    //returns the biggest X position from the BoardX ArrayList  || probably could be done more resourcefully
    public static int getMaxX(ArrayList<int[]> board) {
        if(board == null) return 0;

        ArrayList<Integer> xPos = new ArrayList<>();

        board.forEach(e -> xPos.add(e[0]));

        return Collections.max(xPos);
    }

    //returns the biggest Y position from the BoardY ArrayList || probably could be done more resourcefully
    public static int getMaxY(ArrayList<int[]> board) {
        if(board == null) return 0;

        ArrayList<Integer> yPos = new ArrayList<>();

        board.forEach(e -> yPos.add(e[1]));

        return Collections.max(yPos);
    }

    public static ArrayList<int[]> getBoardList() {
        return boardXY;
    }

//------------------------------------------------
//                  DO NOT USE

    public static int[][] getBoardArray() {
        return boardArray;
    }

    // converts arrayLists to an array dynamically
    public static int[][] convertToArray(ArrayList<int[]> boardList) {
        int[][] board = new int[getMaxY(boardList)+1][getMaxX(boardList)+1]; //makes an array with the sizing of the biggest number inside the corresponding ArrayList

        for (int[] element : boardXY) {
            int x = element[0];
            int y = element[1];
            int playerID = element[2];

            board[y][x] = playerID;
        }

        return board;
    }

}
