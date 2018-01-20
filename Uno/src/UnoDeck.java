/* Name: Jamie Brandon
 * Email: jbrandon@brandeis.edu
 */
import java.util.*;

/**UnoDeck class */
public class UnoDeck {
	private static final String[] REGULAR_COLORS = {"red", "yellow", "blue", "green"};
	private SinglyLinkedList<UnoCard> deck; // initialize this in your constructor
	private SinglyLinkedList<UnoCard> discard; // initialize this in your constructor 
	private UnoCard lastDiscarded;
	
	//http://play-k.kaserver5.org/Uno.html
	// There are 108 cards in a Uno deck. 
	// There are four suits, Red, Green, Yellow and Blue, 
	// each consisting of one 0 card, two 1 cards, two 2s, 3s, 4s, 5s, 6s, 7s, 8s and 9s; 
	// two Draw Two cards; two Skip cards; and two Reverse cards. 
	// In addition there are four Wild cards and four Wild Draw Four cards.

	public UnoDeck(){
		//instantiate deck, discard
		this.deck = new SinglyLinkedList<UnoCard>();
		this.discard = new SinglyLinkedList<UnoCard>();
		
		// Initialized as having all 108 cards, as described above
		
		for (String color : REGULAR_COLORS){
			this.deck.randomInsert(new UnoCard(color, 0)); // add one of your color in zero
			
			for (int i = 0; i<2; i++){
				// add numbers 1-9
				for (int cardNumber = 1; cardNumber<=9; cardNumber++){
					this.deck.randomInsert(new UnoCard(color, cardNumber)); // Add this to deck
				}
				// add 2 of each of the special card for that color
				this.deck.randomInsert(new UnoCard(color, true, false, false)); // add to deck
				this.deck.randomInsert(new UnoCard(color, false, true, false)); // add to deck
				this.deck.randomInsert(new UnoCard(color, false, false, true)); // add to deck
			}
			
		}
		// add 4 wild cards, and 4 draw 4 wild cards
		for (int i = 0; i<4; i++){
			this.deck.randomInsert(new UnoCard(false)); // add to deck
			this.deck.randomInsert(new UnoCard(true)); // add to deck
		}
	}
	
	public SinglyLinkedList<UnoCard> getDeck(){
		return deck;
	}
	
	public SinglyLinkedList<UnoCard> getDiscard(){
		return discard;
	}
	
	/**2. UnoCard getLastDiscarded() - gets the last card which was discarded - 
	 * use this to compare to the card you’re about to put down.
	 * Running Time: O(1) 
	 */
	public UnoCard getLastDiscarded() {
		return this.lastDiscarded;
	}
	
	public void setLastDiscarded(UnoCard c) {
		this.lastDiscarded = c;
	}
	
	/**
	 * 3. UnoCard drawCard() - draw a card from the deck. 
	 * If there are no more cards in the deck, it moves all of the cards from the 
	 * discard pile to the deck (the discard pile will already be shuffled, as described below.
	 * Running time: O(1)
	 */
	public UnoCard drawCard() {
		if(this.deck.getHead() == null) {
			deck = discard;
			discard = new SinglyLinkedList<UnoCard>();
		}
		UnoCard c = deck.getHead().getData();
		deck.incHead();//removes card from deck
		return c;
	}

	/**4. void discardCard(UnoCard c) - adds c to the discard pile, 
	 * and sets it as the last discarded card. 
	 * Will throw an error if an invalid card is placed on the deck. When calling this
	 * Running Time: O(1)
	 */
	public void discardCard(UnoCard c) {
		if(lastDiscarded.canBePlacedOn(c)) {
			discard.randomInsert(c);
		}else {
			System.out.println("invalid card!");
		}
	}

	/**5. String toString()
	 * Running time: O(1)
	 */
	public String toString() {
		String s = "UnoDeck with lastDiscarded = " + lastDiscarded.toString();
		return s;
	}

}
