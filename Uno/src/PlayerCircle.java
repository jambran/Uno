/* Name: Jamie Brandon
 * Email: jbrandon@brandeis.edu
 */

/** Player circle class: a circularly doubly linked list */
public class PlayerCircle {
	private int numPlayers;
	private int currNumPlayers;
	private Player last;
	private Player first;
	
	/**1. Constructor (optional)
	 * Running time: O(n)
	 */
	public PlayerCircle(int numPlayers) {
		this.numPlayers = numPlayers;
		this.currNumPlayers = 0;
	}
	
	/**2. addToCircle(Player p) - add the player p to the circle, such that the 
	 * players are still sitting in a circle. Add them in alphabetical order.
	 * Running time: O(n) where n is the number of players currently in the circle
	 */
	public void addToCircle(Player newPlayer) {
		//inserting the first player
		if(currNumPlayers == 0) {
			this.last = newPlayer;
			this.first = newPlayer;
			newPlayer.setNextPlayer(newPlayer);
			newPlayer.setPrevPlayer(newPlayer);
		}else if(currNumPlayers == 1){
			//to enter the second player
			if(compareStrings(first.getName(), newPlayer.getName())) {
				//first precedes newPlayer
				first.setNextPlayer(newPlayer);
				first.setPrevPlayer(newPlayer);
				newPlayer.setNextPlayer(first);
				newPlayer.setPrevPlayer(first);
				last = newPlayer;
			}else {//newPlayer precedes first
				newPlayer.setNextPlayer(last);
				newPlayer.setPrevPlayer(last);
				last.setNextPlayer(newPlayer);
				last.setPrevPlayer(newPlayer);
				first = newPlayer;
			}	
		}else {//adding the third through 5th players
			//need to insert into first player position
			if(compareStrings(newPlayer.getName(), first.getName())) {
				last.setNextPlayer(newPlayer);
				newPlayer.setPrevPlayer(last);
				first.setPrevPlayer(newPlayer);
				newPlayer.setNextPlayer(first);
				first = newPlayer;
			}else {//insert after the first position
				Player currPlayer = this.getFirstPlayer();
				//loop through players, see where name fits alphabetically
				while(compareStrings(currPlayer.getName(), newPlayer.getName()) && currPlayer != last) {
					//newPlayer should follow currPlayer
					currPlayer = currPlayer.getNextPlayer();
				}
				if(currPlayer == last) {
					//compare newPlayer to last
					if(compareStrings(newPlayer.getName(), last.getName())) {
						//newPlayer precedes last
						last.getPrevPlayer().setNextPlayer(newPlayer);
						newPlayer.setPrevPlayer(last.getPrevPlayer());
						last.setPrevPlayer(newPlayer);
						newPlayer.setNextPlayer(last);
					}else {
						//last precedes newPlayer
						newPlayer.setNextPlayer(first);
						first.setPrevPlayer(newPlayer);
						last.setNextPlayer(newPlayer);
						newPlayer.setPrevPlayer(last);
						last = newPlayer;
					}
				}else {//inserting somewhere in the middle of the player circle
					//call next the player to follow NewPlayer
					Player next = currPlayer;
					//call prev the player to come before NewPlayer
					Player prev = currPlayer.getPrevPlayer();
					
					next.setPrevPlayer(newPlayer);
					prev.setNextPlayer(newPlayer);
					
					newPlayer.setNextPlayer(next);
					newPlayer.setPrevPlayer(prev);
				}
			}
		}
		currNumPlayers++;
	}
	
	/**3. Player getFirstPlayer() - returns the first person in the circle.
	 * Running time: O(1)
	 */
	public Player getFirstPlayer() {
		return this.first;
	}
	
	/**4. int getSize() - returns number of players in the circle.
	 * Running time: O(1)
	 */
	public int getSize() {
		return currNumPlayers;
	}
	
	/**5. toString() - print all the players in the circle
	 * Running time: O(n) where n is the number of players in the circle
	 */
	public String toString() {
		String s = "";
		Player curr = first;
		s = s + first.getName() + " ";
		while(curr.getNextPlayer() != this.first) {
			curr=curr.getNextPlayer();
			s = s + curr.getName() + " ";
		}
		return s;
	}
	
	/** removes player from the circle
	 *  Running time: O(n)
	 */
	public void removePlayer(Player p) {
		if(p == first) {
			first = p.getNextPlayer();
		}else if(p == last) {
			last = p.getPrevPlayer();
		}
		Player prev = p.getPrevPlayer();
		Player next = p.getNextPlayer();
		prev.setNextPlayer(next);
		next.setPrevPlayer(prev);
	}
	
	/**method for comparing strings for alphabetical order
	 * Running time: O(n) where n is the length of the shortest string
	 */
	public Boolean compareStrings(String s1, String s2){
		//returns 1 if s1 is alphabetically before s2, 0 otherwise
		s1 = s1.toLowerCase() + " ";
		s2 = s2.toLowerCase() + " ";

		int i = 0;
		while(s1.charAt(i) == s2.charAt(i)){
			i++;
		}
		int a = s1.charAt(i);
		int b = s2.charAt(i);
		
		if(a >=  b){
			return false;
		}
		return true;

	}
	
}


