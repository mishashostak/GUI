import java.util.Stack;

public class SizedStack<T> extends Stack<T> {

	private final int maxSize;

	public SizedStack(int size) {
		super();
		this.maxSize = size;
	}

	
	/** 
	 * @param object
	 * @return Object
	 */
	@Override
	public T push(T object) {
		while (this.size() > maxSize) {
			this.remove(0);
		}
		return super.push(object);
	}
}