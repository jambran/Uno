/*
 * Name: Jamie Brandon
 * Email: jbrandon@brandeis.edu
 */

/** Singly linked node class */
public class SinglyLinkedNode<T> {
	private T data;
	private SinglyLinkedNode<T> next;
	
	/**1. SinglyLinkedNode(T data) - constructor that initializes the node.
	 * Running time: O(1)
	 */
	public SinglyLinkedNode (T data){
		this.data = data;
	}
	
	/**2. T getData() - returns the data in the node
	 * running time: O(1)
	 */
	public T getData() {
		return this.data;
	}
	
	/**3. void setNext(SinglyLinkedNode<T> nextNode) - sets the node as the “next” node in the
	 * list, returned by getNext()
	 * Running time: O(1)
	 */
		public void setNext(SinglyLinkedNode<T> nextNode) {
		this.next = nextNode;
	}
	
	/**4. SinglyLinkedNode<T> getNext() - returns the next node in the list
	 * Running time: O(1)
	 */
	public SinglyLinkedNode<T> getNext() {
		return this.next;
	}
	
	/**5. toString() - return the string of the data inside it.
	 * running time: O(1)
	 */
	public String toString() {
		String dataString =  data.toString();
		return dataString;
	}
	
	/**set the data inside a node
	 * running time: O(n)
	 */
	public void setData(T data) {
		this.data = data;
	}
	
	
}	
