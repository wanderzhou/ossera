package training.c2;

/**
 * 
 * 
 * @author admin
 *
 */
public class Node {
	Node prev;
	Staff item;
	Node next;
	
	public Node(Node prev, Staff item, Node next) {
		this.prev = prev;
		this.item = item;
		this.next = next;
	}

}
