package educanet.Logic;

import educanet.Player.*;

import java.util.*;

public class Logic {

    //------------ GAME LOGIC -------------

    //region Game Logic

    /**
     * checks if last player has won
     */
    public boolean checkWin(Player currentPlayer, ArrayList<String[]> board, Game g) {
        //search from the last played point of the player into all 4 direction for an unbroken chain of X players symbols
        //check 4 directions (horizontal, vert, diagonals) in both sides
        for (int i = 0; i < 4; i++) {
            if(checkDir(i, board, g, currentPlayer)) return true;
        }

        return false;
    }


    public boolean checkDir(int dir, ArrayList<String[]> board, Game g, Player player) {
        String[] lastPlayOfPlayer = player.getLastPlay();
        if (lastPlayOfPlayer == null) return false;

        switch (dir) {
            case 0 -> {
                return checkVert(lastPlayOfPlayer, player, board, g);
            }
            case 1 -> {
                return checkDiag(lastPlayOfPlayer, player, board, g);
            }
            case 2 -> {
                return checkHorizontal(lastPlayOfPlayer, player, board, g);
            }
            case 3 -> {
                return checkAntiDiag(lastPlayOfPlayer, player, board, g);
            }
        } //switch end

        return false;
    }


    // THIS SHIT DOESNT FUCKING WORK I WANT TO KILL MYSELF, FUCK THIS
    public boolean checkVert(String[] lastPlay, Player player, ArrayList<String[]> board, Game g) {
        boolean result = true;
        ArrayList<String> IDs = new ArrayList<>();
        for (int i = -(g.symbolWinCount / 2); i < g.symbolWinCount/2; i++) {
            for (long y = StringToLong(lastPlay[1]); y < g.symbolWinCount; y++) {
                System.out.println("calculating Vert at: " + lastPlay[0] + " | " + y);
                String[] pos = findPos(lastPlay[0], NumToString(y), board);
                if (pos == null) continue;
                IDs.add(pos[2]);
            }
        }

        for (String ID : IDs) {
            if (!ID.equals(player.getID())) {
                result = false;
                break;
            }
        }
        if(IDs.size() < g.symbolWinCount) return false;
        return result;
    }

    private boolean checkDiag(String[] lastPlay, Player player, ArrayList<String[]> board, Game g) {

        //TODO

        return false;
    }

    private boolean checkHorizontal(String[] lastPlay, Player player, ArrayList<String[]> board, Game g) {

        //TODO

        return false;
    }

    private boolean checkAntiDiag(String[] lastPlay, Player player, ArrayList<String[]> board, Game g) {

        //TODO

        return false;
    }


    public boolean checkWinTwo(Player player, ArrayList<String[]> board, Game g) {
        String[] lastPlayOfPlayer = player.getLastPlay();
        if (lastPlayOfPlayer == null) return false;
        String x = lastPlayOfPlayer[0];
        String y = lastPlayOfPlayer[1];
        if(x == null || y == null || x.equals("") || y.equals("")) return false;
        int symbolCount = 1;
        String s = player.getID();
        int n = g.symbolWinCount;
        long i = StringToLong(g.lastPlay[0]);
        //check col
        for(; i < n; i++){
            if(findPos(x,NumToString(i),g) == null) break;
            if(!findPos(x,NumToString(i),g)[2].equals(s))
                break;
            if(i == n-1){
                return true;
            }
        }

        //check row
        for(; i < n; i++){
            if(findPos(NumToString(i),y,g) == null) break;
            if(!findPos(NumToString(i),y,g)[2].equals(s))
                break;
            if(i == n-1){
                return true;
            }
        }

        //check diag
        if(x.equals(y)){
            //we're on a diagonal
            for(; i < n; i++){
                if(findPos(NumToString(i),NumToString(i),g) == null) break;
                if(!findPos(NumToString(i),NumToString(i),g)[2].equals(s))
                    break;
                if(i == n-1){
                    return true;
                }
            }
        }

        //check anti diag
        if(NumToString(StringToLong(x) + StringToLong(y)).equals(NumToString(n-1))){
            for(; i < n; i++){
                if(findPos(NumToString(i),NumToString((n-1)-i),g) == null) break;
                if(!findPos(NumToString(i),NumToString((n-1)-i),g)[2].equals(s))
                    break;
                if(i == n-1){
                    return true;
                }
            }
        }

        return symbolCount == g.symbolWinCount;
    }


    /**
     * Makes the play on the board and saves it into the players history of plays
     */
    public void play(String x, String y, Player player, Board b, Game g) {
        b.play(player.getID(), x, y);
        player.savePlay(x,y);
    }
    public void play(String[] xy, Player player, Board b, Game g) {play(xy[0], xy[1], player, b, g);}

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

    public String[] choosePosition(Scanner sc) {
        String x,y;
        x = y = null;
        while(true) {

            System.out.println("Where do you want to put your symbol?");

            try {
                System.out.println("X: ");
                x = sc.nextLine();
                StringToLong(x);
            } catch(NumberFormatException e) {
                System.out.println("invalid input");
            }

            try {
                System.out.println("Y: ");
                y = sc.nextLine();
                StringToLong(y);
                break;
            } catch(NumberFormatException e) {
                System.out.println("invalid input");
            }
        }
        return new String[]{x,y};
    }
    //endregion


