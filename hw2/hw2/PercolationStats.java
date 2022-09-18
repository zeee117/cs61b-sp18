package hw2;

import edu.princeton.cs.introcs.StdStats;
import edu.princeton.cs.introcs.StdRandom;

public class PercolationStats{
    double[] x;
    int T;

    public PercolationStats(int N, int T, PercolationFactory pf){
        if(N < 0 || T < 0){
            throw new IllegalArgumentException();
        }
        x = new double[T];
        this.T = T;
        for(int i=0; i<T; i++){
            Percolation p = pf.make(N);
            int cnt = 0;
            while(!p.percolates()){
                p.open(StdRandom.uniform(N), StdRandom.uniform(N));
                cnt += 1;
            }
            x[i] = p.numberOfOpenSites() / N * N;
        }
    }

    public double mean(){
        return StdStats.mean(x);
    }

    public double stddev(){
        return StdStats.stddev(x);
    }

    public double confidenceLow(){
        return StdStats.mean(x) - 1.96 * StdStats.stddev(x) / Math.sqrt(T);
    }

    public double confidenceHigh(){
        return StdStats.mean(x) + 1.96 * StdStats.stddev(x) / Math.sqrt(T);
    }
}
