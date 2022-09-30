package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import java.util.ArrayList;
import java.util.Stack;

public class Solver {
    private MinPQ<SearchNode> pq = new MinPQ<>();
    private ArrayList<WorldState> ans = new ArrayList<>();
    private int cntMoves = 0;

    private class SearchNode implements Comparable<SearchNode>{
        private WorldState now;
        private int reachNow;
        private SearchNode prev;
        private int distToGoal;

        public SearchNode(WorldState n){
            now = n;
            prev = null;
            reachNow = 0;
        }

        public SearchNode(WorldState n, SearchNode p){
            this.now = n;
            this.prev = p;
            this.reachNow = p.reachNow + 1;
        }

        @Override
        public int compareTo(SearchNode o){
            return this.reachNow + this.now.estimatedDistanceToGoal() - o.reachNow - o.now.estimatedDistanceToGoal();
        }
    }

    public Solver(WorldState initial){
        pq.insert(new SearchNode(initial));

        while(true){
            SearchNode s = pq.delMin();

            if(s.now.isGoal()){
                solverHelper(s);
                break;
            }else{
                for(WorldState ws : s.now.neighbors()){
                    if(s.prev == null || !ws.equals(s.prev.now)){
                        pq.insert(new SearchNode(ws, s));
                    }
                }
            }
        }
    }

    private void solverHelper(SearchNode node){
        Stack<WorldState> s = new Stack<>();
        while(node != null){
            s.push(node.now);
            node = node.prev;
        }
        while(!s.isEmpty()){
            cntMoves += 1;
            ans.add(s.pop());
        }
        //let the first SearchNode's cntMoves = 0
        cntMoves -= 1;
    }

    public int moves(){
        return cntMoves;
    }

    public Iterable<WorldState> solution(){
        return ans;
    }
}
