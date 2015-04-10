package de.htwg.chess.view.gui;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.htwg.chess.controller.IChessController;
import de.htwg.chess.persistence.ChessGame;

public class InfoPane extends JOptionPane {

	private static final int KNIGHT = 0;
	private static final int BISHOP = 1;
	private static final int ROOK = 2;
	private static final long serialVersionUID = 1L;

	private IChessController controller;

	/**
	 * Creates an InfoPane
	 * 
	 * @param controller
	 *            - Chess Controller
	 */
	public InfoPane(IChessController controller) {
		this.controller = controller;
	}

	/**
	 * Shows the game over Message
	 */
	public void showGameOver(JFrame frame) {
		showMessageDialog(frame, this.controller.getCheckmateMessage(),
				"Game Over", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Shows the message for the exchange of a pawn. Calls methods from
	 * controller for the exchange
	 */
	public void handleExchange(JFrame frame) {
		String[] options = { "Knight", "Bishop", "Rook", "Queen" };
		int selection = -1;

		while (selection < KNIGHT) {
			selection = JOptionPane.showOptionDialog(frame,
					"Pawn reaches end of the playground!\n"
							+ "Please select a figure for the exchange!",
					"Exchange", JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, options, "Knight");
		}

		if (selection == KNIGHT) {
			this.controller.exchangeKnight();
		} else if (selection == BISHOP) {
			this.controller.exchangeBishop();
		} else if (selection == ROOK) {
			this.controller.exchangeRook();
		} else {
			this.controller.exchangeQueen();
		}
	}

	/**
	 * Saves the game to database.
	 * 
	 * @param frame
	 */
	public void handleSaveGame(JFrame frame) {
		String gameName = JOptionPane.showInputDialog(frame,
				"Please enter game name", "Saving game",
				JOptionPane.QUESTION_MESSAGE);
		if (gameName != null) {
			this.controller.saveToDB(gameName);
		}
	}

	/**
	 * Loads game from database.
	 */
	public void handleLoadGame(JFrame frame, List<ChessGame> chessGames) {

		if (chessGames.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "No saved games available.",
					"No saved games available", JOptionPane.WARNING_MESSAGE);
			return;
		}
		String nameList[] = new String[chessGames.size()];
		for (int i = nameList.length - 1; i >= 0; i--) {
			ChessGame game = chessGames.get(i);
			String dateTimeString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(game.getSaveDate());
			nameList[i] = game.getName() + " - Saved " + dateTimeString;
		}

		JOptionPane.showInputDialog(frame, "Pick a game to load", "Load game",
				JOptionPane.QUESTION_MESSAGE, null, nameList, null);
	}
}
