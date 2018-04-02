import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation 
{
    private boolean[] grid;
	private int openCount;
	private final WeightedQuickUnionUF uf;
	private final int N;
	private final int virtualTop;
	// private boolean isPercolate;
	
	public Percolation(int n)// create n-by-n grid,with all sites blocked
	{
		if (n <= 0) throw new IllegalArgumentException();
		N = n;
		uf = new WeightedQuickUnionUF(N * N + 1);
		grid = new boolean[N * N];  // if the value of gird[i] == true, then is not open
		virtualTop = N * N;
		openCount = 0;
		// isPercolate = false;
	}
	
	private int xyTo1D(int row, int col) {
        return (row - 1) * N + (col - 1);
    }
	
	// open site(row open)if it is not open
	public void open(int row, int col)
	{
		if (row < 1 || row > N || col < 1 || col > N) throw new IllegalArgumentException();
		if (isOpen(row, col)) return;	
		
	    int index = xyTo1D(row, col); // offset is the order form 0 to n-1
		grid[index] = true;  // open the grid
		openCount++;
		if (row == 1)
			uf.union(index, virtualTop);
		if (row == N)
			uf.union(index, virtualTop + 1);
		if (row > 1 && isOpen(row - 1, col))// up
		{
			uf.union(index, index - N);
		}
		if (row < N && isOpen(row + 1, col))// down
		{
			uf.union(index, index + N);
		}
		if (col > 1 && isOpen(row, col - 1))// left
		{
			uf.union(index, index - 1);
		}
		if (col < N && isOpen(row, col + 1))// right
		{
			uf.union(index, index + 1);
		}
		//if (row == N && uf.connected(index, virtualTop))
			//isPercolate = true;
	}
	
	public boolean isOpen(int row,int col)
	{
		if (row < 1 || row > N || col < 1 || col > N) throw new IllegalArgumentException();
		return grid[xyTo1D(row, col)];
	}
	
	// full: for example, if you pour water from the top,whether the water can flow here
	public boolean isFull(int row,int col)
	{
		if (row < 1 || row > N || col < 1 || col > N) throw new IllegalArgumentException();
		if (isOpen(row, col))
		{
			return uf.connected(xyTo1D(row, col), virtualTop);
		}
		return false;
	}
	
	public int numberOfOpenSites()
	{
		return openCount;
	}
	
	public boolean percolates()
	{
		return uf.connected(virtualTop, virtualTop + 1);
		//return isPercolate;
	}
	
	/* 
	public static void main(String[] args)//test client(optional)
	{
		n=2;
		Percolation p = new Percolation(n);
		while(true)
		{
			int row = StdRandom.uniform(1, n+1);
			int col = StdRandom.uniform(1, n+1);
			p.open(row, col);
			if(p.percolates())
				break;
		}     
		System.out.println("percolation is " + p.percolates());

		System.out.println(p.numberOfOpenSites());
	} 
	 */
}
