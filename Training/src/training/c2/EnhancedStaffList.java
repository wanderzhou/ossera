package training.c2;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 
 * 在双向链表的基础上，增加HashMap，保存节点和对象对应的hash code，用于contains和listAll方法
 * 
 * @author admin
 *
 */
public class EnhancedStaffList {

	int size = 0;
	
	Node first;
	
	Node last;
	
	//HashMap<Node, Integer> hashMap = new HashMap<Node, Integer>();
	HashMap<Integer, Node> hashMap = new HashMap<Integer, Node>();
	
	public void add(Staff staff) {
		Node t = last;
		Node newNode = new Node(t, staff, null);
		last = newNode;
		if(t == null) {
			first = newNode;
		} else {
			t.next = newNode;
		}		
		
		hashMap.put(staff.hashCode(), newNode);
		
		size++;
	}
	
	public boolean contains(Staff staff) {
		return hashMap.containsKey(staff.hashCode());
	}
	
	public Staff get(int index) {
		if(index >= size) {
			return null;
		}
		
		if(index < size / 2) {
			Node n = first;
			for(int i = 0; i < index; i++) {				
				n = n.next;
			}
			return n.item;
		} else {
			Node n = last;
			for(int i = size - 1; i > index; i--) {
				n = n.prev;
			}
			return n.item;
		}
	}
	
	public Staff[] listAll() {
		Staff[] staffs = new Staff[size];
		Iterator<Node> iterator = hashMap.values().iterator();
		int i = 0;
		while(iterator.hasNext()) {
			staffs[i++] = iterator.next().item;
		}
		return staffs;
	}
	
	public void remove(Staff staff) {
		Node node = hashMap.get(staff.hashCode());
		if(node != null) {
			hashMap.remove(staff.hashCode());
			unlink(node);
		}
	}
	
	private void unlink(Node node) {
		Node prev = node.prev;
		Node next = node.next;
		
		if(prev == null) {
			first = next;
		} else {
			prev.next = next;
			node.prev = null;
		}
		
		if(next == null) {
			last = prev;
		} else {
			next.prev = prev;
			node.next = null;
		}	
		
		size--;
	}

	public int getSize() {
		return size;
	}
	
	
}
