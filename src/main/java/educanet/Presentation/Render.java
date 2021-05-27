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
    public void renderCycle(ArrayList<Player> players, ArrayList<String[]> board, String emptyChar, String startX, String startY, long chunkSize, Logic l, Game g) {
        showPlayers(players);
        printChunk(board, startX, startY, chunkSize, emptyChar);
        printWholeBoard(board,emptyChar, l, g); //remove later
    }
    //region renderCycle overloading
    public void renderCycle(String emptyChar, String startX, String startY, long chunkSize, Logic l, Game g) {renderCycle(g.getPlayers(), g.getBoard(), emptyChar, startX, startY, chunkSize, l, g);}
    public void renderCycle(String emptyChar, String[] xy, long chunkSize, Logic l, Game g) {renderCycle(emptyChar, xy[0], xy[1], chunkSize,l, g);}
    public void renderCycle(String emptyChar, String[]xySize, Logic l, Game g) {renderCycle(emptyChar,xySize[0], xySize[1],Long.parseLong(xySize[2]),l, g);}
    public void renderCycle(String startX, String startY, long chunkSize, Logic l, Game g) {renderCycle(g.getEmptyChar(), startX, startY, chunkSize,l, g);}
    public void renderCycle(String[]xy, long chunkSize, Logic l, Game g) {renderCycle(g.getEmptyChar(),xy, chunkSize,l, g);}
    public void renderCycle(String[]xySize, Logic l, Game g) {renderCycle(g.getEmptyChar(),xySize,l, g);}
    //endregion


    /**
     * displays players
     * @param players players in game
     */
    public void showPlayers(ArrayList<Player> players) {

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
    public void showPlayers(Game g) {showPlayers(g.getPlayers());}

    /**
     * displays chunk from given XY with given reach and fills empty fields with given string
     * @param board game field
     * @param startX starting position X
     * @param startY starting position Y
     * @param chunkSize render reach
     * @param emptyChar filler char
     */
    public void printChunk(ArrayList<String[]> board, String startX, String startY, long chunkSize, String emptyChar) {
        //TODO
    }
    public void printChunk(ArrayList<String[]> board, String[]xy, long chunkSize, String emptyChar) {printChunk(board, xy[0], xy[1], chunkSize, emptyChar); }
    public void printChunk(String[]xy, long chunkSize, String emptyChar, Game g) { printChunk(g.getBoard(),xy, chunkSize, emptyChar);}

    //--------- TEST ------------

    //region testbed
    /**
     * prints the whole board
     * @param board game field
     * @param emptyChar filler char
     */
    public void printWholeBoard(ArrayList<String[]> board, String emptyChar, Logic l, Game g) {
        //theoretically pretty fucking heavy and big loop
        for (long y = Long.parseLong(l.getMinY(board)); y <= Long.parseLong(l.getMaxY(board)); y++) {
            for (long x = Long.parseLong(l.getMinX(board)); x <= Long.parseLong(l.getMaxX(board)); x++) {

                String[] pos = l.findPos(String.valueOf(x), String.valueOf(y), board);

                if(pos != null) System.out.print(l.getPlayerByID(pos[2], g).getSymbol() + " ");
                else            System.out.print(emptyChar+" ");

            }
            System.out.println();
        }
    }


    //----- DO NOT USE -----------
    public void printWholeBoard(long[][] board, String emptyChar) {
        for (long[] nums : board) {
            for (long content : nums) {

                if (content != 0) System.out.print(content + " ");
                else System.out.print(emptyChar);

            }
            System.out.println();
        }
    }
    //endregion
}
