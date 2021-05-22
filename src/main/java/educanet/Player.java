package educanet;

public class Player {

    private String name;
    private String symbol;
    private final int ID;

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

}
