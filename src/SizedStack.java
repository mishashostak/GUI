import java.util.Stack;

/**
 * This class is a SizedStack
 * 
 * @author Misha Shostak
 * 
 * @version Jan 12, 2022
 */
public class SizedStack<T> extends Stack<T> {
	// Constant to determine the maximum size of the class defined Stack
	private final int maxSize;

	/**
     * Parameterized constructor
     * 
     * @param size maximum size of Stack
     */
	public SizedStack(int size) {
		super();
		this.maxSize = size;
	}

	
	/** 
	 * Override of abstract method to push an Object into Stack
	 * 
	 * @param object
	 * @return T - Object pushed into Stack
	 */
	@Override
	public T push(T object) {
		while (this.size() > maxSize) {
			this.remove(0);
		}
		return super.push(object);
	}
}