/*Name: Jamie Brandon
 *Email: jbrandon@brandeis.edu
 *
 *Uno rules that I play by here:
 *When a player draws two, he's still allowed to play a card that turn
 *When a player plays a wild card, the next player calls the color by playing a card of their choice
 */
import java.util.*;
public class UnoGame {
	/** Running time: could be infinite, in theory. Stops when user decides to */
	public static void main(String[] args) {
		//1. In the beginning, have a prompt that asks the user to enter the names of the people that 
		// are playing. For the first five names, add the player to the PlayerCircle in alphabetical
		// order. Then after that, add the rest of the players to a Queue (you can ask the user how
		// many players they’re expecting). 
		Scanner console = new Scanner(System.in);
		int numPlayers = getNumPlayers(console);
		int numPlayersInGame = Math.min(5, numPlayers);
		
		PlayerCircle playerCircle = new PlayerCircle(numPlayersInGame);
		Queue<Player> queue = new Queue<Player>(numPlayers - numPlayersInGame);
		addingPlayers(numPlayers, numPlayersInGame, playerCircle , console, queue);
		System.out.println("Player circle " + playerCircle.toString());

		System.out.println("\nNow let us begin. Each player draws seven cards.");
		UnoDeck unoDeck = new UnoDeck();
		SinglyLinkedList<UnoCard> deck = unoDeck.getDeck();
		
		//2. For each player, draw 7 cards and add them to the player’s hand. Print out each player’s hand.
		drawSeven(playerCircle, unoDeck);
		printEachHand(playerCircle);
		
		//3. Draw one card from the deck, and add it to the discard pile. Say what card was placed down.
		SinglyLinkedList<UnoCard> discard = unoDeck.getDiscard();
		UnoCard c = unoDeck.drawCard();
		//add card to discard and remove this card from deck.
		discard.regularInsert(c);
		
		unoDeck.setLastDiscarded(c);
		UnoCard topCard = c;
		System.out.println("Last discarded was: " + c.toString());
		
		//4. Plays a game of Uno
		Player loser = playGame(console, playerCircle, topCard, deck, discard, numPlayersInGame, unoDeck);
		
		while(loser != null) {
			
			//Start a new game?
			System.out.println("Would you like to play again? [y/n]");
			String again =  console.next();
			while(!again.equals("y") && !again.equals("n")) {
				System.out.println("Only enter y or n");
				again = console.next();
			}
			
			if(again.equals("n")) {
				System.out.println("Thanks for playing!");
				loser = null;
			}else {//play again
				if(playerCircle.getSize() > 5) {
					// take loser out of the circle,
					playerCircle.removePlayer(loser);
					
					// Take someone else out of the queue, 
					Player newPlayer = queue.dequeue();
					
					// put loser in the queue. 
					queue.enqueue(loser);
					
					// put them in the PlayerCircle, and 
					playerCircle.addToCircle(newPlayer);
				}
				
				// then start again
				//clear old hands
				Player currPlayer = playerCircle.getFirstPlayer();
				do {
					currPlayer.clearHand();
					currPlayer = currPlayer.getNextPlayer();
				}while(currPlayer != playerCircle.getFirstPlayer());
				
				System.out.println("\nNow let us begin. Each player draws seven cards.");
				unoDeck = new UnoDeck();
				deck = unoDeck.getDeck();
				
				//For each player, draw 7 cards and add them to the player’s hand. Print out each player’s hand.
				drawSeven(playerCircle, unoDeck);
				printEachHand(playerCircle);
				
				//Draw one card from the deck, and add it to the discard pile. Say what card was placed
				// down.
				discard = unoDeck.getDiscard();
				c = unoDeck.drawCard();
				//add card to discard and remove this card from deck.
				discard.regularInsert(c);
				
				unoDeck.setLastDiscarded(c);
				topCard = c;
				System.out.println("Last discarded was: " + c.toString());
				
				loser = playGame(console, playerCircle, topCard, deck, discard, numPlayersInGame, unoDeck);
			}	
		}
	}
	
