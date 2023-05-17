/**
 * The Card class holds properties of each card
 * @author Robin
 *
 */
public class Card {
	
	private int suit;
	private int value;
	private boolean special;
	
	/**
	 * Constructor of Card
	 * @param suit, suit of the card in int form (1 = clubs, 2 = spades, 3 = diamonds, 4 = hearts)
	 * @param value, value of the card in int form (1 = aces, 2 = 2, ...)
	 */
	public Card(int suit, int value) {
		this.suit = suit;
		this.value = value;
		boolean s = (value > 10);
		this.special = s;
	}
	
	/**
	 * 
	 * @return suit of the card in int form
	 */
	public int getSuit() {
		return suit;
	}
	
	/**
	 * 
	 * @return value of the card in int form
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * 
	 * @return true if the card is a special card
	 */
	public boolean getSpecial() {
		return special;
	}
	
	/**
	 * 
	 * @return filename of the corresponding image
	 */
	public String getImage() {
		return "Images/card_" + Integer.toString(suit) + Integer.toString(value) + ".gif";
	}
}
