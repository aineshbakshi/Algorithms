package structures;

public class AVLTree {
	
	// Declaring the root
	AVLTreeNode root ;
	
	/*
	 * Initializing the Tree
	 */
	
	public AVLTree()
	{
		root =null;
	}
	
	/*
	 * Search Method for a particular Node given it's key
	 * Returns null if element not found
	 */
	
	public AVLTreeNode search(int node)
	{
		if(root == null)
		{
			System.out.println("Element not found");
			return root;
		}
		AVLTreeNode search = root;
		while(search!=null)
		{
			if(search.node == node)
				return search;
			if(search.node>node)
			{
				search = search.leftchild;
			}
			else
			{
				search = search.rightchild;
			}
		}
		if(search == null)
		{
			System.out.println("Element not found");
			
		}
		return null;
	}
	
	/*
	 * Insert Function for AVL Tree
	 * Takes arguments key, right child, left child and parent
	 * Performs rotations to keep tree balanced 
	 * O(log n)
	 * 
	 */
	public void insert(int node)
	{
		AVLTreeNode insert = new AVLTreeNode(node, null, null, null);
		AVLTreeNode search = root;
		AVLTreeNode prev = null;
		// IF there are no nodes in the tree
		if(root == null)
		{
			root = insert;
			root.parent =null;
			root.height = 0;
		}
		// Search for empty spot to insert a node similar to a normal BST
		
		while(search!= null)
		{
			prev = search;
			if(search.node > node)
			{
				search = search.leftchild;
			}
			else if(search.node < node)
			{
				search = search.rightchild;
			}
			else
			{
				System.out.println("Item already exits");
				return;
			}
		}
		
		// Inserting the node once found 
		
		if(prev !=null)
		{
			// Determining whether left or right child
			if(prev.node < node)
			{	
				prev.rightchild = insert;
				insert.parent = prev;
				setHeight(prev);
				recursiveBalance(prev);
			}
			else
			{
				prev.leftchild = insert;
				insert.parent = prev;
				setHeight(prev);
				recursiveBalance(prev);
			}
			
		}
		
	}
	
	public void delete(AVLTreeNode target)
	{
		AVLTreeNode p = null;
		AVLTreeNode x = root;
		
		while(x != null)
		{
			p = x;
			if(x.node == target.node)
			{
				break;
			}
			x = target.node > x.node? x.leftchild : x.rightchild;
		}
		if(x == null)
		{
			System.out.println("Object Not Found");
			return;
		}
		// Delete a node that has two children:
		if(x.leftchild != null && x.rightchild!= null)
		{
			AVLTreeNode next = x.rightchild;
			while(next.leftchild != null)
			{
				p = next;
				next = next.leftchild;
			}
			x.node = next.node;
			x = next;
			//fall to next case
		}
		if(x.leftchild == null && x.rightchild == null)
		{
			if(p == null)
			{
				root=null;
				return;
			}
			if(p.leftchild == x)
				p.leftchild = null;
			else
				p.rightchild =null;
			recursiveBalance(p);
			return;
		}
		if(x.leftchild != null || x.rightchild != null)
		{
			if(p == null)
			{
				if(x.leftchild!= null)
					root = x.leftchild;
				else
					root = x.rightchild;
				recursiveBalance(p);
				return;
			}
			if(x == p.leftchild)
			{
				p.leftchild = x.leftchild == null? x.rightchild: x.leftchild;
			}
			else
				p.rightchild= x.leftchild == null? x.rightchild: x.leftchild;
			recursiveBalance(p);
		}
	}
	
	/*
	 * Recursive method to balance each node till a rotation or till the root
	 * Balance factor for each node cannot exceed a magnitude of 1 in order to remain AVl
	 * 
	 */
	
