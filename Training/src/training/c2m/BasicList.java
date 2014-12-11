package training.c2m;

/**
 * 
 * @author admin
 *
 * @param <E>
 */
public abstract class BasicList<E extends BasicObject> {
	protected int size;

	public abstract boolean add(E element);
	
	public abstract boolean contains(E object);
	
	public abstract E get(int index);
	
	public abstract Object[] listAll();

	public abstract void remove(Object object);


	public int getSize() {
		return size;
	}	
}