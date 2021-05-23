package educanet;

public class AI extends Player{

    private final int difficulty;

    public AI(String name, String symbol, int ID, int difficulty) {
        super(name, symbol, ID);
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void turn() {
        System.out.println("AI" + getID());
        //TODO
    }
}
