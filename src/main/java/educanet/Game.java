package educanet;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    public static ArrayList<Player> players = new ArrayList<>();
    public static ArrayList<String> usedSymbols = new ArrayList<>();
    public static ArrayList<long[]> board = new ArrayList<>();

    public static long turns = 0;

    public static void init() {
        Scanner sc = new Scanner(System.in);
        Logic.charCreation(sc);
        Render.showPlayers(players);

        //test(sc);
        gameLoop(sc);
    }

    public static void gameLoop(Scanner sc) {
        long wonID = 0; //ID of player who has won !no player with ID 0!

        while(wonID == 0) {
            for(Player player : players) {
                turns++;
                Logic.turn(player, sc);
                board = Board.getBoardList();
                wonID = Logic.checkWin(player);
            }
        }
        win(wonID);
    }


    /**
     winner method.
     * */
    public static void win(long winnerID) {
        Player winner = Logic.getPlayer(players, winnerID);
        System.out.println(winner.getName() + " alias " + winner.getSymbol() + " has won the game in " + turns + " turns.");
    }

//------------- GETTERS ---------------------

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static ArrayList<String> getUsedSymbols() {
        return usedSymbols;
    }

    public static ArrayList<long[]> getBoard() {
        return board;
    }

//-----------------------------------------


    public static void test(Scanner sc) {

        Logic.play(10, 10, new Player("test 1", "x", 1));
        Logic.play(2, 4,   new Player("test 2", "o", 2));
        Logic.play(0,0,    new Player("test 3", "z", 3));
        Logic.play(30,0,   new Player("test 4", "s", 4));
        Logic.play(15, 5,  new Player("test 5", "a", 5));
        Logic.play(9, 10,  new Player("test 1", "x", 1));
        Logic.play(8, 10,  new Player("test 1", "x", 1));
        Logic.play(7, 10,  new Player("test 1", "x", 1));


        System.out.println("----------------------");
        Render.printWholeBoard(Board.getBoardList(), "- ");

        System.exit(0);
    }
}
