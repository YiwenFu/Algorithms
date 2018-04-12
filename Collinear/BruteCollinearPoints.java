import java.util.ArrayList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints 
{
	private ArrayList<LineSegment> line = new ArrayList<LineSegment>();
	private LineSegment[] linesegment;
	private int N = 0;
	
	// finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points)  
    {
    	if (points == null) throw new java.lang.IllegalArgumentException();
    	for (int i = 0; i < points.length; i++)
		{
			if (points[i] == null) throw new java.lang.IllegalArgumentException();
		}
    	for (int i = 0; i < points.length; i++)
		{
			for (int j = i + 1; j < points.length; j++)
			{
				if (points[i].compareTo(points[j]) == 0) throw new java.lang.IllegalArgumentException();
			}
		}
		for (int i = 0; i < points.length; i++)
		{
			for (int j = i + 1; j < points.length; j++)
			{
				double a = points[i].slopeTo(points[j]);
				for (int k = j + 1; k < points.length; k++)
				{
					if (a == points[i].slopeTo(points[k]))
					{
						for (int l = k + 1; l < points.length; l++)
						{
							if (a == points[i].slopeTo(points[l]))
							{
								Point[] Temp = {points[i], points[j], points[k], points[l]};
								Insertion.sort(Temp);
								LineSegment c = new LineSegment(Temp[0], Temp[3]);
								line.add(c);
								N++;
							}
						}
					}
				}		
			}
		}
    }
    
    // the number of line segments
    public int numberOfSegments() 
    {
    	return N;
    }
    // the line segments
    public LineSegment[] segments()     
    {
    	int size = line.size();
		linesegment = new LineSegment[size];
		for (int l = 0; l < line.size(); l++)
			linesegment[l] = line.get(l);
		return linesegment;
    }
    
    public static void main(String[] args) 
    {
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
        BruteCollinearPoints Bcollinear = new BruteCollinearPoints(points);
        for (LineSegment segment : Bcollinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}