/* Name: Jamie Brandon
 * Email: jbrandon@brandeis.edu
*/

public class UnoCard{
	/** Class for UnoCard */
	
	// blue, green, red, yellow, wild
	private String color;
	private int number;
	private boolean isSpecialCard = false;
	private boolean isDrawTwo = false;
	private boolean isReverse = false;
	private boolean isSkip = false;
	private boolean isWildDrawFour = false;
	
	
	// constructor for normal cards
	public UnoCard(String color, int number){
		/** Running time: O(1) */
		this.color = color;
		this.number = number;
	}
	
	// constructor for colored cards which have special values (so no numbers)
	// another problem with this implementation is that something shouldn't be
	// able to be both drawTwo, reverse, and skip. I'll just throw an error.
	public UnoCard(String color, boolean drawTwo, boolean reverse, boolean skip){
		/** Running time: O(1) */
		if (drawTwo && (reverse || skip) || (reverse && skip)){
			// http://rymden.nu/exceptions.html
			throw new IllegalStateException("A card can't have more than one special property");
		}
		this.number = -1;
		this.color = color;
		this.isSpecialCard = true;
		this.isDrawTwo = drawTwo;
		this.isReverse = reverse;
		this.isSkip = skip;
	}
	
	// constructor for wild cards
	public UnoCard(boolean drawFour){
		/** Running time: O(1) */
		this.number = -1;
		this.color = "wild";
		this.isSpecialCard = true;
		this.isWildDrawFour = drawFour;
	}

	/**
	 * @return the color
	 * Running time: O(1)
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @return the number
	 * Running time: O(1)
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @return the isSpecialCard
	 * Running time: O(1)
	 */
	public boolean isSpecial() {
		return isSpecialCard;
	}

	/**
	 * @return the isDrawTwo
	 * Running time: O(1)
	 */
	public boolean isDrawTwo() {
		return isDrawTwo;
	}

	/**
	 * @return the isReverse
	 * Running time: O(1)
	 */
	public boolean isReverse() {
		return isReverse;
	}

	/**
	 * @return the isSkip
	 * Running time: O(1)
	 */
	public boolean isSkip() {
		return isSkip;
	}

	/** Running time: O(1) */
	public boolean isWild(){
		return this.getColor().equals("wild");
	}
	
	/** Running time: O(1) */
	public boolean isWildDrawFour() {
		return isWildDrawFour;
	}
	
	/** Running time: O(1) */
	public boolean canBePlacedOn(UnoCard other){
		return (this.getNumber()!=-1 && this.getNumber()==other.getNumber()) ||
				(this.getColor().equals(other.getColor())) ||
				(this.isDrawTwo() && this.isDrawTwo()==other.isDrawTwo()) ||
				(this.isReverse() && this.isReverse()==other.isReverse()) ||
				(this.isSkip()  && this.isSkip()==other.isSkip()) ||
				this.isWild() || other.isWild();
	}
	
	// toString - prints the type of the card
	/** Running time: O(1) */
	public String toString(){
		if (this.isSpecial()){
			if (this.isWild()){
				if (this.isWildDrawFour()){
					return "wild draw four card";
				}
				else{
					return "wild card";
				}
			}
			else if (this.isDrawTwo()){
				return this.getColor()+" draw two";
			}
			else if (this.isReverse()){
				return this.getColor()+" reverse";
			}
			else if (this.isSkip){
				return this.getColor()+" skip";
			}
			else{
				return "this shouldn't happen";
			}
		}
		else{
			return this.getColor()+" "+this.getNumber();
		}
	}
	
	// tests that the memory addresses are the same
	/** Running time: O(1) */
	public boolean equals(UnoCard other){
		return this==other;
	}

	
}
