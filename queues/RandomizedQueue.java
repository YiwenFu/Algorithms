import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue <Item> implements Iterable<Item>
{
	private Item[] a ;
	private int N = 0;
	public RandomizedQueue() 
	{ 
		a = (Item[]) new Object[10];
	}
	 
	public boolean isEmpty()  { return  N == 0;} 
	public int size() { return N;}
	 
	private void resize(int max)
	{
		Item[] temp = (Item[]) new Object[max];		
		for (int i = 0; i < N; i++)
			temp[i] = a[i];
		a = temp;
	}
	
	// add the item
	public void enqueue(Item item)
	 {
		 if (item == null) throw new java.lang.IllegalArgumentException();
		 if (N == a.length) 
			 resize(2 * a.length);
		 a[N++] = item;
	 }
	// remove and return a random item
	public Item dequeue()     
	 {
		 if(isEmpty()) throw new java.util.NoSuchElementException();
		 int randomIndex = StdRandom.uniform(N);
		 Item item = a[randomIndex];
		 a[randomIndex] = a[--N];
		 a[N] = null;
		 if (N > 0 && N == a.length / 4)
			 resize(a.length / 2);
		 return item;
	 }
	// return a random item (but do not remove it)
	public Item sample()        
	 {
		 if(N == 0) throw new java.util.NoSuchElementException();
		 int randomIndex = StdRandom.uniform(N);
		 Item item = a[randomIndex];
		 return item;
	 }
	// return an independent iterator over items in random order
	public Iterator<Item> iterator()      
	 {
		 return new RandomizedQueueIterator();
	 }
	private class RandomizedQueueIterator implements Iterator<Item>
     {
    	public boolean hasNext()
    	{ return N > 0;}
    	public void remove()
    	{
    		throw new java.lang.UnsupportedOperationException();
    	}
    	public Item next()
    	{
    		if(!hasNext()) throw new java.util.NoSuchElementException();
    		return dequeue();
    	}
     }
	/*
	public static void main(String[] args) {
        RandomizedQueue<Integer> deque = new RandomizedQueue<Integer>();
        for (int j = 0; j < 16; j++)
        {
        	deque.enqueue(j);
        }
        Iterator<Integer> i = deque.iterator();
        while (i.hasNext())
        {
            Integer s = i.next();
            System.out.println(s);
        }
    }
    */
}
