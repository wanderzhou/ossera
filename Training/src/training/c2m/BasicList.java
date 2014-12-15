package training.c2m;

/**
 * 
 * @author admin
 *
 * @param <E>
 */
public abstract class BasicList<E extends ListObject> implements CustomList<E> {
	protected int size;

	@Override
	public synchronized int getSize() {
		return size;
	}	
}