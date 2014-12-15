package training.c2m;

import java.util.HashMap;

/**
 * 
 * @author admin
 *
 * @param <E>
 */
public class HybridList<E extends ListObject> extends BasicList<E> {
	
	static class Node<E> {
		E item;
		
		Node<E> prev;
		Node<E> next;
		
		Node(Node<E> prev, E item, Node<E> next) {
			this.prev = prev;
			this.item = item;
			this.next = next;
		}
	}
	
	Node<E> first;
	Node<E> last;
	
	HashMap<Integer, Node<E>> hashMap = new HashMap<Integer, Node<E>>();
	
	
	public synchronized boolean add(E element) {
		if(contains(element)) {
			return false;
		}
		
		//debug for ReadWriteTest
		System.out.println("add :" + ((ListObject)element).getId());
		
		Node<E> t = last;
		Node<E> newNode = new Node<>(t, element, null);
		last = newNode;
		if(t == null) {
			first = newNode;
		} else {
			t.next = newNode;
		}		
		
		//put into hash map
		hashMap.put(element.id, newNode);
		
		size++;
		return true;
	}

	public synchronized boolean contains(E object) {
		if(object == null) {
			for(Node<E> node = first; node != null; node = node.next) {
				if(node.item == null){
					return true;
				}
			}			
		} else {
			for(Node<E> node = first; node != null; node = node.next) {
				if(node.item.hashCode() == object.hashCode() && object.equals(node.item)){
					return true;
				}
			}
		}
		
		return false;
	}

	public synchronized E get(int index) {
		if(index >= size) {
			return null;
		}
		
		if(index < size / 2) {
			Node<E> n = first;
			for(int i = 0; i < index; i++) {				
				n = n.next;
			}
			return n.item;
		} else {
			Node<E> n = last;
			for(int i = size - 1; i > index; i--) {
				n = n.prev;
			}
			return n.item;
		}
	}

	public synchronized Object[] listAll() {
		Object[] objects = new Object[size];
		int i = 0;
		Node<E> node = first;
		while(node != null) {
			objects[i++] = node.item;
			node = node.next;
		}
		return objects;		
	}

	public synchronized  void remove(Object object) {
		if(object == null) {
			Node<E> n = first;
			while(n != null) {
				if(n.item == null) {
					unlink(n);
					return;
				}
				n = n.next;
			}
		} else {
			Node<E> n = first;
			while(n != null) {
				if(object.equals(n.item)) {
					unlink(n);
					return;
				}
				n = n.next;
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
		
		Node<E> mNode = hashMap.get(node.item.hashCode());
		if(mNode.equals(node)) {
			hashMap.remove(node.item.id);
		}
		
		size--;
	}	
	
}
