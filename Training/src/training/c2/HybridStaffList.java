package training.c2;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * 
 * 双向链表的基础上，增加LinkedHashMap，用于顺序存储节点和对象的hash code，实现get方法
 * 
 * @author admin
 *
 */
public class HybridStaffList {

	int size = 0;
	
	Node first;
	
	Node last;
	
	LinkedHashMap<Integer, Node> map = new LinkedHashMap<Integer, Node>();
	
	public void add(Staff staff) {
		Node t = last;
		Node newNode = new Node(t, staff, null);
		last = newNode;
		if(t == null) {
			first = newNode;
		} else {
			t.next = newNode;
		}		
		
		map.put(staff.hashCode(), newNode);
		
		size++;
	}
	
	public boolean contains(Staff staff) {
		return map.containsKey((staff.hashCode()));
	}
	
	public Staff get(int index) {
		//return ((Entry<Integer, Node>)(map.entrySet().toArray()[index])).getValue().item;
		/*if(index > size - 1) {
			return null;
		}
		
		int i = 0;
		Iterator<Node> iterator = map.values().iterator();
		while(iterator.hasNext() && i < index) {
			iterator.next();
			i++;
		}
		iterator.hasNext();
		return iterator.next().item;*/
		
		if(index > size - 1) {
			return null;
		}
		
		int i = 0;
		Iterator<Integer> iterator = map.keySet().iterator();
		while(iterator.hasNext() && i < index) {
			iterator.next();
			i++;
		}
		iterator.hasNext();
		return map.get(iterator.next()).item;		
		
	}
	
	public Staff[] listAll() {
		Staff[] staffs = new Staff[size];
		Iterator<Node> iterator = map.values().iterator();
		int i = 0;
		while(iterator.hasNext()) {
			staffs[i++] = iterator.next().item;
		}
		return staffs;
	}
	
	public void remove(Staff staff) {
		Node node = map.get(staff.hashCode());
		if(node != null) {
			unlink(node);
			
			map.remove(staff.hashCode());
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