	public void recursiveBalance(AVLTreeNode p)
	{
		int bal ;
		setHeight(p);
		// If it is a leaf node
		if(p.leftchild == null && p.rightchild == null)
			bal = 0;
		// If it has one subtree 
		else if(p.leftchild == null)
		{
			bal = -1 - p.rightchild.height;
		}
		else if(p.rightchild == null)
		{
			bal = p.leftchild.height +1;
		}
		// If it has two subtrees 
		else
			bal = p.leftchild.height - p.rightchild.height;
		//System.out.println("bf " + bal );
		if(bal ==0)
		{	
			p.bf = '-';
		}
		else if(bal == 1)
		{
			p.bf = '/';
		}
		else if(bal == -1)
		{
			p.bf = '\\';
		}
		
		/*
		 *  Encountering an unbalanced Node
		 *  p is current node
		 *  x is root of taller subtree of p
		 *  r is root of taller subtree of x
		 */
		
		else
		{ 
			AVLTreeNode x = getHeight(p.leftchild) >= getHeight(p.rightchild) ? p.leftchild : p.rightchild;
			
			// Case 1 p and x have same bf
			
			if(p.bf == x.bf)
			{
				// Determine left or right rotation
				if(p.bf == '/')
				{
					rotateRight(p,x);
					setBalFactor(p);
					setBalFactor(x);
					//return;
				}
				else
				{
					rotateLeft(p,x);
					setBalFactor(p);
					setBalFactor(x);
					//return;
				}
			}
			
			// Case 2: p and x have different balance factors 
			else
			{
				AVLTreeNode r = getHeight(x.leftchild) >= getHeight(x.rightchild)? x.leftchild : x.rightchild;
				
				// Double rotation 
				
				if(x.bf == '/')
				{
					rotateRight(x,r);
					setBalFactor(x);
					setBalFactor(r);
					rotateLeft(p,r);
					setBalFactor(p);
					setBalFactor(r);
					//return;
				}
				else
				{
					rotateLeft(x,r);
					setBalFactor(x);
					setBalFactor(r);
					rotateRight(p,r);
					setBalFactor(p);
					setBalFactor(r);
					//return;
				}
			}
		}
		// End recursion at root
		
		if(p == root)
			return;
		
		//Recursive call
		
		recursiveBalance(p.parent);
	}
	
	/*
	 * Takes as argument two nodes a and b
	 * Rotates counter clockwise
	 * Adjusts heights and balance factors 
	 */
	public void rotateLeft(AVLTreeNode a, AVLTreeNode b)
	{
		AVLTreeNode parentNode = a.parent;
		b.parent = parentNode;
		if(a.parent == null)
		{
			root = b;
		}
		else{
			if(parentNode.node > a.node)
				parentNode.leftchild = b;
			else 
				parentNode.rightchild = b;
		}
		if(b.leftchild!=null)
		{	
			a.rightchild = b.leftchild;
			a.rightchild.parent =a;
		}
		else 
		{
			a.rightchild =null;
		}
		b.leftchild = a;
		a.parent =b;
		setHeight(a);
		setHeight(b);
		//a.bf = '-';
		//b.bf = '-';

	}
	
	/*
	 * Takes as argument two nodes a and b
	 * Rotates clockwise
	 * Adjusts heights and balance factors 
	 */
	
	public void rotateRight(AVLTreeNode a, AVLTreeNode b)
	{
		AVLTreeNode parentNode = a.parent;
		b.parent = parentNode;
		if(a.parent == null)
		{
			//a is root
			root = b;
		}
		else
		{
			if(parentNode.node > a.node)
				parentNode.leftchild = b;
			else 
				parentNode.rightchild = b;
		}
		if(b.rightchild!=null)
		{
			a.leftchild = b.rightchild;
			a.leftchild.parent =a;
		}
		else {
			a.leftchild = null;
		}
		b.rightchild = a;
		a.parent =b;
		setHeight(a);
		setHeight(b);
		//a.bf = '-';
		//b.bf = '-';

	}
	
	/*
	 * In order traversal of AVL tree
	 * 
	 */
	public void traverse (AVLTreeNode r)
	{
		if(r == null)
			return;
		traverse(r.leftchild);
		System.out.println(r);
		traverse(r.rightchild);
	}
	
	/*
	 * Helper functions:
	 * setHeight determines current height of a node based on it's subtrees 
	 */
	public void setHeight(AVLTreeNode a)
	{
		if(a.leftchild== null && a.rightchild == null)
		{	
			a.height = 0;
			return;
		}
		if(a.leftchild == null)
		{
			a.height = a.rightchild.height +1;
			return;
		}
		if(a.rightchild == null)
		{
			a.height = a.leftchild.height +1;
			return;
		}
		a.height = a.leftchild.height >= a.rightchild.height ? a.leftchild.height +1: a.rightchild.height +1;
		return;
	}
	
	/*
	 * Helper functions 
	 * getHeight returns current height of a node based on it's subtrees 
	 */
	public int getHeight(AVLTreeNode a)
	{
		if(a == null)
			return -1;
		if(a.leftchild== null && a.rightchild == null)
		{	
			return  0;
		}
		if(a.leftchild == null)
		{
			return a.rightchild.height +1;
		}
		if(a.rightchild == null)
		{
			return a.leftchild.height +1;
		}
		a.height = a.leftchild.height >= a.rightchild.height ? a.leftchild.height +1: a.rightchild.height +1;
		return a.height;
	}
	public void setBalFactor(AVLTreeNode a)
	{
		int c = getHeight(a.leftchild) - getHeight(a.rightchild);
		if(c == 0)
		{
			a.bf = '-';
			return;
		}
		if(c < 0)
		{
			a.bf = '\\';
			return;
		}
		if(c>0)
		{
			a.bf = '/';
		}
	}
	
	/*
	 * Returns the root of the AVL tree
	 * 
	 */
	public AVLTreeNode getRoot()
	{
		return root;
	}
}
