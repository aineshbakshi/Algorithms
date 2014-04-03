package structures;

/*
 * Fields : 
 * 1. Key
 * 2. Balance Factor
 * 3. Height
 * 4. Right Child
 * 5. Left Child
 * 6. Parent 
 */


public class AVLTreeNode {
	int node;
	char bf;
	int height;
	AVLTreeNode rightchild;
	AVLTreeNode leftchild;
	AVLTreeNode parent;
	
	/*
	 * Initialize the constructor 
	 */
	public AVLTreeNode(int node,AVLTreeNode leftChild, AVLTreeNode rightChild, AVLTreeNode parent)
	{
		this.node = node;
		height =0;
		rightchild = rightChild;
		leftchild = leftChild;
		this.parent = parent;
		bf = '-';
	}
	
	/*
	 * toString method to visualize the Node
	 * 
	 */
	public String toString()
	{
		return "(" + height + ", " + node + ", " + bf + ")";
	}
}
