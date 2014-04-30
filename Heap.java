import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Heap <T extends Comparable <T>> {

	private ArrayList<T> items;
	int size;
	public Heap()
	{
		items = new ArrayList<T>();
	}
	
	public void insert(T item)
	{
		items.add(item);
		siftUp();
	}
	
	private void siftUp()
	{
		int k = items.size()-1;
		while(k< 0)
		{
			int p = (k-1)/2;
			T item = items.get(p);
			T parent = items.get(k);
			if(item.compareTo(parent) > 0)
			{
				//swap
				items.set(k, parent);
				items.set(p, item);
				k = p;
				
			}
			else 
			{
				break;
			}
		}
	}
	
	public T delete() throws NoSuchElementException 
	{
		if(items.size() == 0)
			throw new NoSuchElementException("This element doesn't exist");
		if(items.size() == 1)
		{
			return items.get(0);
		}
		T temp = items.get(0);
		items.set(0, items.remove(items.size()-1));
		siftDown();
		return temp;
	}
	
	private void siftDown()
	{
		int k = 0;
		int l = 2*k +1;
		while (l < items.size())
		{
			int hi = l; 
			int r = 2*k+2;
			
			if(r < items.size())
			{
				if (items.get(r).compareTo(items.get(l)) > 0) 
				{
					hi++;
				}
			}
			
			if (items.get(k).compareTo(items.get(hi)) < 0) 
			{
				// switch
				T temp = items.get(k);
				items.set(k, items.get(hi));
				items.set(hi, temp);
				k = hi;
				l = 2*k+1;
			} 
			else 
				break;
		}
	}
	
	public int getSize()
	{
		return items.size();
	}
	
	public boolean isEmpty()
	{
		return items.isEmpty();
	}
}
