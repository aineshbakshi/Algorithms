package apps;

import structures.AVLTree;

public class AVLTreeDriver {
	public static void main (String args [])
	{
		AVLTree avl = new AVLTree ();
		
		/* Cool sample
		
		avl.insert(15);
		avl.insert(4);
		avl.insert(20);
		avl.insert(3);
		avl.insert(8);
		avl.insert(6);
		//avl.delete(avl.getRoot());
		
		*/
		avl.insert(5);
		avl.insert(3);
		avl.insert(8);
		avl.insert(7);
		avl.insert(9);
		avl.insert(6);
		avl.traverse(avl.getRoot());
	}
}
