package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private Maze maze;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = m.xyTo1D(1, 1);
        edgeTo[s] = s;
        distTo[s] = 1;
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        solveHelper(s);
    }

    // Helper methods go here
    private void solveHelper(int s){
        marked[s] = true;
        announce();

        for(int vertex : maze.adj(s)){
            if(!marked[vertex]){
                edgeTo[vertex] = s;
                distTo[vertex] = s + 1;
                solveHelper(vertex);
            }else if(marked[vertex] &&edgeTo[s] != vertex ){
                edgeTo[vertex] = s;
                announce();
                return;
            }
        }
    }
}

