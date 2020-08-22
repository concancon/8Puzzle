import java.util.ArrayList;

public class Board {

    private int[][] board;
    private int[] emptyTile;

    private void swapTiles(int x1, int y1, int x2, int y2) {
        int temp = this.board[x1][y1];
        this.board[x1][y1] = this.board[x2][y2];
        this.board[x2][y2] = temp;

    }

    //copy constructor
    private Board(Board b) {
        this.board = new int[b.dimension()][b.dimension()];
        for (int i = 0; i < b.dimension(); i++) {
            for (int j = 0; j < b.dimension(); j++) {
                this.board[i][j] = b.board[i][j];
            }
        }
    }

    private static int[][] goalArray = {
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 8, 0 }
    };

    private static Board goalBoard = new Board(goalArray);


    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {

        board = new int[tiles.length][tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (tiles[i][j] == 0) {
                    emptyTile = new int[] { i, j };
                }
                board[i][j] = tiles[i][j];
            }
        }

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
                if (board[i][j] != goalArray[i][j] && board[i][j] != 0) diff++;

            }
        }
        return diff;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int man = 0;
        int content;
        int x, y;
        int indexCombination;
        int shouldCombination;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {


                //1. get contents at index i, j
                content = board[i][j];
                if (content == 0) {
                    //do nothing at all. this function only measures tiles

                }
                else {

                    //2. what is content's corresponding index in terms of an n x n grid?
                    x = content / dimension();
                    y = content % dimension() - 1;
                    if (y < 0) y = y * -1;
                    indexCombination = x + y;
                    shouldCombination = i + j;
                    //3. what is the distance between should and is index
                    man += Math.abs(indexCombination - shouldCombination);
                }


            }

        }
        return man;
    }

    //does this board equal y?
    @Override
    public boolean equals(Object y) {
        if (y == this) {
            return true;
        }
        if (y == null) {
            return false;
        }
        if (!(y.getClass() == this.getClass())) {
            return false;
        }
        if (((Board) y).dimension() != this.dimension() || ((Board) y).manhattan() != this
                .manhattan()) {
            return false;
        }
        return true;

    }

    // is this board the goal board?
    public boolean isGoal() {

        return this.equals(goalBoard);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> listOfNeighbors = new ArrayList<>();
        int x = -1;
        int y = -1;
        outerloop:
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (this.board[i][j] == 0) {
                    x = i;
                    y = j;
                    break outerloop;
                }
            }
        }
        // System.out.println("found" + i + " " + j);


        //go through each element int the board and see if it has a 0 to its

        //1. left    0, -1
        if (x < dimension() && (y - 1) < dimension() && (y - 1) >= 0) {
            //make a copy of our board
            Board copyBoard = new Board(this);
            copyBoard.swapTiles(x, y, x, y - 1);

            listOfNeighbors.add(copyBoard);

        }

        //2. right   0, +1

        if (x < dimension() && (y + 1) < dimension()) {

            System.out.println("i: " + x + " j: " + y);
            //make a copy of our board
            Board copyBoard = new Board(this);
            copyBoard.swapTiles(x, y, x, y + 1);

            listOfNeighbors.add(copyBoard);

        }

        //3. top     +1, 0
        if (x + 1 < dimension() && y < dimension()) {
            //make a copy of our board
            Board copyBoard = new Board(this);
            copyBoard.swapTiles(x, y, x + 1, y);

            listOfNeighbors.add(copyBoard);

        }
        //4. bottom   -1, 0
        if (x - 1 < dimension() && x - 1 >= 0 && y < dimension()) {
            //make a copy of our board
            Board copyBoard = new Board(this);
            copyBoard.swapTiles(x, y, x - 1, y);

            listOfNeighbors.add(copyBoard);

        }


        return listOfNeighbors;

    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {

        Board copy = new Board(this);


        int i, j, i2, j2;
        //swap the first with the second
        int first = 0;
        int second = dimension();

        //convert first to 2d
        i = first / dimension();
        j = first % dimension();
        i2 = second / dimension();
        j2 = second / dimension();


        //find first tile to swap
        if (emptyTile[0] == i && emptyTile[1] == j) {
            if (i + 1 < (dimension() - 1)) i++;
            else {
                if (j + 1 < (dimension() - 1)) j++;
            }
        }
        //find second tile to swap
        if (emptyTile[0] == i2 && emptyTile[1] == j2) {

            if (i2 + 1 < (dimension() - 1)) i2++;
            else {
                if (j2 + 1 < (dimension() - 1)) j2++;
                else {
                    j2 = (j2 + 1) % dimension();
                }
            }


        }

        copy.swapTiles(i, j, i2, j2);
        System.out.println("swapping " + i + " " + j + " with " + i2 + " " + j2);
        return copy;

    }
    // unit testing (not graded)

    public static void main(String[] args) {

        int[][] testArray = {
                { 1, 2, 3 },
                { 4, 5, 0 },
                { 7, 8, 6 }
        };

        Board b = new Board(testArray);


        Iterable<Board> neigh = new ArrayList<>();
        neigh = b.neighbors();
        for (Board n : neigh) {
            System.out.println(n.toString());
            System.out.println(n.manhattan());
        }
        // System.out.println(b.neighbors());


    }

}
