package educanet;

import java.util.ArrayList;
import java.util.Collections;

public class Logic {

    /**
     * returns 0 if no-one has won yet
     */
    public static int checkWin(ArrayList<Player> players) {
        //TODO



        return 0;
    }

    /**
     * returns the contents of the position, if the position hasn't been played yet, returns null
     */
    public static int[] findPos(int x, int y, ArrayList<int[]> board) {
        for (int[] position : board) {
            if(position[0] == x && position[1] == y) {
                return position;
            }
        }
        return null;
    }

    public static boolean legitMove(int x, int y, ArrayList<int[]> board) {
        return findPos(x, y, board) == null;
    }

    /**
     * returns 0 if pos is empty
     */
    public static int getPlayerID_AtPos(int x, int y, ArrayList<int[]> board) {
        int[] pos = findPos(x, y, board);

        if(pos != null) return pos[2];
        else            return 0;
    }

    /**
     * returns null if no player is found
     */
    public static Player getPlayer(ArrayList<Player> players, int targetID) {
        if(players == null) return null;

        for (Player player : players) {
            if(player.getID() == targetID) return player;
        }

        return null;
    }



    /**
     * returns the biggest X position from the ArrayList
     */
    public static int getMaxX(ArrayList<int[]> board) {
        if(board == null) return 0;

        ArrayList<Integer> xPos = new ArrayList<>();

        board.forEach(e -> xPos.add(e[0]));

        return Collections.max(xPos);
    }

    /**
     * returns the biggest Y position from the ArrayList
     */
    public static int getMaxY(ArrayList<int[]> board) {
        if(board == null) return 0;

        ArrayList<Integer> yPos = new ArrayList<>();

        board.forEach(e -> yPos.add(e[1]));

        return Collections.max(yPos);
    }

    /**
     * converts arrayLists to an array dynamically
     */
    public static int[][] convertToArray(ArrayList<int[]> boardList) {
        int[][] board = new int[getMaxY(boardList)+1][getMaxX(boardList)+1]; //makes an array with the sizing of the biggest number inside the corresponding ArrayList

        for (int[] element : boardList) {
            int x = element[0];
            int y = element[1];
            int playerID = element[2];

            board[y][x] = playerID;
        }

        return board;
    }




}
