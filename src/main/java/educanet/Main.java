package educanet;

public class Main {

    public static void main(String[] args) {
        test();
    }

    public static void test() {


        Board.play(1, 10,10);
        Board.play(2, 2,4);
        Board.play(3,0,0);
        Board.play(4,30,0);

        printBoard(Board.getBoardArray());

    }

    public static void printBoard(int[][] board) {
        for (int[] ints : board) {
            for (int x = 0; x < ints.length; x++) {
                System.out.print(ints[x] + " ");
            }

            System.out.println();
        }
    }
}
