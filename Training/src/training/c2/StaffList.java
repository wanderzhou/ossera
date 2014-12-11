package training.c2;

/**
 * 
 * @author admin
 *
 */
public class StaffList {

	int size;
	
	Node first;
	
	Node last;
	
	public void add(Staff staff) {
		Node t = last;
		Node newNode = new Node(t, staff, null);
		last = newNode;
		if(t == null) {
			first = newNode;
		} else {
			t.next = newNode;
		}		
		
		size++;
	}
	
	public boolean contains(Staff staff) {		
		Node n = first;
		for(int i = 0; i < size; i++) {				
			if(n.item.equals(staff)) {
				return true;
			}
			n = n.next;
		}
		
		return false;
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
		int i = 0;
		Node node = first;
		while(node != null) {
			staffs[i++] = node.item;
			node = node.next;
		}
		return staffs;		
	}
	
	public void remove(Staff staff) {
		if(staff == null) {
			Node s = first;
			while(s != null) {
				if(s.item == null) {
					unlink(s);
					return;
				}
				s = s.next;
			}
		} else {
			Node s = first;
			while(s != null) {
				if(s.item.equals(staff)) {
					unlink(s);
					return;
				}
				s = s.next;
			}
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
