package de.htwg.chess.view.gui;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.htwg.chess.controller.IChessController;
import de.htwg.chess.model.IChessGame;

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
	public String handleLoadGame(JFrame frame, List<IChessGame> chessGames, String desc) {

		// check if any games are available
		if (chessGames.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "No saved games available.",
					"No saved games available", JOptionPane.WARNING_MESSAGE);
			return null;
		}
		String nameList[] = new String[chessGames.size()];
		for (int i = 0; i < nameList.length; i++) {
			IChessGame game = chessGames.get(i);
			String dateTimeString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(game.getSaveDate());
			nameList[i] = game.getName() + " - Saved " + dateTimeString;
		}

		// display available games in database games.
		Object selection = JOptionPane.showInputDialog(frame,
				"Pick a game to " + desc.toLowerCase(), desc + " game",
				JOptionPane.QUESTION_MESSAGE, null, nameList, null);

		// there was a selection of the user. so check for the selection
		if (selection != null) {
			// now search for the chess games in List<ChessGame> chessGames
			for (int i = 0; i < nameList.length; i++) {
				if (nameList[i].equals(selection)) {
					// return the UUID of the game
					return chessGames.get(i).getId();
				}
			}
		}

		// somehow failed, so we return null.
		return null;
	}
}
