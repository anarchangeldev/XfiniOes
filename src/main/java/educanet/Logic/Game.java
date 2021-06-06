package educanet.Logic;

import educanet.Player.Player;
import educanet.Presentation.Render;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public Logic l = new Logic();
    public Board b = new Board();
    public Render r = new Render();

    public ArrayList<Player> players = new ArrayList<>();
    public ArrayList<String> usedSymbols = new ArrayList<>();
    public ArrayList<String[]> board = new ArrayList<>();

    public long turns = 0;

    public int symbolWinCount = 4;
    public int chunkSize = 16;
    public String emptyChar = "-";

    public String[] lastPlay = new String[]{"0","0","0"};


    public void init() {
        Scanner sc = new Scanner(System.in);
        l.charCreation(sc, this);
        r.showPlayers(players);

        gameLoop(sc);
        sc.close();
    }

    public void gameLoop(Scanner sc) {
        String wonID = "0"; //ID of player who has won !no player with ID 0!

        while(wonID.equals("0")) {
            for(Player player : players) {

                r.renderCycle(players, board, emptyChar, lastPlay[0], lastPlay[1], chunkSize, l, this, player);
                l.turn(player, sc, b, this);
                updateVars(player);

                //both dont work properly, but #2 is kinda better
                //if(l.checkWin(player, board, this)) wonID = player.getID();
                if(l.checkWinTwo(player, board, this)) wonID = player.getID();
            }
        }
        win(wonID);
    }

    private void updateVars(Player player) {
        turns++;
        setLastPlay(player);
        board = b.getBoardList();
    }

    private void setLastPlay(Player player) {
        if(player.getLastPlay() != null) {
            lastPlay = new String[]{player.getLastPlay()[0], player.getLastPlay()[1], player.getID()};
        }
    }


    /**
     winner method.
     * */
    public void win(String winnerID) {
        Player winner = l.getPlayerByID(players, winnerID);
        System.out.println(winner.getName() + " alias " + winner.getSymbol() + " has won the game in " + turns + " turns.");
    }

//------------- GETTERS ---------------------

    //region getters
    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<String> getUsedSymbols() {
        return usedSymbols;
    }

    public ArrayList<String[]> getBoard() {
        return board;
    }

    public String getEmptyChar() { return emptyChar; }

    public String[] getLastPlay() { return lastPlay; }

    //endregion

//-----------------------------------------
}
