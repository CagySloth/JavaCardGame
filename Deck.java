import java.util.ArrayList;
import java.util.Collections;

/**
 * The Deck class initialized and shuffle 52 cards whenever a new turn starts
 * Cards are drew from the shuffled deck
 * @author Robin
 *
 */
public class Deck {

	private ArrayList<Card> deck;
	
	/**
	 * Constructor of Deck
	 * Add all 52 cards into a new Deck then shuffle it
	 */
	public Deck() {
		deck = new ArrayList<Card>();
		for (int s = 1; s <= 4; s++) {
			for (int v = 1; v <= 13; v++)
				deck.add(new Card(s, v));
		}
		Collections.shuffle(deck);
	}
	
	/**
	 * 
	 * @return top card of the deck
	 */
	public Card drawCard() {
		return deck.remove(0);
	}
}
