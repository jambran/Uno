/* Name: Jamie Brandon
 * Email: jbrandon@brandeis.edu
 */

import java.util.*;

/** Player class: holds name, next, prev and hand */
public class Player {
	private String name;
	private Player nextPlayer;
	private Player prevPlayer;
	private SinglyLinkedList<UnoCard> hand;
	
	/**1. Add a field for a singly linked list at the top (for the hand), 
	 * and instantiate it in the constructor. This will be for the player’s hand.
	 * Running time: O(1)
	 */
	public Player(String name){
		this.name = name;
		this.hand = new SinglyLinkedList<UnoCard>();
	}
	
	/**2. Implement addToHand(UnoCard c) - adds c to the player’s hand.
	 * Running time: O(n) where n is the number of cards in a player's hand
	 * @param c, card to be inserted
	 */
	public void addToHand(UnoCard c){
		//append card to end of SLL
		hand.regularInsert(c);
		
	}
	
	/**3. Implement removeFromHand(int index) - removes the item at the passed in index from
	 * the hand.
	 * Running time: O(index)
	 * @param index of card to be removed
	 * @param console
	 * @return UnoCard from hand
	 */
	public UnoCard removeFromHand(int index, Scanner console){
		while(index >= hand.size()) {
			System.out.println("Please enter that index again.");
			index =console.nextInt();
		}
		//find the ith card's data field
		SinglyLinkedNode<UnoCard> curr = new SinglyLinkedNode(null);
		curr = hand.getHead();
		for(int i = 0; i< index; i++) {
			curr = curr.getNext();
		}
		UnoCard c = curr.getData();
		
		//remove that card
		hand.remove(c);
		return c;
	}
	
	/**get card at index, but don't remove it
	 * Running time: O(index)
	 * @param index of card to be removed
	 * @param console
	 */
	public UnoCard cardAtIndex(int index, Scanner console) {
		if(index >= hand.size()) {
			System.out.println("Please only enter an integer listed above: index >= hand.size()");
			index =console.nextInt();
		}
		
		SinglyLinkedNode<UnoCard> curr = hand.getHead();
		SinglyLinkedNode<UnoCard> prev = hand.getHead();
		
		if(index == 0) {
			return curr.getData();
		}
		curr = curr.getNext();//increment curr once
		//get to the card before the one to remove
		for(int i = 1; i< index; i++) {
			curr= curr.getNext();
			prev= prev.getNext();
		}
		UnoCard c = curr.getData();
		return c;
	}
	
	/**4. Implement winner()
	 * Running time: O(1)
	 */
	public boolean winner(){
		// return true when your hand has nothing left. 
		if(hand.size() == 0) {
			return true;
		}
		return false;
	}

	/** Running time: O(1) */
	public String getName() {
		return name;
	}
	
	/** Running time: O(1) */
	public SinglyLinkedList<UnoCard> getHand() {
		return hand;
	}
	
	/** returns the hand as a string
	 * Running time: O(n) where n is the number of cards in the hand */
	public String handToString() {
		String string = "";
		//if empty hand, say that. 
		if(hand.getHead() == null) {
			string = string + "The hand is empty";
		}else {
			SinglyLinkedNode<UnoCard> curr = hand.getHead();
			while(curr != null) {
				
				UnoCard c = curr.getData();
				string = string + c.toString() + "\t";
				curr = curr.getNext();
				
				}
		}
		return string;
	}
	
	/** Running time: O(n) where n is the number of cards in the hand */
	public void printHand() {
		//if empty hand, say that. 
		if(hand.getHead() == null) {
			System.out.println("The hand is empty");
		}else {
			SinglyLinkedNode<UnoCard> curr = hand.getHead();
			while(curr != null) {
				
				UnoCard c = curr.getData();
				System.out.printf("%20s", c.toString());
				curr = curr.getNext();
				
				}
		}
	}
	
	/**Used to 'discard' all cards at the end of a round
	 * Running time: O(1) */
	public void clearHand() {
		this.hand = new SinglyLinkedList<UnoCard>();
	}
	
	/** Running time: O(1) */
	public Player getNextPlayer() {
		return nextPlayer;
	}

	/** Running time: O(1) */
	public void setNextPlayer(Player nextPlayer) {
		this.nextPlayer = nextPlayer;
	}

	/** Running time: O(1) */
	public Player getPrevPlayer() {
		return prevPlayer;
	}

	/** Running time: O(1) */
	public void setPrevPlayer(Player prevPlayer) {
		this.prevPlayer = prevPlayer;
	}

	/** Running time: O(1) */
	public String toString() {
		return "Player [name=" + name + "]";
	}
	
	
	
}
