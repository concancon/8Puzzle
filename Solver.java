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

        this.searchNode = new SearchNode(initial);
        MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
        ArrayList<SearchNode> gameTree = new ArrayList<SearchNode>();
        //add the initial searchnode to Gametree and pQ
        gameTree.add(this.searchNode);
        pq.insert(this.searchNode);

        //remove the initial node from PQ
        System.out.println("remove :" + pq.delMin().board);
        System.out.println();

        //add the neighbors to tree
        //FIRST create an array for conversion
        Iterable<Board> neighbors = initial.neighbors();
        numberOfMoves++;


        for (Board n : neighbors) {
            SearchNode newSearchNode = new SearchNode(n);
            gameTree.add(newSearchNode);
            pq.insert(newSearchNode);
        }

        for (int i = 0; i < gameTree.size(); i++) {
            System.out.println(gameTree.get(i).board);
            System.out.println(gameTree.get(i).priority);
        }

        //get the smallest item still on the PQ

        System.out.println("smallest item still on the pQ: \n" + pq.min().board);

        ////////////////////////////repetition
        this.searchNode = new SearchNode(pq.min().board);
        neighbors = pq.min().board.neighbors();
        //remove the initial node from PQ
        System.out.println("remove :" + pq.delMin().board);
        numberOfMoves++;

        for (Board n : neighbors) {
            SearchNode newSearchNode = new SearchNode(n);
            gameTree.add(newSearchNode);
            pq.insert(newSearchNode);
        }
        //get the smallest item still on the PQ

        System.out.println("smallest item still on the pQ: \n" + pq.min().board);

        ////////////////////////////repetition
        this.searchNode = new SearchNode(pq.min().board);
        neighbors = pq.min().board.neighbors();
        //remove the initial node from PQ
        System.out.println("remove :" + pq.delMin().board);
        numberOfMoves++;

        for (Board n : neighbors) {
            SearchNode newSearchNode = new SearchNode(n);
            gameTree.add(newSearchNode);
            pq.insert(newSearchNode);
        }
        //get the smallest item still on the PQ

        System.out.println("smallest item still on the pQ: \n" + pq.min().board);

        ////////////////////////////repetition
        this.searchNode = new SearchNode(pq.min().board);
        neighbors = pq.min().board.neighbors();
        //remove the initial node from PQ
        System.out.println("remove :" + pq.delMin().board);
        numberOfMoves++;

        for (Board n : neighbors) {
            SearchNode newSearchNode = new SearchNode(n);
            gameTree.add(newSearchNode);
            pq.insert(newSearchNode);
        }
        //get the smallest item still on the PQ

        System.out.println("smallest item still on the pQ: \n" + pq.min().board);

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
