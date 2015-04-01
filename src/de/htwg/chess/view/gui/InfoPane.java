package de.htwg.chess.view.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.htwg.chess.controller.IChessController;

public class InfoPane extends JOptionPane {

	private static final int KNIGHT = 0;
	private static final int BISHOP = 1;
	private static final int ROOK = 2;
	private static final long serialVersionUID = 1L;
	
	private IChessController controller;
	
	/**
	 * Creates an InfoPane
	 * @param controller - Chess Controller
	 */
	public InfoPane(IChessController controller) {
		this.controller = controller;
	}
	
	/**
	 * Shows the game over Message
	 */
	public void showGameOver(JFrame frame) {
		showMessageDialog(frame, controller.getCheckmateMessage(), "Game Over", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Shows the message for the exchange of a pawn.
	 * Calls methods from controller for the exchange
	 */
	public void handleExchange(JFrame frame) {
		String[] options = { "Knight", "Bishop", "Rook", "Queen"};
		int selection = -1;
		
		while(selection < KNIGHT) {
			selection = JOptionPane.showOptionDialog(frame, "Pawn reaches end of the playground!\n" +
					"Please select a figure for the exchange!", "Exchange",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, "Knight");
		}
		
		if(selection == KNIGHT) {
			controller.exchangeKnight();
		}
		else if(selection == BISHOP) {
			controller.exchangeBishop();
		}
		else if(selection == ROOK) {
			controller.exchangeRook();
		}
		else {
			controller.exchangeQueen();
		}
	}
}
