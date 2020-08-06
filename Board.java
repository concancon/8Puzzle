public class Board {

    private static int[][] goalBoard = {
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 8, 0 }
    };
    private int[][] board;


    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        board = tiles;
    }

    // string representation of this board
    public String toString() {
        String s = "";

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                s = s.concat(Integer.toString(board[i][j]) + " ");

            }
            s = s.concat("\n");
        }
        return s;
    }

    //board dimension n
    public int dimension() {
        return board[0].length;

    }

    // number of tiles out of place
    public int hamming() {
        int diff = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != goalBoard[i][j] && board[i][j] != 0) diff++;

            }
        }
        return diff;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int man = 0;
        int content;
        int x, y;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {


                //1. get contents at index i, j
                content = board[i][j];


                //2. what is content's corresponding index in terms of nx n grid?
                x = content / dimension();
                y = content % dimension();

                if (content != 0) {

                    //3. what is the distance between should and is index
                    man += Math.abs(j - x);
                    man += Math.abs(i - y);
                    man--; //account fo 1 based indexing
                }
            }

        }
        return man;
    }
  /*//
    // is this board the goal board?
    public boolean isGoal()

    // does this board equal y?
    public boolean equals(Object y)

    // all neighboring boards
    public Iterable<Board> neighbors()

    // a board that is obtained by exchanging any pair of tiles
    public Board twin()*/

    // unit testing (not graded)

    public static void main(String[] args) {
        int[][] testArray = {
                { 8, 1, 3 },
                { 4, 0, 2 },
                { 7, 6, 5 }
        };

        Board b = new Board(testArray);

        System.out.println(b.toString());
        System.out.println(b.dimension());
        System.out.println(b.hamming());
        System.out.println(b.manhattan());
    }

}
