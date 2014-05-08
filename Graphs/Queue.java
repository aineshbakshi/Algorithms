

import java.util.NoSuchElementException;

public class Queue<T> {
	
	private Node<T> rear;
	private int size;
	
	public Queue() {
		rear = null;
		size = 0;
	}

	public void enqueue(T item) {
		Node<T> newItem = new Node<T>(item, null);
		if (rear == null) {
			newItem.next = newItem; //point to itself
		} else {
			newItem.next = rear.next;
			rear.next = newItem;
		}
		size++;
		rear = newItem;
	}
	//remove the front and return its data
	public T dequeue() 
	throws NoSuchElementException {
		if (rear == null) {
			throw new NoSuchElementException("queue is empty");
		}
		T data = rear.next.data;
		if (rear == rear.next) {
			rear = null;
		} else {
			rear.next = rear.next.next;
		}
		size--;
		return data;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		size = 0;
		rear = null;
	}
	// peek the first element of the queue to check its value otherwise it will have to dequeue everything 
	public T peek() 
			throws NoSuchElementException {
		if (rear == null) {
			throw new NoSuchElementException("queue is empty");
		}
		return rear.next.data;				
	}
}