	/** Plays a game of uno with the current player circle
	 * Running time: could be infinite, in theory
	 * @param console
	 * @param playerCircle
	 * @param topCard
	 * @param deck
	 * @param discard
	 * @param numPlayersInGame
	 * @param unoDeck
	 * @return loser
	 */
	public static Player playGame(Scanner console, PlayerCircle playerCircle, UnoCard topCard, SinglyLinkedList<UnoCard> deck, SinglyLinkedList<UnoCard> discard, int numPlayersInGame, UnoDeck unoDeck) {
		//  4. Then, for each round (starting with the first player):
		Player currPlayer = playerCircle.getFirstPlayer();
		
		Player winner = null;
		int timesAroundCircle = 0;
		boolean reverse = false;
		do {
			//a. If the previous card was a draw 2 or draw 4, draw those cards.
			int k = 0; //k is how many cards the need to draw (if any)
			if(topCard.isSpecial()) {
				if(topCard.isDrawTwo()) {
					k = 2;
					System.out.println(currPlayer.getName() + " had to draw " + k + " cards!");
				} else if (topCard.isWildDrawFour()) {
					k = 4;
					System.out.println(currPlayer.getName() + " had to draw " + k + " cards!");
				}
			}
			for(int i = 0; i < k; i++) {
				drawCard(currPlayer, unoDeck);
			}
			
			/* 	b. Tell the user which of their cards they can place down. If they have none,
			 * 		draw a card, and skip to the next player. Use the canBePlacedOn method
			 * 		to make this much easier.*/ 
			System.out.println();
			printHand(currPlayer);
			System.out.println("Hand size: " + currPlayer.getHand().size());
			
			//print possible cards
			System.out.println(currPlayer.getName() + ", what card will you play? ");
			SinglyLinkedNode<UnoCard> curr = currPlayer.getHand().getHead();//points to the first card
			int i = 0; //first card will be at index 0
			int count = 0; //counts the number of cards that cannot be placed on topcard
			while(i < currPlayer.getHand().size() && curr !=null) {
				if(curr.getData().canBePlacedOn(topCard)) {
					System.out.printf("%25s = %1d \n", curr.toString(), i);
				}else {//count goes higher. 
					count +=1;
				}
				i++;
				curr = curr.getNext();
			}
			System.out.println();
			if(count == currPlayer.getHand().size()) {//was count == i
				System.out.println("No cards playable! You must draw a card.");
				System.out.println(currPlayer.getName() + ", you drew: " + deck.getHead().toString());
				drawCard(currPlayer, unoDeck);
			}else {
				System.out.print("Enter the index of the card you wish to play: ");
				/* 	c. Allow the user to select a card from one of the ones that can be legally
				 * 		placed.*/
				while(!console.hasNextInt()) {
					console.next();
					System.out.println("Please only enter one of the keys above.");
				}
				
				int index = console.nextInt();
				
				//if they entered an index not available
				while(!currPlayer.cardAtIndex(index, console).canBePlacedOn(topCard)) {
					System.out.println("Please only enter an integer listed above");
					index = console.nextInt();
				}
				
				
				// 	d. Take that card from the user’s hand, and discard it into the deck.
				topCard = currPlayer.removeFromHand(index, console);
				discard.randomInsert(topCard); //random insert so the deck is shuffled for next game
				System.out.println("Discard: " + topCard.toString());
				printHand(currPlayer);
				
			}
			
			
			// 	e. If the player has no more cards, they won.
			if(currPlayer.winner()) {
				winner = currPlayer;
			}else {
				// 	f. Go to the next player (if the card placed was a reverse card, reverse the
				//	direction of which way you are going. If it’s a skip card, skip the next
				//	player).
				
				if(topCard.isReverse()) {//change direction of play
					reverse = !reverse;	
				}
				if(reverse) {
					if(topCard.isSkip()) {//if it's a skip card
						currPlayer = currPlayer.getPrevPlayer(); //curr now is player getting skipped
						System.out.println(currPlayer.getName() + " was skipped! Next player is " + currPlayer.getPrevPlayer().getName());
						currPlayer = currPlayer.getPrevPlayer();
					}else {
						currPlayer = currPlayer.getPrevPlayer();
					}
				}else {
					if(topCard.isSkip()) {//if it's a skip card
						currPlayer = currPlayer.getNextPlayer(); //curr now is player getting skipped
						System.out.println(currPlayer.getName()+" was skipped! Next player is " + currPlayer.getNextPlayer().getName());
						currPlayer = currPlayer.getNextPlayer();
					}else {
						currPlayer = currPlayer.getNextPlayer();
					}//ends else
				}//ends else	
				timesAroundCircle++;
			}
			
		}while(currPlayer != winner);
		
		// 5. Once the round is over, 
		// report the winner, and 
		System.out.println(winner.getName() + " won the game!");
		
		// how many times around the circle you went. Also, 
		System.out.println("You went around the circle about " + timesAroundCircle / numPlayersInGame + " times (give or take with reverses and skips).");
		
		// report the loser (the one with the most cards), and
		int maxCards = 0;
		Player loser = null;
		Player player = playerCircle.getFirstPlayer();
		for(int i = 0; i < playerCircle.getSize(); i++) {
			if(player.getHand().size() > maxCards) {
				loser = player;
				maxCards = player.getHand().size();
			}
			player = player.getNextPlayer();
		}
		System.out.println(loser.getName() + " had the most cards: " + maxCards);
		return loser;
	}
	
