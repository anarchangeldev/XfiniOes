package educanet.Presentation;

import educanet.Logic.Game;
import educanet.Logic.Logic;
import educanet.Player.*;

import java.util.ArrayList;

public class Render {
    /**
     * displays all needed info for the turn
     * @param players players in game
     * @param board the game field
     */
    public static void renderCycle(ArrayList<Player> players, ArrayList<long[]> board, String emptyChar, long startX, long startY, long chunkSize) {
        showPlayers(players);
        printChunk(board, startX, startY, chunkSize, emptyChar);
        printWholeBoard(board,emptyChar); //remove later
    }
    public static void renderCycle(String emptyChar, long startX, long startY, long chunkSize) {renderCycle(Game.getPlayers(), Game.getBoard(), emptyChar, startX, startY, chunkSize);}
    public static void renderCycle(String emptyChar, long[] xy, long chunkSize) {renderCycle(emptyChar, xy[0], xy[1], chunkSize);}
    public static void renderCycle(String emptyChar, long[]xySize) {renderCycle(emptyChar,xySize[0], xySize[1],xySize[2]);}
    public static void renderCycle(long startX, long startY, long chunkSize) {renderCycle(Game.getEmptyChar(), startX, startY, chunkSize);}
    public static void renderCycle(long[]xy, long chunkSize) {renderCycle(Game.getEmptyChar(),xy, chunkSize);}
    public static void renderCycle(long[]xySize) {renderCycle(Game.getEmptyChar(),xySize);}


    /**
     * displays players
     * @param players players in game
     */
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
        System.out.println("------------------------");
    }
    public static void showPlayers() {showPlayers(Game.getPlayers());}

    /**
     * displays chunk from given XY with given reach and fills empty fields with given string
     * @param board game field
     * @param startX starting position X
     * @param startY starting position Y
     * @param chunkSize render reach
     * @param emptyChar filler char
     */
    public static void printChunk(ArrayList<long[]> board, long startX, long startY, long chunkSize, String emptyChar) {
        //TODO
    }
    public static void printChunk(ArrayList<long[]> board, long[]xy, long chunkSize, String emptyChar) {printChunk(board, xy[0], xy[1], chunkSize, emptyChar); }
    public static void printChunk(long[]xy, long chunkSize, String emptyChar) { printChunk(Game.getBoard(),xy, chunkSize, emptyChar);}

    //--------- TEST ------------

    /**
     * prints the whole board
     * @param board game field
     * @param emptyChar filler char
     */
    public static void printWholeBoard(ArrayList<long[]> board, String emptyChar) {
        //theoretically pretty fucking heavy and big loop
        for (long y = Logic.getMinY(board); y <= Logic.getMaxY(board); y++) {
            for (long x = Logic.getMinX(board); x <= Logic.getMaxX(board); x++) {

                long[] pos = Logic.findPos(x, y, board);

                if(pos != null) System.out.print(Logic.getPlayerByID(pos[2]).getSymbol() + " ");
                else            System.out.print(emptyChar+" ");

            }
            System.out.println();
        }
    }
    public static void printWholeBoard(String emptyChar) { printWholeBoard(Game.getBoard(), emptyChar); }


    //----- DO NOT USE -----------
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
