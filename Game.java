/**
 * The Game class holds and updates all data and objects
 * @author Robin
 *
 */
public class Game {
	private int initial_money = 100;
	private int cardreplaced;
	private int bet;
	
	private Player player;
	private Player dealer;
	private Deck deck;
	
	/**
	 * Constructor of Game
	 * Declare 2 Player instance (dealer and player)
	 */
	public Game() {
		player = new Player(initial_money);
		dealer = new Player(0);
	}
	
	/**
	 * When the start button is clicked
	 * Get a new shuffled card deck
	 * Draw top 6 cards
	 * Reset number of cards replaced
	 * @param amount of money the player betted
	 */
	public void start(int bet) {
		this.bet = bet;
		deck = new Deck();
		cardreplaced = 0;
		player.drawCards(deck);
		dealer.drawCards(deck);
	}
	
	/**
	 * 
	 * @return cards hold by player
	 */
	public Card[] getPlayerCards() {
		return player.getCards();
	}
	
	/**
	 * 
	 * @return cards hold by dealer
	 */
	public Card[] getDealerCards() {
		return dealer.getCards();
	}

	/**
	 * 
	 * @return amount of money the player has
	 */
	public int getPlayerMoney() {
		return this.player.getMoney();
	}
	
	/**
	 * Replace a card when the replace card button is clicked
	 * Update number of cards replaced this turn
	 * @param which card to replace
	 * @return the filename of the corresponding image of the card
	 */
	public String replaceCard(int idx) {
		player.replaceCard(idx, deck);
		Card card = player.getCards()[idx];
		cardreplaced++;
		return card.getImage();
	}
	
	/**
	 * 
	 * @return number of cards replaced
	 */
	public int getReplaceCount() {
		return cardreplaced;
	}
	
	/**
	 *
	 * @return true if player has no money left
	 */
	public boolean checkGameEnd() {
		return (player.getMoney() <= 0);
	}
	
	/**
	 * Calculate the result of this turn
	 * @return the message to be printed on the JOptionPane
	 */
	public String getResult() {
		int p = player.num_of_special_card();
		int d = dealer.num_of_special_card();
		if (p > d) {
			player.updateMoney(bet);
			return "Congratulations! You win this round!";
		}
		else if (d > p) {
			player.updateMoney(bet*-1);
			return "Sorry! The Dealer wins this round!";
		}
		else {
			int p_remainder = player.getRemainder();
			int d_remainder = dealer.getRemainder();
			if (p_remainder > d_remainder) {
				player.updateMoney(bet);
				return "Congratulations! You win this round!";
			}
			else {
				player.updateMoney(bet*-1);
				return "Sorry! The Dealer wins this round!";
			}
		}
	}
}
