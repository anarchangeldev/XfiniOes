package educanet;

import java.util.ArrayList;

public class Render {
    //TODO REWORK
    public static void printBoard(ArrayList<int[]> board, String emptyChar) {
        for (int y = 0; y <= Board.getMaxY(board); y++) {
            for (int x = 0; x <= Board.getMaxX(board); x++) {

                int[] pos = Board.findPos(x, y);

                if(pos != null) System.out.print(pos[2] + " ");
                else            System.out.print(emptyChar);

            }
            System.out.println();
        }
    }

    public static void printBoardFromArray(int[][] board, String emptyChar) {
        for (int[] ints : board) {
            for (int content : ints) {

                if (content != 0) System.out.print(content + " ");
                else System.out.print(emptyChar);

            }
            System.out.println();
        }
    }

}
