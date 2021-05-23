package educanet;

import java.util.ArrayList;

public class Render {
    //TODO REWORK
    public static void printWholeBoard(ArrayList<int[]> board, String emptyChar) {
        for (int y = 0; y <= Logic.getMaxY(board); y++) {
            for (int x = 0; x <= Logic.getMaxX(board); x++) {

                int[] pos = Logic.findPos(x, y, board);

                if(pos != null) System.out.print(pos[2] + " ");
                else            System.out.print(emptyChar);

            }
            System.out.println();
        }
    }

    public static void printWholeBoard(int[][] board, String emptyChar) {
        for (int[] ints : board) {
            for (int content : ints) {

                if (content != 0) System.out.print(content + " ");
                else System.out.print(emptyChar);

            }
            System.out.println();
        }
    }

    public static void printChunk(ArrayList<int[]> board, int startX, int startY, int chunkSize, String emptyChar) {}



}
