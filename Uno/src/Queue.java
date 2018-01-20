/* Name: Jamie Brandon
 * Email: jbrandon@brandeis.edu
 */
/** Queue class
 * 
 * @author jamie
 *
 * @param <T>
 */
public class Queue<T> {
	int size; 
	private Object[] arr; 
	private int start;
	private int end;
	
	/**1. queue(int size) - Constructor that creates the internal 
	 * array of size “size”, as well as any other variables needed in the queue.
	 * Running time: O(1)
	 */
	public Queue (int size){
		this.size = size;
		this.arr = new Object[size];
		
		this.start = 0;		
		this.end = 0;
		//start represents the first item that will be dequeued
		//end represents the first open slot, where an item will be enqueued
	}
	
	/** 2. void enqueue(T data) - enqueue data
	 * Running time: O(1) */
	public void enqueue(T newObject) {
		if (this.getSize() == this.size) {
			System.out.println("Error: queue full");
		}else {
			this.arr[this.end % this.size] = newObject;
			this.end = this.end % this.size +1;
		}
	}
	
	/** 3. T dequeue() - dequeue first item in the queue
	 * Running time: O(1) */
	public T dequeue() {
		T x = (T) arr[this.start];
		arr[this.start] = null;
		if(this.start == size - 1) {
			this.start = 0;
		}else {
			this.start += 1;
		}
		return x;
	}
	
	
	/** 4. int getSize() - return size of the queue
	 *     Running time: O(1) */
	public int getSize() {
		return (this.end - this.start) % Math.max(this.size,1);
	}
	
	/**5. boolean isEmpty() - returns true if queue is empty
	 * Running time: O(1)
	 */
	public Boolean isEmpty() {
		return start - end == 0;
	}
	
	/**6. boolean isFull() - return true if queue is full.
	 * Running time: O(1)
	 */
	public Boolean isFull() {
		return start == end;
	}
	
	/**to string
	 * Running time: O(n) where n is the size of the array
	 */
	public String toString() {
		String s = "";
		int i = this.start;
		
		while(arr[i] != null) {
			s = s + arr[i].toString();
		}
		return s;
	}
}
