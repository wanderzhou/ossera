package training.c2m;

public interface CustomList<E extends ListObject> {
	public boolean add(E element);
	
	public boolean contains(E object);
	
	public E get(int index);
	
	public Object[] listAll();

	public void remove(Object object);
	
	//return size of the list
	public int getSize();
}
