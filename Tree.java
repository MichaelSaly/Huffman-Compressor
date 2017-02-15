package huffman;

public class Tree implements Comparable<Tree>{
	Node root;
	int weighting;
	
	public Tree() {
        root = null;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public void insert(char data) {
        root = insert(root, data);
    }

    private Node insert(Node node, char data) {
        if(node == null)
            node = new Node(data);
        else {
            if(node.getRight() == null)
                node.rightChild = insert(node.rightChild, data);
            else
                node.leftChild = insert(node.leftChild, data);             
        }
        return node;
    }     

    public int countNodes() {
        return countNodes(root);
    }

    private int countNodes(Node r) {
        if(r == null)
            return 0;
        else {
            int l = 1;
            l += countNodes(r.getLeft());
            l += countNodes(r.getRight());
            return l;
        }
    }
    
    String path="error";     //this variable will track the path to the letter we're looking for

    public String getCode(char letter){  //we want the code for this letter

        inOrder(root, letter, "");    //call an inOrder traversal, starting at the root, looking for this letter
        return path;     //return the path that results

    }

    // -------------------------------------------------------------
    private void inOrder(Node localRoot, char letter, String path){ //the path variable tracks the current search path
        if(localRoot != null){ //if root is null we've gone off the edge of the tree - back up
            if(localRoot.letter==letter){
                this.path=path;     //if we've found the letter, note the path - final path = current search path
            }else{
                inOrder(localRoot.leftChild, letter, path+"0"); //go left and add "0" to the current search path
                inOrder(localRoot.rightChild, letter, path+"1");    //go right and add "1" to the current search path
            }
        }
        return; //quit searching this branch of the tree
    }
    
    public int compareTo(Tree object) {
    	
    	//compare the cumulative frequencies of the tree
    	//must return -1,0, or 1
    	
    	if(weighting-object.weighting > 0)
    		return 1;
    	else if(weighting-object.weighting < 0)
    		return -1;
    	else
    		return 0; //return 0 if they're the same
    }
}
