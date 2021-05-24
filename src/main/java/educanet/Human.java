package educanet;

import java.util.Scanner;

public class Human extends Player{
    public Human(String name, String symbol, long ID) {
        super(name, symbol, ID);
    }

    public void turn(Scanner sc) {


        long[] pos = Logic.choosePosition(sc);

        if (!Logic.legitMove(pos)) {
            Player occupant = Logic.getPlayerByID(Logic.getPlayerIDAtPos(pos));
            System.out.println("Position already occupied by "+occupant.getName()+" ("+occupant.getSymbol()+"). choose another");
            turn(sc);
        }
        Logic.play(pos, this);

    }


}
