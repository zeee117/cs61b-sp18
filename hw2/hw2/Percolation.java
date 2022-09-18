package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grids;
    private int N;
    private int openSize;
    private WeightedQuickUnionUF wqu;
    private final int upperBound;
    private final int lowerBound;

    public Percolation(int N){
        if(N <= 0){
            throw new IllegalArgumentException();
        }
        grids = new int[N][N];
        this.N = N;
        wqu = new WeightedQuickUnionUF(N * N + 2);
        upperBound = N * N;
        lowerBound = N * N + 1;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                grids[i][j] = -1;
            }
        }
        for(int i=0; i<N; i++){
            wqu.union(upperBound, xyToNum(0, i));
            wqu.union(lowerBound, xyToNum(N - 1, i));
        }
    }

    private int xyToNum(int row, int col){
        return row*(N - 1) + col;
    }

    private void errorHelper(int row, int col){
        if(row < 0 || row >= N || col < 0 || col >= N){
            throw new IndexOutOfBoundsException();
        }
    }

    private void unionHelper(int num1, int row, int col){
        if(row >= N || col >= N){
            return;
        }else if(row < 0 || col < 0){
            return;
        }
        if(grids[row][col] >= 0){
            wqu.union(num1, xyToNum(row, col));
        }
    }

    public void open(int row, int col){
        errorHelper(row, col);
        if(grids[row][col] >= 0){
            return;
        }
        int num1 = xyToNum(row, col);
        unionHelper(num1, row - 1, col);
        unionHelper(num1, row + 1, col);
        unionHelper(num1, row, col - 1);
        unionHelper(num1, row, col + 1);

        grids[row][col] = 0;
        openSize += 1;
    }

    public boolean isOpen(int row, int col){
        errorHelper(row, col);
        if(grids[row][col] >= 0){
            return true;
        }
        return false;
    }

    public boolean isFull(int row, int col){
        errorHelper(row, col);
        if(!isOpen(row, col)){
            return false;
        }
        return wqu.connected(upperBound, xyToNum(row, col));
    }

    public int numberOfOpenSites(){
        return openSize;
    }

    public boolean percolates(){
        if(openSize == 0){
            return false;
        }
        return wqu.connected(upperBound, lowerBound);
    }

    public static void main(String[] args){

    }
}
