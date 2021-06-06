package educanet.Presentation;

import educanet.Logic.Game;
import educanet.Logic.Logic;
import educanet.Player.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Render {
    /**
     * displays all needed info for the turn
     * @param players players in game
     * @param board the game field
     */
    public void renderCycle(ArrayList<Player> players, ArrayList<String[]> board, String emptyChar, String startX, String startY, long chunkSize, Logic l, Game g, Player player) {
        showPlayers(players);
        String[] playersLastPlay = player.getLastPlay();
        if(playersLastPlay != null) {
            System.out.println("last global play:\n");
            printChunk(board, startX, startY, chunkSize, emptyChar, l, g);
            System.out.println("\nYour last play:\n");
            printChunk(board, playersLastPlay[0], playersLastPlay[1], chunkSize, emptyChar, l, g);
        } else {
            printChunk(board, startX, startY, chunkSize, emptyChar, l, g);
        }



    }
    //region renderCycle overloading
    public void renderCycle(String emptyChar, String startX, String startY, long chunkSize, Logic l, Game g, Player player) {renderCycle(g.getPlayers(), g.getBoard(), emptyChar, startX, startY, chunkSize, l, g, player);}
    public void renderCycle(String emptyChar, String[] xy, long chunkSize, Logic l, Game g, Player player) {renderCycle(emptyChar, xy[0], xy[1], chunkSize,l, g, player);}
    public void renderCycle(String emptyChar, String[]xySize, Logic l, Game g, Player player) {renderCycle(emptyChar,xySize[0], xySize[1],l.StringToLong(xySize[2]),l, g, player);}
    public void renderCycle(String startX, String startY, long chunkSize, Logic l, Game g, Player player) {renderCycle(g.getEmptyChar(), startX, startY, chunkSize,l, g, player);}
    public void renderCycle(String[]xy, long chunkSize, Logic l, Game g, Player player) {renderCycle(g.getEmptyChar(),xy, chunkSize,l, g, player);}
    public void renderCycle(String[]xySize, Logic l, Game g, Player player) {renderCycle(g.getEmptyChar(),xySize,l, g, player);}
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
    public void printChunk(ArrayList<String[]> board, String startX, String startY, long chunkSize, String emptyChar, Logic l, Game g) {
        long Xpos = l.StringToLong(startX) - (chunkSize / 2);
        long Ypos = l.StringToLong(startY) + (chunkSize / 2);
        String spacing = " ";
        spacing += fixSpace(Xpos, Ypos, chunkSize, l, board);
        chunkSize++;
        topInfo(chunkSize, l.StringToLong(startX), l, spacing);
        for (long y = 0; y < chunkSize; y++) {
            sideInfo(chunkSize, y, l.StringToLong(startY), l);
            for ( long x = 0; x < chunkSize; x++) {

                String[] pos = l.findPos(l.NumToString(Xpos), l.NumToString(Ypos), board);
                if(pos != null) System.out.print(l.getPlayerByID(pos[2], g).getSymbol() + spacing);
                else            System.out.print(emptyChar + spacing);


                Xpos++;
            }
            Xpos = l.StringToLong(startX) - (chunkSize / 2);
            System.out.println();
            Ypos--;
        }

    }

    private void sideInfo(long chunkSize, long y, long startNum, Logic l) {
        long pPos = startNum + (y - (chunkSize /2));
        if (pPos < 0) pPos = Math.abs(pPos); else pPos *= -1;
        System.out.print(pPos + " ");
        calcSpace(chunkSize, startNum, l, pPos);
        System.out.print("|");
    }

    private void calcSpace(long chunkSize, long startNum, Logic l, long pPos) {
        long spacingDiff =  l.NumToString(startNum + chunkSize).length() - l.NumToString(pPos).length();

        for (int i = 0; i < spacingDiff; i++) {
            System.out.print(" ");
        }
    }

    private String fixSpace(long Xpos, long Ypos, long chunkSize, Logic l, ArrayList<String[]> board) {
        StringBuilder result = new StringBuilder(" ");
        long biggestLength = l.NumToString(Xpos).length();
        for (long y = 0; y < chunkSize; y++) {
            for (long x = 0; x < chunkSize; x++) {
                String[] pos = l.findPos(l.NumToString(Xpos), l.NumToString(Ypos), board);
                if(pos!= null && pos[0].length() > biggestLength) biggestLength = pos[0].length();
            }
        }
        System.out.println(biggestLength);
        for(long i = biggestLength; i != 0; i--) {
            result.append(" ");
        }
        return result.toString();
    }

    private void topInfo(long chunkSize, long startNum, Logic l, String spacing) {
        System.out.print("    ");
        calcSpace(chunkSize, startNum, l, startNum+chunkSize);
        for (long top = 0; top < chunkSize; top++) {
            System.out.print(startNum-(chunkSize/2)+spacing);
            startNum++;
        }
        System.out.println();
        System.out.print("    ");
        for (long top = 0; top < chunkSize; top++) {
            System.out.print("-----");
        }
        System.out.println();
    }

    public void printChunk(ArrayList<String[]> board, String[]xy, long chunkSize, String emptyChar, Logic l, Game g) {printChunk(board, xy[0], xy[1], chunkSize, emptyChar, l, g); }
    public void printChunk(String[]xy, long chunkSize, String emptyChar, Game g, Logic l) { printChunk(g.getBoard(),xy, chunkSize, emptyChar, l, g);}

    //--------- TEST ------------

    //region testbed
    /**
     * prints the whole board
     * @param board game field
     * @param emptyChar filler char
     */
    public void printWholeBoard(ArrayList<String[]> board, String emptyChar, Logic l, Game g) {
        //theoretically pretty fucking heavy and big loop
        for (long y = l.StringToLong(l.getMinY(board)); y <= l.StringToLong(l.getMaxY(board)); y++) {
            for (long x = l.StringToLong(l.getMinX(board)); x <= l.StringToLong(l.getMaxX(board)); x++) {

                String[] pos = l.findPos(l.NumToString(x), l.NumToString(y), board);

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
