package educanet;

import educanet.Logic.Game;

public class Main {
    public static Game g = new Game();
    /*

    LOGIC TODO:
    - repair checkWin();
    - 0;0 is centered
    -check win to work with negative values

   RENDER TODO:
    - render logic (probably render area around last play || around current players last play || at selected location)
    - look of board
    - either render with something later (swing || awt) || do some beautiful console fuckery to atleast make it bearable
      (more into console tbh, too much hassle otherwise)

    AI TODO:
    - everything.... holy fuck.

    GENERAL TODO:
     - cleanup of code
     - commenting of code
     - better method description

    */


    public static void main(String[] args) {
        g.init();
    }

}