    //------------ BOARD LOGIC ---------------------

    //region Board Logic
    /**
     * returns the contents of the position, if the position hasn't been played yet, returns null
     */
    public String[] findPos(String x, String y, ArrayList<String[]> board) {
        for (String[] position : board) {
            if(position[0].equals(x) && position[1].equals(y)) {
                return position;
            }
        }
        return null;
    }
    public String[] findPos(String x, String y, Game g) {return findPos(x,y, g.getBoard());}
    public String[] findPos(String[]xy, Game g) {return findPos(xy[0], xy[1], g);}
    /**
     * Determines if the specified position is empty or not
     */
    public boolean legitMove(String x, String y, ArrayList<String[]> board) {
        return findPos(x, y, board) == null;
    }
    public boolean legitMove(String x, String y, Game g) {
        return legitMove(x,y, g.getBoard());
    }
    public boolean legitMove(String[] xy, Game g) {
        return legitMove(xy[0], xy[1], g);
    }

    /**
     * returns 0 if pos is empty
     */
    public String getPlayerIDAtPos(String x, String y, ArrayList<String[]> board) {
        String[] pos = findPos(x, y, board);

        if(pos != null) return pos[2];
        else            return null;
    }
    public String getPlayerIDAtPos(String x, String y, Game g) {return getPlayerIDAtPos(x,y, g.getBoard());}
    public String getPlayerIDAtPos(String[]xy, Game g) {return getPlayerIDAtPos(xy[0], xy[1], g);}

    /**
     * returns the biggest X position from the ArrayList
     */
    public String getMaxX(ArrayList<String[]> board) {
        if(board == null) return null;

        ArrayList<String> xPos = new ArrayList<>();

        board.forEach(e -> xPos.add(e[0]));

        return Collections.max(xPos);
    }
    public String getMaxX(Game g) {return getMaxX(g.getBoard());}

    /**
     * returns the biggest Y position from the ArrayList
     */
    public String getMaxY(ArrayList<String[]> board) {
        if(board == null) return null;

        ArrayList<String> yPos = new ArrayList<>();

        board.forEach(e -> yPos.add(e[1]));

        return Collections.max(yPos);
    }
    public String getMaxY(Game g) {return getMaxY(g.getBoard());}

    /**
     * returns the smallest X position from the ArrayList
     */
    public String getMinX(ArrayList<String[]> board) {
        if(board == null) return null;
        ArrayList<String> xPos = new ArrayList<>();

        board.forEach(e -> xPos.add(e[0]));

        return Collections.min(xPos);
    }
    public String getMinX(Game g) {return getMinX(g.getBoard());}

    /**
     * returns the smallest Y position from the ArrayList
     */
    public String getMinY(ArrayList<String[]> board) {
        if(board == null) return null;
        ArrayList<String> yPos = new ArrayList<>();

        board.forEach(e -> yPos.add(e[1]));

        return Collections.min(yPos);
    }
    public String getMinY(Game g) {return getMinY(g.getBoard());}

    /**
     * converts arrayLists to an array dynamically
     */
    public String[][] convertToArray(ArrayList<String[]> boardList) {
        String[][] board =
                new String[(StringToInt(getMaxY(boardList))+StringToInt(getMinY(boardList)))+2]
                          [(StringToInt(getMaxX(boardList))+StringToInt(getMinX(boardList)))+2];
        //makes an array with the sizing of the biggest number inside the corresponding ArrayList

        for (String[] element : boardList) {
            int x = StringToInt(element[0]);
            int y = StringToInt(element[1]);
            String playerID = element[2];

            board[y][x] = playerID;
        }

        return board;
    }
    public String[][] convertToArray(Game g) {return convertToArray(g.getBoard());}
    //endregion

    //---------- PLAYER LOGIC ---------------------

    //region Player Logic

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
     * creates a player or AI agent
     */
    public void createPlayer(Scanner sc, boolean player, Game g) {
        // VARIABLES
        ArrayList<String> usedSymbols = g.getUsedSymbols();
        String type = (player) ? "player" : "AI";
        String name;
        String symbol;
        int AIdifficulty = 0;
        String ID = NumToString(g.getPlayers().size()+1);

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
            for(int i = 0; i < 26; i++) {
                if(i+65 == 77) continue; //remove M m [ if(i+97 == 109) is valid too ] 77=M 109=m
                alphabet[i] = (char)(97 + i);
                alphabet[i+26] = (char)(65 + i);
            }
            int randomSymbol = new Random().nextInt(alphabet.length);
            mastermindSymbol = Character.toString(alphabet[randomSymbol]);
        }
        g.players.add(new AI("mastermind",mastermindSymbol,"2", 3));
    }

    /**
     * returns null if no player is found
     */
    public Player getPlayerByID(ArrayList<Player> players, String targetID) {
        if(players == null) return null;

        for (Player player : players) {
            if(player.getID().equals(targetID)) return player;
        }

        return null;
    }
    public Player getPlayerByID(String targetID, Game g) {return getPlayerByID(g.getPlayers(), targetID);}


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

    //--------- UTILITY ---------
    //region Utility

    public String NumToString(long num) {
        return String.valueOf(num);
    }

    public Long StringToLong(String string) {
        return Long.parseLong(string);
    }

    public int StringToInt(String string) {
        return Integer.parseInt(string);
    }


    //endregion
}
