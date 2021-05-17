package educanet;

import java.util.ArrayList;
import java.util.Collections;

public class Board {

    // X Y playerID
    private static ArrayList<int[]> boardXY = new ArrayList<>();

    // X playerID
    private static ArrayList<int[]> boardX = new ArrayList<>();

    // Y playerID
    private static ArrayList<int[]> boardY = new ArrayList<>();

    //dynamic array changed every play
    private static int[][] boardArray;

    //i want to "transform" [0;0] to the middle of the array I create later
    public static void play(int playerID, int X, int Y) {
        //not doing cuz im running out of memory
        //X += Integer.MAX_VALUE/2;
        //Y += Integer.MAX_VALUE/2;
        boardXY.add(new int[]{X,Y,playerID});
        boardX.add(new int[]{X,playerID});
        boardY.add(new int[]{Y,playerID});

        boardArray = createArray();

    }

    //converts arrayLists to an array dynamically
    public static int[][] createArray() {
      int[][] board = new int[getMaxY()+1][getMaxX()+1]; //makes an array with the sizing of the biggest number inside the corresponding ArrayList


      for (int[] element : boardXY) {
          int x = element[0];
          int y = element[1];
          int playerID = element[2];

          board[y][x] = playerID;
      }

      return board;
    };


    //returns the biggest X position from the BoardX ArrayList  || probably could be done more resourcefully
    public static int getMaxX() {
        ArrayList<Integer> xPos = new ArrayList<>();

        boardX.forEach(e -> xPos.add(e[0]));

        return Collections.max(xPos);
    }

    //returns the biggest Y position from the BoardY ArrayList || probably could be done more resourcefully
    public static int getMaxY() {
        ArrayList<Integer> yPos = new ArrayList<>();

        boardY.forEach(e -> yPos.add(e[0]));

        return Collections.max(yPos);
    }


    public static int[][] getBoardArray() {
        return boardArray;
    }


}