	/**draw a card
	 * Running time: O(1)
	 * @param player
	 * @param unoDeck
	 */
	public static void drawCard(Player player, UnoDeck unoDeck) {
		//add card to hand
		player.addToHand(unoDeck.drawCard());
	}
	
	/**draws starting hand, seven cards
	 * Running time: O(n) where n is the number of players in the game
	 * Since this number has a maximum of 5, 
	 * for our scenario, this is O(5) = O(1)
	 * @param playerCircle
	 * @param unoDeck
	 */
	public static void drawSeven(PlayerCircle playerCircle, UnoDeck unoDeck) {
		Player player = playerCircle.getFirstPlayer();
		for(int i = 0; i < Math.min(5, playerCircle.getSize()); i++) {
			for(int j = 0; j < 7; j++) {
				drawCard(player, unoDeck);
			}
			player = player.getNextPlayer();
		}
	}
	
	/**print each player's hand
	 * Running time: O(n) where n is the number of players in the game
	 * Since this number has a maximum of 5, 
	 * for our scenario, this is O(5) = O(1)
	 * @param playerCircle
	 */
	public static void printEachHand(PlayerCircle playerCircle) {
		Player player = playerCircle.getFirstPlayer();
		for(int i = 0; i < Math.min(5, playerCircle.getSize()); i++) {
			
			printHand(player);
			player = player.getNextPlayer();
		}
	}
	
	/**this method prints a player's hand
	 * Running time: O(n) where n is the number of cards in a players hand
	 * @param player
	 */
	public static void printHand(Player player) {
		System.out.printf("%15s's hand: ", player.getName());
		player.printHand();
		System.out.println();
	}
	
	/**This method puts the inputed players into a playerCircle, and the rest of the players into a queue
	 * Running time: O(n) where n is the number of players
	 * @param numPlayers
	 * @param numPlayersInGame
	 * @param playerCircle
	 * @param console
	 * @param queue
	 */
	public static void addingPlayers(int numPlayers, int numPlayersInGame, PlayerCircle playerCircle , Scanner console, Queue queue) {
		for(int i = 0; i < numPlayersInGame; i++) {
			System.out.print("Please enter the player's first name: ");
			String name = console.next();
			Player player = createPlayer(name);
			playerCircle.addToCircle(player);
			System.out.println(name + " was added to the game");
		}
		for(int i = numPlayersInGame; i < numPlayers; i++) {
			System.out.print("Please enter the player's first name: ");
			String name = console.next();
			Player player = createPlayer(name);
			queue.enqueue(player);
			System.out.println(name + " is on deck");
		}
	}
	
	/**get number of players
	 * Running time: O(n) where n is the number of times the user inputs something unacceptable
	 * @param console
	 * @return number of players total
	 */
	public static int getNumPlayers(Scanner console) {
		System.out.println("How many players are there?");
		int k = 0;
		while(!console.hasNextInt() || k <2) {
			if(console.hasNextInt()) {
				k = console.nextInt();
				if(k>1) {
					return k;
				}
			}else {
				console.next();
			}
			System.out.println("Please enter an integer greater than one.");
		}
		return k;
	}
	
	/**returns a new player
	 * Running time: O(1)
	 * @param player
	 * @return
	 */
	public static Player createPlayer(String player) {
		return new Player(player);
	}

}
