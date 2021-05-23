package educanet;

import java.util.*;

public class Logic {

    //------------ GAME LOGIC -------------

    /**
     *  Creates players || AI agents
     */
    public static void charCreation(Scanner sc) {
        boolean continueCreation = true;
        String[] validAnswers = new String[]{"P", "A", "E"};
        while (continueCreation) {

            //selection
            System.out.println("Do you want to add a (P)layer || (A)I or (E)xit creation?");
            String selection = sc.nextLine().toUpperCase();


            //validate selection
            if (!(Arrays.asList(validAnswers).contains(selection))) {
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
        playerCountCheck(sc);
    }

    /**
     * returns 0 if no-one has won yet or ID of player, who has won
     */
    public static int checkWin(Player currentPlayer) {
        //TODO



        return 0;
    }

    /**
     winner method.
     * */
    public static void win(int winnerID) {
        Player winner = Logic.getPlayer(Game.getPlayers(), winnerID);
    }

    /**
     * Makes the play on the board and saves it into the players history of plays
     */
    public static void play(int x, int y, Player player) {
        Board.play(player.getID(), x, y);
        player.savePlay(x,y);
    }

    /**
     * Checks if there are players or if the player is alone. (either gives option to exit, add more, or if the player is alone, adds a Hard AI)
     */
    public static void playerCountCheck(Scanner sc) {

        if (Game.getPlayers().size() == 0) {

            System.out.println("No players or AIs were added to the game\nDo you want to (E)xit, or (R)etry?");
            String selection = sc.nextLine();

            if (selection.equalsIgnoreCase("E"))        throw new Error("no players");
            else if (selection.equalsIgnoreCase("R"))   charCreation(sc);
            else {
                System.out.println("rebellious rascal... I hope you just miss clicked");
                throw new Error("monke");
            } //elif end
        }//size if end
        if (Game.getPlayers().size() == 1) {
            System.out.println("You cant play alone silly, I'll myself then, prepare...");
            mastermindCreate();
        }
    }

    /**
     What to do on turn -> AI logic || player input
     * */
    public static void turn(Player player) {
        if (player instanceof AI) {((AI) player).turn(); return;}
        System.out.println("player" + player.getID());

        //TODO

    }


    //------------ BOARD LOGIC ---------------------

    /**
     * returns the contents of the position, if the position hasn't been played yet, returns null
     */
    public static int[] findPos(int x, int y, ArrayList<int[]> board) {
        for (int[] position : board) {
            if(position[0] == x && position[1] == y) {
                return position;
            }
        }
        return null;
    }

    /**
     * Determines if the specified position is empty or not
     */
    public static boolean legitMove(int x, int y, ArrayList<int[]> board) {
        return findPos(x, y, board) == null;
    }

    /**
     * returns 0 if pos is empty
     */
    public static int getPlayerID_AtPos(int x, int y, ArrayList<int[]> board) {
        int[] pos = findPos(x, y, board);

        if(pos != null) return pos[2];
        else            return 0;
    }

    /**
     * returns the biggest X position from the ArrayList
     */
    public static int getMaxX(ArrayList<int[]> board) {
        if(board == null) return 0;

        ArrayList<Integer> xPos = new ArrayList<>();

        board.forEach(e -> xPos.add(e[0]));

        return Collections.max(xPos);
    }

    /**
     * returns the biggest Y position from the ArrayList
     */
    public static int getMaxY(ArrayList<int[]> board) {
        if(board == null) return 0;

        ArrayList<Integer> yPos = new ArrayList<>();

        board.forEach(e -> yPos.add(e[1]));

        return Collections.max(yPos);
    }

    /**
     * returns the smallest X position from the ArrayList
     */
    public static int getMinX(ArrayList<int[]> board) {
        if(board == null) return 0;
        ArrayList<Integer> xPos = new ArrayList<>();

        board.forEach(e -> xPos.add(e[0]));

        return Collections.min(xPos);
    }

    /**
     * returns the smallest Y position from the ArrayList
     */
    public static int getMinY(ArrayList<int[]> board) {
        if(board == null) return 0;
        ArrayList<Integer> yPos = new ArrayList<>();

        board.forEach(e -> yPos.add(e[1]));

        return Collections.min(yPos);
    }

    /**
     * converts arrayLists to an array dynamically
     */
    public static int[][] convertToArray(ArrayList<int[]> boardList) {
        int[][] board = new int[getMaxY(boardList)+getMinY(boardList)+2][getMaxX(boardList)+getMinX(boardList)+2]; //makes an array with the sizing of the biggest number inside the corresponding ArrayList

        for (int[] element : boardList) {
            int x = element[0];
            int y = element[1];
            int playerID = element[2];

            board[y][x] = playerID;
        }

        return board;
    }


    //---------- PLAYER LOGIC ---------------------

    /**
     * creates a player or AI agent
     */
    public static void createPlayer(Scanner sc, boolean player) {
        // VARIABLES
        ArrayList<String> usedSymbols = Game.getUsedSymbols();
        String type = (player) ? "player" : "AI";
        String name;
        String symbol;
        int AIdifficulty = 0;
        int ID = Game.getPlayers().size()+1;
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
            else { invalidSymbol = false; }
        }

        //AI SPECIFIC DIFFICULTY
        if(!player) {
            System.out.println("How hard will the AI be? 1. monke -> 3. mastermind (number from 1 to 3)");
            int diff = sc.nextInt();
            if(diff<1 || diff>3) {
                System.out.println("seems like you are a monke, so you will be playing against one too!");
                AIdifficulty = 1;
            } else AIdifficulty = diff;
        }

        //-----
        Game.usedSymbols.add(symbol);
        //-----
        if (player) Game.players.add(new Player(name, symbol, ID));
        else        Game.players.add(new     AI(name, symbol, ID, AIdifficulty));

    }

    /**
     * Creates a hard AI named mastermind with m, or if it is occupied a random letter, as its symbol
     */
    public static void mastermindCreate() {
        String mastermindSymbol = "m";
        //if player uses M/m symbol
        if(!Game.getPlayers().get(0).getSymbol().equalsIgnoreCase(mastermindSymbol)) {
            char[] alphabet = new char[26*2]; //a-zA-Z
            for(int i = 0; i < 26; i++){
                if(i+65 == 77) continue; //remove M m [ if(i+97 == 109) is valid too ] 77=M 109=m
                alphabet[i] = (char)(97 + i);
                alphabet[i+26] = (char)(65 + i);
            }
            int randomSymbol = new Random().nextInt(alphabet.length);
            mastermindSymbol = Character.toString(alphabet[randomSymbol]);
        }
        Game.players.add(new AI("mastermind",mastermindSymbol,1, 3));
    }

    /**
     * returns null if no player is found
     */
    public static Player getPlayer(ArrayList<Player> players, int targetID) {
        if(players == null) return null;

        for (Player player : players) {
            if(player.getID() == targetID) return player;
        }

        return null;
    }






}
