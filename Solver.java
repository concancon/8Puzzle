import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;


public class Solver {

    private SearchNode searchNode;

    private class SearchNode {
        private Board board;
        private SearchNode prev;
        private int numberOfMoves;

        public SearchNode(Board b) {
            this.board = b;
            this.prev = null;
            this.numberOfMoves = 0;

        }


    }


    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {

        searchNode = new SearchNode(initial);
        MinPQ<Board> pq = new MinPQ<Board>(1);


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

    }

}
