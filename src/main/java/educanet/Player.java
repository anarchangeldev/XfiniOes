package educanet;

import java.util.ArrayList;

public class Player {

    private String name;
    private String symbol;
    private final long ID;

    private ArrayList<long[]> playHistory = new ArrayList<>(); //UNUSED -> maybe can speed up the checkWin method later

    public Player(String name, String symbol, long ID) {
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

    public long getID() {
        return ID;
    }

    public void savePlay(long[] pos) {
        playHistory.add(pos);
    }

    public void savePlay(long x, long y) {
        savePlay(new long[]{x,y});
    }


}
