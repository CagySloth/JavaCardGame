/**
 * The Player class holds data (money and cards) of each player object
 * @author Robin
 *
 */
public class Player {

	private int money;
	private Card[] cards;
	
	/**
	 * Constructor of Player
	 * @param initial amount of money
	 */
	public Player(int money) {
		this.money = money;
		cards = new Card[3];
	}
	
	/**
	 * Draw the top 3 cards from the deck
	 * @param a shuffled deck of cards
	 */
	public void drawCards(Deck deck) {
		cards[0] = deck.drawCard();
		cards[1] = deck.drawCard();
		cards[2] = deck.drawCard();
	}
	
	/**
	 * Replace a card with the top card from the deck
	 * @param which card to be replaced
	 * @param the shuffled deck of cards
	 */
	public void replaceCard(int idx, Deck deck) {
		cards[idx] = deck.drawCard();
	}
	
	/**
	 * 
	 * @return amount of money the Player has
	 */
	public int getMoney() {
		return money;
	}
	
	/**
	 * 
	 * @return cards the Player has
	 */
	public Card[] getCards() {
		return cards;
	}
	
	/**
	 * 
	 * @return number of special cards
	 */
	public int num_of_special_card() {
		int count = 0;
		for (Card card: cards) {
			if (card.getSpecial() == true)
				count++;
		}
		return count;
	}
	
	/**
	 * 
	 * @return the remainder of the 3 cards
	 */
	public int getRemainder() {
		int sum = 0;
		for (Card card: cards) {
			if (card.getSpecial() == false)
				sum += card.getValue();
		}
		return sum%10;
	}
	
	/**
	 * Update the amount of money of the Player
	 * @param change of money
	 */
	public void updateMoney(int n) {
		money += n;
	}
	
}
