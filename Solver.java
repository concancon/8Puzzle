import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;


public class Solver {

    public static int numberOfMoves = 0;
    private SearchNode searchNode;


    private class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private SearchNode prev;
        private int priority;


        public SearchNode(Board b) {
            this.board = b;
            this.prev = null;
            this.priority = b.manhattan() + numberOfMoves;

        }


        public int compareTo(SearchNode o) {
            if (this.priority == o.priority) return 0;
            else if (this.priority < o.priority) return -1;
            else return 1;
        }
    }


    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {

        // INITIALIZATION
        MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
        ArrayList<SearchNode> gameTree = new ArrayList<SearchNode>();
        this.searchNode = new SearchNode(initial);

        //add the initial searchnode to Gametree and pQ
        gameTree.add(this.searchNode);
        pq.insert(this.searchNode);

        //add the neighbors to tree
        //FIRST create an array for conversion
        Iterable<Board> neighbors;

        /////////////////////

        Board previous = initial;
        ////////////////////////////repetition
        while (!pq.min().board.isGoal()) {
            this.searchNode = pq.min();
            neighbors = pq.min().board.neighbors();
            // remove the initial node from PQ
            numberOfMoves++;
            System.out.println("remove : \n" + pq.delMin().board);

            for (Board n : neighbors) {


                SearchNode newSearchNode = new SearchNode(n);
                System.out.println("Priority: " + newSearchNode.priority);
                //System.out.println("previous is: " + previous.board.toString());

                SearchNode previousSearchNode = new SearchNode(this.searchNode.board);
                newSearchNode.prev = previousSearchNode;


                if (!newSearchNode.board.equals(previous)) {
                    pq.insert(newSearchNode);
                    gameTree.add(newSearchNode);
                    System.out.println("new neigh \n" + newSearchNode.board.toString());
                    //System.out.println("Previous is: \n" + newSearchNode.prev.board);
                }
            }

            previous = pq.min().prev.board;
            System.out.println("smallest item still on the pQ: \n" + pq.min().board);
            System.out.println("its previous is: \n" + pq.min().prev.board);
        }

        System.out.println("number of moves: " + (numberOfMoves));
        System.out.println(pq.min().board.isGoal());
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return true;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return 4;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {

        return new ArrayList<Board>();
    }

    // test client (see below)
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

 /*       // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }*/
    }

}
