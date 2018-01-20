/*
 * SinglyLinkedList<T>
This will be how we will represent each player’s hand, as well as the uno deck and discard pile.

4 uses break :(

 */
import java.util.Random;

public class SinglyLinkedList<T>{
	private SinglyLinkedNode<T> head;
	private int size;
	private SinglyLinkedNode<T> tail;
	
	/**1. SinglyLinkedList() - constructor (optional)
	 * Running time: O(1)
	 */
	public SinglyLinkedList() {
		this.head = null;
		this.size = 0; //make sure to update this in your methods
		this.tail = null; //update this in methods.
	}
	
	/**2. SinglyLinkedNode<T> getHead() - returns the first node in the list (null if empty)
	 * Running time: O(1)
	 * @return head of list
	 */
	public SinglyLinkedNode<T> getHead() {
		return this.head;		
	}
	
	/**3. void regularInsert(T data) - insert “data” at the end.
	 * Running time: O(n) where n is the number of cards
	 * @param data is the card to insert
	 */
	public void regularInsert(T data) {
		SinglyLinkedNode<T> newNode = new SinglyLinkedNode<T>(data);
		if(this.head == null) {//if hand is empty
			this.head = newNode;
			newNode.setNext(null);
			tail = newNode;
		}else {
			tail.setNext(newNode);
			newNode.setNext(null);
			tail = newNode;
		}
		this.size++;
	}
	

	/**4. void randomInsert(T data) - insert “data” at a random point in the LinkedList. It should
	 * be equally likely that a card will end up at any of the possible locations, including the
	 * front and the end. This is going to be used when inserting a card into the (deck?)
	 * Running time: O(n) where n is the number of cards in where ever you're inserting to
	 * @param data is the card to be inserted
	 */
	public void randomInsert(T data) {
		Random rand = new Random();
		int pos = rand.nextInt(this.size+1);
		//make a new node, then figure out where to put it
		SinglyLinkedNode<T> newNode = new SinglyLinkedNode<T>(data);
		

		if(size == 0) {
			this.head = newNode;
			this.tail = newNode;
		}
		if(pos == 0) {//insert at beginning
			newNode.setNext(this.head);
			this.head = newNode;
		}else if(pos == this.size ) {//inserting at end
			tail.setNext(newNode);
			newNode.setNext(null);
			tail = newNode;
		}else { //insert in middle
			SinglyLinkedNode<T> curr = this.head;
			
			for(int i = 0; i < pos-1; i++) {
				curr = curr.getNext();
			}
			newNode.setNext(curr.getNext());
			curr.setNext(newNode);
		}				
		this.size++;
	}
	
	/**5. void remove(T data) - delete the “data” node from the list.
	 * Running time: O(n)
	 * @param data is the card to remove
	 */
	public void remove(T data) {
		SinglyLinkedNode<T> curr = this.head;
		
		if(curr == null) {//if the list is empty
			System.out.println("Error: empty list");
		}else if (curr.getData() == data) {//if the head is the card to remove
			this.head = curr.getNext();
		}else if(tail.getData() == data){//if the tail is the card to remove
			while(curr.getNext().getData() != data) {
				curr = curr.getNext();
			}
			curr.setNext(null);
			tail = curr;
		}else {
			while(curr.getNext().getData() != data) {
				curr = curr.getNext();
			}
			curr.setNext(curr.getNext().getNext());
		}
		this.size--;
	}
	
	/**6. int size() - gets size of linked list.
	 * Running time: O(1)
	 * @return size
	 */
	public int size() {
		return this.size;
	}
	
	/**7. toString()
	 * Running time: O(1)
	 */
	public String toString() {
		String s = "";
		SinglyLinkedNode<T> curr = this.head;
		while(curr.getNext() != null) {
			s = s + curr.getData() + " ";
		}
		return s;
	}
	
	/**get Tail
	 * Running time: O(1)
	 * @return tail
	 */
	public SinglyLinkedNode<T> getTail() {
		return tail;
	}
	
	/**increment head by one (to remove the first card from the list)
	 * Running time: O(1)
	 */
	public void incHead() {
		head = head.getNext();
		size--;
	}
}
