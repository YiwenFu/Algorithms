
import java.util.Arrays;
import java.util.ArrayList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints 
{
	private ArrayList<LineSegment> line = new ArrayList<LineSegment>();
	private Point[] P;

	// finds all line segments containing 4 or more points
	public FastCollinearPoints(Point[] points) 
	{
		if (points == null) throw new java.lang.IllegalArgumentException();
		int N = points.length;
		for (int i = 0; i < N; i++)
		{
			if (points[i] == null) throw new java.lang.IllegalArgumentException();
		}
		for (int i = 0; i < N; i++)
		{
			for (int j = i + 1; j < N; j++)
			{
				if (points[i].compareTo(points[j]) == 0) throw new java.lang.IllegalArgumentException();
			}
		}
		this.P = Arrays.copyOf(points, N);
		for (int i = 0; i < N - 3; i++)
		{
			Arrays.sort(P);
			Arrays.sort(P, P[i].slopeOrder());
		    for (int first = 1, last = 2; last < N; last++)
		    {
		    	while (last < N && Double.compare(P[0].slopeTo(P[first]), P[0].slopeTo(P[last])) == 0)
		    		last++;
		    	if (last - first >= 3 && P[0].compareTo(P[first]) < 0)
		    	{
		    		line.add(new LineSegment(P[0], P[last - 1]));
		    	}
			    first = last;
		    }
		}
	}
	// the number of line segments
	public int numberOfSegments()
	{
		return line.size();
	}

	// the line segments
	public LineSegment[] segments()
	{
		return line.toArray(new LineSegment[line.size()]);
	}
	
	public static void main(String[] args) {
		
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
