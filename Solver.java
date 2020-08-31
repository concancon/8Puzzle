import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Collections;


public class Solver {


    private ArrayList<Board> solution;

    private class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private SearchNode prev;
        private int priority;
        private int numberOfMoves;

        public SearchNode(Board b) {
            this.board = b;
            this.prev = null;
            this.numberOfMoves = 0;
            this.priority = b.manhattan();

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
        SearchNode searchNode = new SearchNode(initial);
        ////////////////////////////////

        MinPQ<SearchNode> otherPq = new MinPQ<SearchNode>();
        ArrayList<SearchNode> otherGameTree = new ArrayList<SearchNode>();
        SearchNode otherSearchNode = new SearchNode(initial.twin());


        //shared solution field for case unsolvable or solvable
        this.solution = new ArrayList<Board>();

        //add the initial searchnode to Gametree and pQ
        gameTree.add(searchNode);
        pq.insert(searchNode);
        ////////////////////////

        otherGameTree.add(otherSearchNode);
        otherPq.insert(otherSearchNode);


        //add the neighbors to tree
        //FIRST create an array for conversion
        Iterable<Board> neighbors;
        /////////////////////////
        Iterable<Board> otherNeighbors;


        ////////////////////////////repetition

        while (pq.min().board.isGoal() == otherPq.min().board.isGoal()) {
            searchNode = pq.delMin();
            neighbors = searchNode.board.neighbors();
            ///////////////////////////////////////

            otherSearchNode = otherPq.delMin();
            otherNeighbors = otherSearchNode.board.neighbors();


            for (Board n : neighbors) {


                SearchNode newSearchNode = new SearchNode(n);
                newSearchNode.prev = searchNode;


                if (newSearchNode.prev.prev != null) {
                    if (!newSearchNode.board.equals(searchNode.prev.board)) {


                        newSearchNode.numberOfMoves = searchNode.numberOfMoves + 1;
                        newSearchNode.priority = newSearchNode.numberOfMoves + n.manhattan();
                        pq.insert(newSearchNode);
                        gameTree.add(newSearchNode);

                    }
                }
                else { // for the inital's neighbors
                    newSearchNode.numberOfMoves = searchNode.numberOfMoves + 1;
                    newSearchNode.priority = newSearchNode.numberOfMoves + n.manhattan();


                    pq.insert(newSearchNode);
                    gameTree.add(newSearchNode);
                }

            }


            for (Board n : otherNeighbors) {


                SearchNode newSearchNode = new SearchNode(n);
                newSearchNode.prev = otherSearchNode;


                if (newSearchNode.prev.prev != null) {
                    if (!newSearchNode.board.equals(otherSearchNode.prev.board)) {


                        newSearchNode.numberOfMoves = otherSearchNode.numberOfMoves + 1;
                        newSearchNode.priority = newSearchNode.numberOfMoves + n.manhattan();
                        otherPq.insert(newSearchNode);
                        otherGameTree.add(newSearchNode);

                    }
                }
                else { // for the inital's neighbors
                    newSearchNode.numberOfMoves = otherSearchNode.numberOfMoves + 1;
                    newSearchNode.priority = newSearchNode.numberOfMoves + n.manhattan();


                    otherPq.insert(newSearchNode);
                    otherGameTree.add(newSearchNode);
                }

            }


        }


        int indexOfWinner = gameTree.indexOf(pq.min());
        while (gameTree.get(indexOfWinner).prev != null) {
            solution.add(gameTree.get(indexOfWinner).prev.board);
            gameTree.set(indexOfWinner, gameTree.get(indexOfWinner).prev);
        }


        Collections.reverse(solution);
        solution.add(pq.min().board); // add the goalboard to the end of the solution array
        //System.out.println("number of moves: " + moves());
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return true;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return this.solution.size() - 1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {


        return this.solution;
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
        //StdOut.println("new board of size" + initial.dimension());


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
