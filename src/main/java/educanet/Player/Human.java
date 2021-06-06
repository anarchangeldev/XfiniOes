package educanet.Player;

import educanet.Logic.Board;
import educanet.Logic.Game;
import educanet.Logic.Logic;

import java.util.Scanner;

public class Human extends Player{
    public Human(String name, String symbol, String ID) {
        super(name, symbol, ID);
    }

    public void turn(Scanner sc, Logic l, Board b, Game g) {

        String[] pos = l.choosePosition(sc);

        if (!l.legitMove(pos, g)) {
            Player occupant = l.getPlayerByID(l.getPlayerIDAtPos(pos, g), g);
            System.out.println("Position already occupied by "+occupant.getName()+" ("+occupant.getSymbol()+"). choose another");
            turn(sc, l , b, g);
        }
        l.play(pos, this, b, g);

    }


}
