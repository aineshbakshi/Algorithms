

/**
 * Generic node, holds any type of object
 * @author seshv
 *
 */
/*
 * Can hold any type of data variable is for type, parameterize the class with a type name 
 * All the 'T' inside the class match with it
 * Node requires <> at all places apart from the constructor
 */
public class Node<T> {
	public T data;
	public Node<T> next;
	// constructor name does not get the <T>
	public Node(T data, Node<T> next) {
		this.data = data;
		this.next = next;
	}
}

