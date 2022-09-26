package lab11.graphs;

import java.util.Queue;
import java.util.ArrayDeque;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private Queue<Integer> q;


    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        q = new ArrayDeque<>();
        s = m.xyTo1D(sourceX, sourceY);
        t = m.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        q.add(s);
        // Add more variables here!
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        int vertex = q.poll();
        marked[vertex] = true;
        announce();

        if(vertex == t){
            return;
        }
        for(int v : maze.adj(vertex)){
            if(!marked[v]){
                q.add(v);
                distTo[v] = distTo[vertex] + 1;
                edgeTo[v] = vertex;
            }
        }
        bfs();
    }


    @Override
    public void solve() {
        bfs();
    }
}

