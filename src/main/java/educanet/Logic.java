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
    public static long checkWin(Player currentPlayer) {
        //TODO



        return 0;
    }


    /**
     * Makes the play on the board and saves it into the players history of plays
     */
    public static void play(long x, long y, Player player) {
        Board.play(player.getID(), x, y);
        player.savePlay(x,y);
    }
    public static void play(long[] xy, Player player) {play(xy[0], xy[1], player);}

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
    public static void turn(Player player, Scanner sc) {

        if (player instanceof AI) { //AI play
            System.out.println("AI: "+player.getName()+" ("+player.getID()+") is playing.");
            ((AI) player).turn();
            return;
        }

        if (player instanceof Human) { //Human play
            System.out.println("Player: "+player.getName()+" ("+player.getID()+") is playing.");
            ((Human) player).turn(sc);
            return;
        }
        System.out.println(player.getName() + " ("+player.getSymbol()+") has ended their turn.");
    }

    public static long[] choosePosition(Scanner sc) {
        System.out.println("Where do you want to put your symbol?\nX: ");
        long x = sc.nextLong();
        System.out.println("Y: ");
        long y = sc.nextLong();

        return new long[]{x,y};
    }


    //------------ BOARD LOGIC ---------------------

    /**
     * returns the contents of the position, if the position hasn't been played yet, returns null
     */
    public static long[] findPos(long x, long y, ArrayList<long[]> board) {
        for (long[] position : board) {
            if(position[0] == x && position[1] == y) {
                return position;
            }
        }
        return null;
    }
    public static long[] findPos(long x, long y) {return findPos(x,y,Game.getBoard());}
    public static long[] findPos(long[]xy) {return findPos(xy[0], xy[1]);}
    /**
     * Determines if the specified position is empty or not
     */
    public static boolean legitMove(long x, long y, ArrayList<long[]> board) {
        return findPos(x, y, board) == null;
    }
    public static boolean legitMove(long x, long y) {
        return legitMove(x,y,Game.getBoard());
    }
    public static boolean legitMove(long[] xy) {
        return legitMove(xy[0], xy[1]);
    }

    /**
     * returns 0 if pos is empty
     */
    public static long getPlayerID_AtPos(long x, long y, ArrayList<long[]> board) {
        long[] pos = findPos(x, y, board);

        if(pos != null) return pos[2];
        else            return 0;
    }
    public static long getPlayerID_AtPos(long x, long y) {return getPlayerID_AtPos(x,y, Game.getBoard());}
    public static long getPlayerID_AtPos(long[]xy) {return getPlayerID_AtPos(xy[0], xy[1]);}

    /**
     * returns the player by his symbol, if not found, returns null
     */
    public static Player getPlayerBySymbol(String symbol, ArrayList<Player> players) {
        for(Player player : players) {
            if(player.getSymbol().equals(symbol)) return player;
        }
        return null;
    }
    public static Player getPlayerBySymbol(String symbol) {return getPlayerBySymbol(symbol, Game.getPlayers());}
    /**
     * returns the biggest X position from the ArrayList
     */
    public static long getMaxX(ArrayList<long[]> board) {
        if(board == null) return 0;

        ArrayList<Long> xPos = new ArrayList<>();

        board.forEach(e -> xPos.add(e[0]));

        return Collections.max(xPos);
    }
    public static long getMaxX() {return getMaxX(Game.getBoard());}

    /**
     * returns the biggest Y position from the ArrayList
     */
    public static long getMaxY(ArrayList<long[]> board) {
        if(board == null) return 0;

        ArrayList<Long> yPos = new ArrayList<>();

        board.forEach(e -> yPos.add(e[1]));

        return Collections.max(yPos);
    }
    public static long getMaxY() {return getMaxY(Game.getBoard());}

    /**
     * returns the smallest X position from the ArrayList
     */
    public static long getMinX(ArrayList<long[]> board) {
        if(board == null) return 0;
        ArrayList<Long> xPos = new ArrayList<>();

        board.forEach(e -> xPos.add(e[0]));

        return Collections.min(xPos);
    }
    public static long getMinX() {return getMinX(Game.getBoard());}

    /**
     * returns the smallest Y position from the ArrayList
     */
    public static long getMinY(ArrayList<long[]> board) {
        if(board == null) return 0;
        ArrayList<Long> yPos = new ArrayList<>();

        board.forEach(e -> yPos.add(e[1]));

        return Collections.min(yPos);
    }
    public static long getMinY() {return getMinY(Game.getBoard());}

    /**
     * converts arrayLists to an array dynamically
     */
    public static long[][] convertToArray(ArrayList<long[]> boardList) {
        long[][] board = new long[(int) (getMaxY(boardList)+getMinY(boardList)+2)][(int) (getMaxX(boardList)+getMinX(boardList)+2)]; //makes an array with the sizing of the biggest number inside the corresponding ArrayList

        for (long[] element : boardList) {
            int x = (int) element[0];
            int y = (int) element[1];
            long playerID = element[2];

            board[y][x] = playerID;
        }

        return board;
    }
    public static long[][] convertToArray() {return convertToArray(Game.getBoard());}

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
        long ID = Game.getPlayers().size()+1;
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
        if (player) Game.players.add(new Human(name, symbol, ID));
        else        Game.players.add(new    AI(name, symbol, ID, AIdifficulty));

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
    public static Player getPlayer(ArrayList<Player> players, long targetID) {
        if(players == null) return null;

        for (Player player : players) {
            if(player.getID() == targetID) return player;
        }

        return null;
    }
    public static Player getPlayer(long targetID) {return getPlayer(Game.getPlayers(), targetID);}

}
