package educanet.Logic;

import educanet.Player.*;

import java.lang.reflect.Array;
import java.util.*;

public class Logic {

    //------------ GAME LOGIC -------------

    //region Game Logic
    /**
     *  Creates players || AI agents
     */
    public void charCreation(Scanner sc, Game g) {
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
                case "P" -> createPlayer(sc, true, g);
                case "A" -> createPlayer(sc, false, g);
                case "E" -> {
                    System.out.println("EXITING...");
                    continueCreation = false;
                }
            } //switch end

        } //while loop end
        playerCountCheck(sc, g);
    }

    /**
     * checks if last player has won
     */
    public boolean checkWin(Player currentPlayer, ArrayList<long[]> board, int dir, Game g) {
        //TODO
        //search from the last played point of the player into all 4 direction for an unbroken chain of X players symbols


        long[] lastPlayOfPlayer = currentPlayer.getLastPlay();
        if (lastPlayOfPlayer == null) return false;

        //check 4 directions (horizontal, vert, diagonals) in both sides
        for (int i = 0; i < 4; i++) {
            if(checkDir(i, board, g)) return true;
        }

        return false;
    }
    public boolean checkWin(Player currentPlayer, ArrayList<long[]> board, Game g) {return checkWin(currentPlayer, board, 0, g);}


    public boolean checkDir(int Dir, ArrayList<long[]> board, Game g) {
        int symbolCount = 0;



        return symbolCount == g.symbolWinCount;
    }

    /**
     * checks if last player has won using an array mapping the surroundings (size of array is winSymbolCount * 2)
     */
    public boolean checkWinUsingArray(Player currentPlayer, ArrayList<long[]> board) {
        //search from the last played point of the player for an unbroken chain of X players symbols using an array long[winSymbolCount][wSC]


        long[] lastPlayOfPlayer = currentPlayer.getLastPlay();
        if (lastPlayOfPlayer == null) return false;

        //TODO

        return true;
    }


    /**
     * Makes the play on the board and saves it into the players history of plays
     */
    public void play(long x, long y, Player player, Board b) {
        b.play(player.getID(), x, y);
        player.savePlay(x,y);
    }
    public void play(long[] xy, Player player, Board b) {play(xy[0], xy[1], player, b);}

    /**
     * Checks if there are players or if the player is alone. (either gives option to exit, add more, or if the player is alone, adds a Hard AI)
     */
    public void playerCountCheck(Scanner sc, Game g) {

        if (g.getPlayers().size() == 0) {

            System.out.println("No players or AIs were added to the game\nDo you want to (E)xit, or (R)etry?");
            String selection = sc.nextLine();

            if (selection.equalsIgnoreCase("E"))        throw new Error("no players");
            else if (selection.equalsIgnoreCase("R"))   charCreation(sc, g);
            else {
                System.out.println("rebellious rascal... I hope you just missclicked");
                throw new Error("monke");
            } //elif end
        }//size if end
        if (g.getPlayers().size() == 1) {
            System.out.println("You cant play alone silly, I'll add myself then, prepare...");
            mastermindCreate(g);
        }
    }

    /**
     What to do on turn -> AI logic || player input
     * */
    public void turn(Player player, Scanner sc, Board b, Game g) {

        if (player instanceof AI) { //AI play
            System.out.println("AI"+player.getID()+": "+player.getName()+" ("+player.getSymbol()+") is playing.");
            ((AI) player).turn();
            return;
        }else if (player instanceof Human) { //Human play
            System.out.println("Player"+player.getID()+": "+player.getName()+" ("+player.getSymbol()+") is playing.");
            ((Human) player).turn(sc, this, b, g);
            return;
        } else System.out.println("wtf?");

        System.out.println(player.getName() + " ("+player.getSymbol()+") has ended their turn.");
    }

    public long[] choosePosition(Scanner sc) {
        long x,y;
        while(true) {
            try {
                System.out.println("Where do you want to put your symbol?\nX: ");
                x = sc.nextLong();
                System.out.println("Y: ");
                y = sc.nextLong();
                break;
            } catch(NumberFormatException e) {
                System.out.println("invalid input");
            }
        }
        return new long[]{x,y};
    }
    //endregion


    //------------ BOARD LOGIC ---------------------

    //region Board Logic
    /**
     * returns the contents of the position, if the position hasn't been played yet, returns null
     */
    public long[] findPos(long x, long y, ArrayList<long[]> board) {
        for (long[] position : board) {
            if(position[0] == x && position[1] == y) {
                return position;
            }
        }
        return null;
    }
    public long[] findPos(long x, long y, Game g) {return findPos(x,y, g.getBoard());}
    public long[] findPos(long[]xy, Game g) {return findPos(xy[0], xy[1], g);}
    /**
     * Determines if the specified position is empty or not
     */
    public boolean legitMove(long x, long y, ArrayList<long[]> board) {
        return findPos(x, y, board) == null;
    }
    public boolean legitMove(long x, long y, Game g) {
        return legitMove(x,y, g.getBoard());
    }
    public boolean legitMove(long[] xy, Game g) {
        return legitMove(xy[0], xy[1], g);
    }

    /**
     * returns 0 if pos is empty
     */
    public long getPlayerIDAtPos(long x, long y, ArrayList<long[]> board) {
        long[] pos = findPos(x, y, board);

        if(pos != null) return pos[2];
        else            return 0;
    }
    public long getPlayerIDAtPos(long x, long y, Game g) {return getPlayerIDAtPos(x,y, g.getBoard());}
    public long getPlayerIDAtPos(long[]xy, Game g) {return getPlayerIDAtPos(xy[0], xy[1], g);}

    /**
     * returns the biggest X position from the ArrayList
     */
    public long getMaxX(ArrayList<long[]> board) {
        if(board == null) return 0;

        ArrayList<Long> xPos = new ArrayList<>();

        board.forEach(e -> xPos.add(e[0]));

        return Collections.max(xPos);
    }
    public long getMaxX(Game g) {return getMaxX(g.getBoard());}

    /**
     * returns the biggest Y position from the ArrayList
     */
    public long getMaxY(ArrayList<long[]> board) {
        if(board == null) return 0;

        ArrayList<Long> yPos = new ArrayList<>();

        board.forEach(e -> yPos.add(e[1]));

        return Collections.max(yPos);
    }
    public long getMaxY(Game g) {return getMaxY(g.getBoard());}

    /**
     * returns the smallest X position from the ArrayList
     */
    public long getMinX(ArrayList<long[]> board) {
        if(board == null) return 0;
        ArrayList<Long> xPos = new ArrayList<>();

        board.forEach(e -> xPos.add(e[0]));

        return Collections.min(xPos);
    }
    public long getMinX(Game g) {return getMinX(g.getBoard());}

    /**
     * returns the smallest Y position from the ArrayList
     */
    public long getMinY(ArrayList<long[]> board) {
        if(board == null) return 0;
        ArrayList<Long> yPos = new ArrayList<>();

        board.forEach(e -> yPos.add(e[1]));

        return Collections.min(yPos);
    }
    public long getMinY(Game g) {return getMinY(g.getBoard());}

    /**
     * converts arrayLists to an array dynamically
     */
    public long[][] convertToArray(ArrayList<long[]> boardList) {
        long[][] board = new long[(int) (getMaxY(boardList)+getMinY(boardList)+2)][(int) (getMaxX(boardList)+getMinX(boardList)+2)]; //makes an array with the sizing of the biggest number inside the corresponding ArrayList

        for (long[] element : boardList) {
            int x = (int) element[0];
            int y = (int) element[1];
            long playerID = element[2];

            board[y][x] = playerID;
        }

        return board;
    }
    public long[][] convertToArray(Game g) {return convertToArray(g.getBoard());}
    //endregion

    //---------- PLAYER LOGIC ---------------------

    //region Player Logic
    /**
     * creates a player or AI agent
     */
    public void createPlayer(Scanner sc, boolean player, Game g) {
        // VARIABLES
        ArrayList<String> usedSymbols = g.getUsedSymbols();
        String type = (player) ? "player" : "AI";
        String name;
        String symbol;
        int AIdifficulty = 0;
        long ID = g.getPlayers().size()+1;
        boolean invalidSymbol = false;

        // NAME SELECT
        System.out.println("What do you want to name the " + type + "?");
        name = sc.nextLine();

        // SYMBOL SELECT
        System.out.println("What symbol will " + name + " use?");
        symbol = sc.nextLine();

        //SYMBOL VALIDATION -> could be simplified
        if(usedSymbols.contains(symbol)) {
            System.out.println("Symbol is already used by another Agent. Choose a different one:");
            invalidSymbol = true;
        }

        while (invalidSymbol) {
            symbol = sc.nextLine();
            if(usedSymbols.contains(symbol)) System.out.println("Symbol is already used by another Agent. Choose a different one:");
            else invalidSymbol = false;
        }

        //AI SPECIFIC DIFFICULTY
        if(!player) {
            System.out.println("How hard will the AI be? 1. monke -> 3. mastermind (number from 1 to 3)");
            int diff;
            try {
                diff = sc.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("seems like you are a monke, so you will be playing against one too!");
                diff = 1;
            }

            if(diff<1 || diff>3) {
                System.out.println("seems like you are a monke, so you will be playing against one too!");
                AIdifficulty = 1;
            } else AIdifficulty = diff;
        }

        //-----
        g.usedSymbols.add(symbol);
        //-----
        if (player) g.players.add(new Human(name, symbol, ID));
        else        g.players.add(new    AI(name, symbol, ID, AIdifficulty));

    }

    /**
     * Creates a hard AI named mastermind with m, or if it is occupied a random letter, as its symbol
     */
    public void mastermindCreate(Game g) {
        String mastermindSymbol = "m";
        //if player uses M/m symbol
        if(!g.getPlayers().get(0).getSymbol().equalsIgnoreCase(mastermindSymbol)) {
            char[] alphabet = new char[26*2]; //a-zA-Z
            for(int i = 0; i < 26; i++){
                if(i+65 == 77) continue; //remove M m [ if(i+97 == 109) is valid too ] 77=M 109=m
                alphabet[i] = (char)(97 + i);
                alphabet[i+26] = (char)(65 + i);
            }
            int randomSymbol = new Random().nextInt(alphabet.length);
            mastermindSymbol = Character.toString(alphabet[randomSymbol]);
        }
        g.players.add(new AI("mastermind",mastermindSymbol,1, 3));
    }

    /**
     * returns null if no player is found
     */
    public Player getPlayerByID(ArrayList<Player> players, long targetID) {
        if(players == null) return null;

        for (Player player : players) {
            if(player.getID() == targetID) return player;
        }

        return null;
    }
    public Player getPlayerByID(long targetID, Game g) {return getPlayerByID(g.getPlayers(), targetID);}


    /**
     * returns the player by his symbol, if not found, returns null
     */
    public Player getPlayerBySymbol(String symbol, ArrayList<Player> players) {
        for(Player player : players) {
            if(player.getSymbol().equals(symbol)) return player;
        }
        return null;
    }
    public Player getPlayerBySymbol(String symbol, Game g) {return getPlayerBySymbol(symbol, g.getPlayers());}


    //endregion

}
