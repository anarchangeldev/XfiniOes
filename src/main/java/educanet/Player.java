package educanet;

import java.util.ArrayList;

public class Player {

    private String name;
    private String symbol;
    private final int ID;
    private ArrayList<int[]> playHistory = new ArrayList<>(); //UNUSED -> maybe can speed up the checkWin method later

    public Player(String name, String symbol, int ID) {
        this.name = name;
        this.symbol = symbol;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getID() {
        return ID;
    }

    public void savePlay(int[] pos) {
        playHistory.add(pos);
    }

    public void savePlay(int x, int y) {
        savePlay(new int[]{x,y});
    }

}
