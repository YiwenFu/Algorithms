import java.util.Iterator;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation 
{
	public static void main(String[] args)
	{
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);
		while (!StdIn.isEmpty())
		{
			String item = StdIn.readString();
			rq.enqueue(item);
		}
		Iterator<String> i = rq.iterator();
		for (int j = 0; j < k; j++)
		{ 
			if (i.hasNext())
			StdOut.println(i.next()); 
		}
	}
}