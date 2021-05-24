package educanet.Presentation;

import educanet.Logic.Logic;
import educanet.Player.*;

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

    public static void printChunk(ArrayList<long[]> board, long startX, long startY, long chunkSize, String emptyChar) {
        //TODO
    }

    public static void printWholeBoard(ArrayList<long[]> board, String emptyChar) {
        //theoretically pretty fucking heavy and big loop
        for (long y = Logic.getMinY(board); y <= Logic.getMaxY(board); y++) {
            for (long x = Logic.getMinX(board); x <= Logic.getMaxX(board); x++) {

                long[] pos = Logic.findPos(x, y, board);

                if(pos != null) System.out.print(Logic.getPlayerByID(pos[2]).getSymbol() + " ");
                else            System.out.print(emptyChar);

            }
            System.out.println();
        }
    }

    public static void printWholeBoard(long[][] board, String emptyChar) {
        for (long[] nums : board) {
            for (long content : nums) {

                if (content != 0) System.out.print(content + " ");
                else System.out.print(emptyChar);

            }
            System.out.println();
        }
    }

}
