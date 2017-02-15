/*
 * Date: 15/02/2017
 * Michael Saly
 */

package huffman;

public class Node {
	Node leftChild, rightChild;
	char letter;
	
	public Node() {
		leftChild = null;
		rightChild = null;
		letter = '!';
	}
	
	public Node(char data) {
        leftChild = null;
        rightChild = null;
        letter = data;
    }
	
	public void setLeft(Node n) {
        leftChild = n;
    }
	
    public void setRight(Node n) {
        rightChild = n;
    }

    public Node getLeft() {
        return leftChild;
    }

    public Node getRight() {
        return rightChild;
    }

    public void setData(char data) {
        letter = data;
    }

    public int getData() {
        return letter;
    }     
}
