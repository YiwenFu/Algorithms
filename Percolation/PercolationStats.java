import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.lang.Math;
public class PercolationStats 
{
	private double resultmean;
	private double resultstddev;
	private double[] OpenSites;
	private final int Times;
	private final int N;
	private double resultconfidenceLo;
	private double resultconfidenceHi;
	
	// perform trials independent experiments on an n-by-n grid
	public PercolationStats(int n, int trials)  
	{
		if (n <= 0 || trials <= 0) throw new java.lang.IllegalArgumentException();
		N = n;
		Times = trials;
		OpenSites = new double[Times];
		if (N == 1)
		{
			resultmean = 1;
			resultstddev = 0;
			resultconfidenceLo = Double.NaN;
			resultconfidenceHi = Double.NaN;
		}
		else
		{
			compute();
		}
	}  
	
	private void compute()
	{
		for (int i = 0; i < Times; i++)
		{
			Percolation per = new Percolation(N);
			while(!per.percolates())
			{
				per.open(StdRandom.uniform(1, N + 1), StdRandom.uniform(1, N + 1));
			}
			OpenSites[i] = (double)(per.numberOfOpenSites()) / (N * N);
		}
	}
	
	// sample mean of percolation threshold
	public double mean()
	{
		return resultmean = StdStats.mean(OpenSites);
	}
	// sample standard deviation of percolation threshold
    public double stddev() 
    {
    	return resultstddev = StdStats.stddev(OpenSites);
    }
    // low  endpoint of 95% confidence interval
    public double confidenceLo()  
    {
    	resultconfidenceLo = resultmean - 1.95 * resultstddev / Math.sqrt(Times);
    	return resultconfidenceLo;
    }
    // high endpoint of 95% confidence interval
    public double confidenceHi() 
    {
    	resultconfidenceHi = resultmean + 1.95 * resultstddev / Math.sqrt(Times);
    	return resultconfidenceHi;
    }
    
    // test client (described below)
    public static void main(String[] args)  
    {
		PercolationStats percolationStats = new PercolationStats(100, 100);
		System.out.println("mean     = " + percolationStats.mean());
		System.out.println("stddev   = " + percolationStats.stddev());
		System.out.println("95% confidence interval  = [" + percolationStats.confidenceLo() + "," + percolationStats.confidenceHi() + "]");
    }
}
