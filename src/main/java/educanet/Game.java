package educanet;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    public static ArrayList<Player> players = new ArrayList<>();
    public static ArrayList<String> usedSymbols = new ArrayList<>();
    public static ArrayList<int[]> board = new ArrayList<>();



    public static void init() {
        charCreation();
        if (players.size() == 0) throw new Error("no players");
        test();//start();
    }

    public static void start() {
        int playingID = 1; //player on turn
        int wonID = 0; //ID of player who has won || no player with ID 0

        while(wonID == 0) {

            //TODO
            board = Board.getBoardList();
            wonID = Logic.checkWin(players);
            if(playingID < players.size()+1) playingID++; else playingID = 1;
        }
    }

    public static void charCreation() {
        boolean continueCreation = true;
        Scanner sc = new Scanner(System.in);



        while (continueCreation) {

            //selection
            System.out.println("Do you want to add a (P)layer || (A)I or (E)xit creation?");
            String selection = sc.nextLine().toUpperCase();


            //validate selection
            if (!(selection.equals("P") || selection.equals("A") || selection.equals("E"))) {
                System.out.println("invalid selection");
                continue;
            }

            //create based on selection
            switch (selection) {
                case "P" -> createPlayer(sc, true);
                case "A" -> createPlayer(sc, false);
                case "E" -> {
                    System.out.println("EXITING...");
                    continueCreation = false;
                }
            } //switch end

        } //while loop end

    }

    public static void createPlayer(Scanner sc, boolean player) {
        // VARIABLES
        String type = (player) ? "player" : "AI";
        String name;
        String symbol;
        int ID = players.size()+1;
        boolean invalidSymbol = false;

        // NAME SELECT
        System.out.println("What do you want to name the " + type + "?");
        name = sc.nextLine();

        // SYMBOL SELECT

        System.out.println("What symbol will " + name + " use?");
        symbol = sc.nextLine();

        //SYMBOL VALIDATION -> could be simplified
        if(usedSymbols.contains(symbol)) {System.out.println("Symbol is already used by another Agent. Choose a different one:"); invalidSymbol = true; }
        while (invalidSymbol) {

            symbol = sc.nextLine();
            if(usedSymbols.contains(symbol)) System.out.println("Symbol is already used by another Agent. Choose a different one:");
            else { usedSymbols.add(symbol); invalidSymbol = false; }
        }

        if (type.equals("player")) players.add(new Player(name, symbol, ID));
        else                       players.add(new AI(name, symbol, ID));

    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static ArrayList<String> getUsedSymbols() {
        return usedSymbols;
    }

    public static ArrayList<int[]> getBoard() {
        return board;
    }

//-----------------------------------------


    public static void test() {

        Board.play(1, 10,10);
        Board.play(1, 9,10);
        Board.play(1, 8,10);
        Board.play(1, 7,10);

        Board.play(2, 2,4);
        Board.play(3,0,0);
        Board.play(4,30,0);
        Board.play(5, 15, 5);

        System.out.println("----------------------");
        Render.printWholeBoard(Board.getBoardList(), "- ");

    }
}
