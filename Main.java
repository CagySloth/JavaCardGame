import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.GridLayout;

/**
 * Main function, includes all GUI components and ActionListener
 * @author Robin
 *
 */
public class Main {
	private static Game game;

	/**
	 * Main function
	 * @param args
	 */
	public static void main(String[] args) {
		
		game = new Game();
		
		/**
		 * Declaring GUI components
		 */
		JLabel label_DealerCard1 = new JLabel();
		JLabel label_DealerCard2 = new JLabel();
		JLabel label_DealerCard3 = new JLabel();
		
		JLabel label_PlayerCard1 = new JLabel();
		JLabel label_PlayerCard2 = new JLabel();
		JLabel label_PlayerCard3 = new JLabel();
		
		JButton btn_rpcard1 = new JButton("Replace Card 1");
		JButton btn_rpcard2 = new JButton("Replace Card 2");
		JButton btn_rpcard3 = new JButton("Replace Card 3");
		JButton btn_start = new JButton("Start");
		JButton btn_result = new JButton("Result");
		
		JLabel label_bet = new JLabel("Bet: $");
		JLabel label_info = new JLabel("Please place your bet!");
		JLabel label_money = new JLabel("The amount of money you have: $"
				+ Integer.toString(game.getPlayerMoney()));
		
		JTextField txt_inputbet = new JTextField(10);
		
		/**
		 * Initializing ImageIcons of 6 JLabels (cards)
		 */
	    label_DealerCard1.setIcon(new ImageIcon(Main.class.getResource("Images/card_back.gif")));
	    label_DealerCard2.setIcon(new ImageIcon(Main.class.getResource("Images/card_back.gif")));
	    label_DealerCard3.setIcon(new ImageIcon(Main.class.getResource("Images/card_back.gif")));
	    label_PlayerCard1.setIcon(new ImageIcon(Main.class.getResource("Images/card_back.gif")));
	    label_PlayerCard2.setIcon(new ImageIcon(Main.class.getResource("Images/card_back.gif")));
	    label_PlayerCard3.setIcon(new ImageIcon(Main.class.getResource("Images/card_back.gif")));
		
	    /**
	     * Declaring 6 JPanels
	     */
		JPanel MainPanel = new JPanel();
		JPanel DealerPanel = new JPanel();
		JPanel PlayerPanel = new JPanel();
		JPanel RpCardBtnPanel = new JPanel();
		JPanel ButtonPanel = new JPanel();
		JPanel InfoPanel = new JPanel();
		
		/**
		 * Putting GUI components into JPanels
		 */
		DealerPanel.add(label_DealerCard1);
		DealerPanel.add(label_DealerCard2);
		DealerPanel.add(label_DealerCard3);
		
		PlayerPanel.add(label_PlayerCard1);
		PlayerPanel.add(label_PlayerCard2);
		PlayerPanel.add(label_PlayerCard3);
		
		RpCardBtnPanel.add(btn_rpcard1);
		RpCardBtnPanel.add(btn_rpcard2);
		RpCardBtnPanel.add(btn_rpcard3);
		
		ButtonPanel.add(label_bet);
		ButtonPanel.add(txt_inputbet);
		ButtonPanel.add(btn_start);
		ButtonPanel.add(btn_result);
		
		InfoPanel.add(label_info);
		InfoPanel.add(label_money);
		
		MainPanel.setLayout(new GridLayout(5,1));
		MainPanel.add(DealerPanel);
		MainPanel.add(PlayerPanel);
		MainPanel.add(RpCardBtnPanel);
		MainPanel.add(ButtonPanel);
		MainPanel.add(InfoPanel);

		DealerPanel.setBackground(Color.green);
		PlayerPanel.setBackground(Color.green);
		RpCardBtnPanel.setBackground(Color.green);
		
		/**
		 * Declaring and implementing JMenu Components
		 */
		JMenuItem menuItem_Exit = new JMenuItem("Exit");
		JMenu menu_Control = new JMenu("Control");
		menu_Control.add(menuItem_Exit);
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menu_Control);
		
