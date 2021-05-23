package educanet;

import java.util.ArrayList;

public class Render {

    public static void showPlayers(ArrayList<Player> players) {

        System.out.println("------------------------\nPlayers:\n");
        for (Player player : players) {
            boolean isAI = player instanceof AI;
            if(isAI) System.out.println("AI "     + player.getID() + ": ");
            else     System.out.println("Player " + player.getID() + ": ");
            System.out.println("- name: " + player.getName());
            System.out.println("- symbol: " + player.getSymbol());
            if (isAI) System.out.println("- difficulty: " + ((AI) player).getDifficulty());
        }

    }

    public static void printChunk(ArrayList<int[]> board, int startX, int startY, int chunkSize, String emptyChar) {
        //TODO
    }

    public static void printWholeBoard(ArrayList<int[]> board, String emptyChar) {
        //theoretically pretty fucking heavy and big loop
        for (int y = Logic.getMinY(board); y <= Logic.getMaxY(board); y++) {
            for (int x = Logic.getMinX(board); x <= Logic.getMaxX(board); x++) {

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

}
