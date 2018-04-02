import java.util.Iterator;

public class Deque<Item> implements Iterable<Item>
{
	private Node first;
	private Node last;
	private int N;
	private class Node
	{
		Item item;
		Node next;
		Node previous;  //double-ended queue 
	}
	//construct an empty deque
	public Deque()
	{
		first = null;
		last = null;
	} 
	public boolean isEmpty()  { return N == 0;	}
	
	public int size()  { return N; }
	
	// add the item to the front
	public void addFirst(Item item) 
	{
		if(item == null) throw new java.lang.IllegalArgumentException();
		Node second = first;
		first = new Node();
		first.item = item;
		first.previous = null;
		if(isEmpty()) 
		{
			first.next = null;
			last = first;
		}
		else
		{
			first.next = second;
			second.previous = first;
		}
		N++;
	}
	 // add the item to the end
	public void addLast(Item item) 
	{
		if(item == null) throw new java.lang.IllegalArgumentException();
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if(isEmpty()) 
		{
			last.previous = null;
			first = last;
		}
		else 
		{
			last.previous = oldlast;
			oldlast.next = last;
		}
		N++;
	}
	// remove and return the item from the front
	public Item removeFirst() 
	{
		if(isEmpty()) throw new java.util.NoSuchElementException();
		Item item = first.item;
		first  = first.next;
		N--;
		if(isEmpty()) last = null;  //if empty after delete first
		else first.previous = null;
		return item;
	}
	 // remove and return the item from the end
	public Item removeLast() 
	{
		if(isEmpty()) throw new java.util.NoSuchElementException();
		Item item = last.item;
		last = last.previous;
		N--;
		if(isEmpty()) first = null;
		else last.next = null;
		return item;
	}
	 // return an iterator over items in order from front to end
	public Iterator<Item> iterator() //return an iterator over items in order form front to end
	{
		return new ListIterator();
	}
    private class ListIterator implements Iterator<Item>
    {
    	private Node current = first;
    	public boolean hasNext()
    	{ return current != null;}
    	public void remove()
    	{
    		throw new java.lang.UnsupportedOperationException();
    	}
    	public Item next()
    	{
    		if(!hasNext()) throw new java.util.NoSuchElementException();
    		Item item = current.item;
    		current = current.next;
    		return item;
    	}
    }
	
	// unit testing (optional)
	public static void main(String[] args)
	{
		Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(3);
        deque.removeFirst();
        Iterator<Integer> i = deque.iterator();
        while (i.hasNext())
        {
            Integer s = i.next();
            System.out.println(s);
        }
	}
}