		/**
		 * Disabling buttons before the game starts
		 */
		btn_rpcard1.setEnabled(false);
		btn_rpcard2.setEnabled(false);
		btn_rpcard3.setEnabled(false);
		btn_result.setEnabled(false);
		
		/**
		 * Puting JPanels into the JFrame
		 */
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(MainPanel);
		frame.setJMenuBar(menuBar);
		frame.setTitle("A Simple Card Game");
		frame.setSize(400,700);
		
		/**
		 * Adding ActionListener to buttons
		 */
		menuItem_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		
		btn_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    label_DealerCard1.setIcon(new ImageIcon(Main.class.getResource("Images/card_back.gif")));
			    label_DealerCard2.setIcon(new ImageIcon(Main.class.getResource("Images/card_back.gif")));
			    label_DealerCard3.setIcon(new ImageIcon(Main.class.getResource("Images/card_back.gif")));
				String bet_str = txt_inputbet.getText();
				if (bet_str.isEmpty() == true)
					bet_str = "0";
				int bet = Integer.parseInt(bet_str);
				label_info.setText("Your current bet is: $"
						+ Integer.toString(bet));
				game.start(bet);
				Card[] playerCards = game.getPlayerCards();
				label_PlayerCard1.setIcon(new ImageIcon(Main.class.getResource(playerCards[0].getImage())));
				label_PlayerCard2.setIcon(new ImageIcon(Main.class.getResource(playerCards[1].getImage())));
				label_PlayerCard3.setIcon(new ImageIcon(Main.class.getResource(playerCards[2].getImage())));
				
				btn_rpcard1.setEnabled(true);
				btn_rpcard2.setEnabled(true);
				btn_rpcard3.setEnabled(true);
				btn_result.setEnabled(true);
				btn_start.setEnabled(false);
			}
		});
		
		btn_result.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Card[] dealerCards = game.getDealerCards();
				label_DealerCard1.setIcon(new ImageIcon(Main.class.getResource(dealerCards[0].getImage())));
				label_DealerCard2.setIcon(new ImageIcon(Main.class.getResource(dealerCards[1].getImage())));
				label_DealerCard3.setIcon(new ImageIcon(Main.class.getResource(dealerCards[2].getImage())));
				JOptionPane.showMessageDialog(null, game.getResult());
				if (game.checkGameEnd() == true) {
					JOptionPane.showMessageDialog(null,
							"Game Over!\n"
							+ "You have no more money!\n"
							+ "Please start a new game!");
					label_info.setText("You have no more money! Please start a new game!");
					label_money.setText("");
				}
				else {
					label_info.setText("Please place your bet!");
					label_money.setText("Amount of money you have: $"
							+ Integer.toString(game.getPlayerMoney()));
					btn_start.setEnabled(true);
				}
				btn_rpcard1.setEnabled(false);
				btn_rpcard2.setEnabled(false);
				btn_rpcard3.setEnabled(false);
				btn_result.setEnabled(false);
			}
		});
		
		btn_rpcard1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				label_PlayerCard1.setIcon(new ImageIcon(Main.class.getResource(game.replaceCard(0))));
				//disable button
				btn_rpcard1.setEnabled(false);
				//if already replaced 2 cards
				if (game.getReplaceCount() >= 2) {
					btn_rpcard2.setEnabled(false);
					btn_rpcard3.setEnabled(false);
				}
			}
		});
		
		btn_rpcard2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				label_PlayerCard2.setIcon(new ImageIcon(Main.class.getResource(game.replaceCard(1))));
				//disable button
				btn_rpcard2.setEnabled(false);
				//if already replaced 2 cards
				if (game.getReplaceCount() >= 2) {
					btn_rpcard1.setEnabled(false);
					btn_rpcard3.setEnabled(false);
				}
			}
		});
		
		btn_rpcard3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				label_PlayerCard3.setIcon(new ImageIcon(Main.class.getResource(game.replaceCard(2))));
				//disable button
				btn_rpcard3.setEnabled(false);
				//if already replaced 2 cards
				if (game.getReplaceCount() >= 2) {
					btn_rpcard1.setEnabled(false);
					btn_rpcard2.setEnabled(false);
				}
			}
		});
		
		frame.setVisible(true);
	}

}
