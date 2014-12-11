package training.c2;

import java.util.ArrayList;
import java.util.HashMap;


public class HybridList<E> {
	
	private static class Node<E> {
		E item;
		
		Node<E> prev;
		Node<E> next;
		
		Node(Node<E> prev, E item, Node<E> next) {
			this.prev = prev;
			this.item = item;
			this.next = next;
		}
	}
	
	int size;
	
	Node<E> first;
	Node<E> last;
	
	ArrayList<Integer> indexArray = new ArrayList<Integer>(10000);
	HashMap<Integer, Node<E>> hashMap = new HashMap<Integer, Node<E>>();
	
	public boolean add(E element) {
		if(contains(element)) {
			return false;
		}
		
		Node<E> t = last;
		Node<E> newNode = new Node<>(t, element, null);
		last = newNode;
		if(t == null) {
			first = newNode;
		} else {
			t.next = newNode;
		}		
		
		//put into hash map
		indexArray.add(size);
		hashMap.put(size, newNode);
		
		size++;
		return true;
	}	
	
	public boolean contains(E object) {
		if(object == null) {
			for(Node<E> node = first; node != null; node = node.next) {
				if(node.item == null){
					return true;
				}
			}			
		} else {
			for(Node<E> node = first; node != null; node = node.next) {
				if(object.equals(node.item)){
					return true;
				}
			}
		}
		
		return false;
	}
	
	public E get(int index) {
		Integer key = indexArray.get(index);
		return hashMap.get(key).item;
	}	
	
	public Object[] listAll() {
		Object[] objects = new Object[size];
		int i = 0;
		Node<E> node = first;
		while(node != null) {
			objects[i++] = node.item;
			node = node.next;
		}
		return objects;		
	}	
	
	public void remove(Object object) {
		int index = 0;
		if(object == null) {
			Node<E> n = first;
			while(n != null) {
				if(n.item == null) {
					indexArray.remove(index);
					unlink(n);
					return;
				}
				n = n.next;
				index++;
			}
		} else {
			Node<E> n = first;
			while(n != null) {
				if(object.equals(n.item)) {
					indexArray.remove(index);
					unlink(n);
					return;
				}
				n = n.next;
				index++;
			}
		}
	}	
	
	private void unlink(Node<E> node) {
		Node<E> prev = node.prev;
		Node<E> next = node.next;
		
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
