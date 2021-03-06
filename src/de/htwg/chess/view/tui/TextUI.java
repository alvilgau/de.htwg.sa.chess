package de.htwg.chess.view.tui;

import org.apache.log4j.Logger;

import com.google.inject.Inject;

import de.htwg.chess.controller.IChessController;
import de.htwg.chess.controller.impl.ChessController;
import de.htwg.chess.model.IField;
import de.htwg.chess.view.ChessBoardXAxisDescription;
import de.htwg.util.observer.Event;
import de.htwg.util.observer.IObserver;

public class TextUI implements IObserver {

	private IChessController controller;

	private static final int MAX_LINE_LENGTH = 3;

	private String newLine = System.getProperty("line.separator");
	private Logger logger = Logger.getLogger("de.htwg.chess.view.tui");

	/**
	 * Constructs a new TextUI
	 */
	@Inject
	public TextUI(IChessController controller) {
		this.controller = controller;
		controller.addObserver(this);
	}

	@Override
	public void update(Event e) {
		printTUI();
	}

	/**
	 * Checks the inputed line of the user and decides between inputMovement and
	 * inputExchange
	 * 
	 * @param line
	 *            - String input of the user
	 * @return false if the line is "q", else it returns true
	 */
	public boolean processInputLine(String line) {
		if (line.equalsIgnoreCase("q")) {
			return false;
		} else if (line.equalsIgnoreCase("r")) {
			controller.restart();
		} else if (controller.getExchange()) {
			return inputExchange(line);
		} else if (!controller.isGameover()) {
			return inputMovement(line);
		}

		return true;
	}

	/**
	 * Checks the inputed line and call functions of controller for the movement
	 * 
	 * @param line
	 *            - String input of the user
	 * @return always true
	 */
	private boolean inputMovement(String line) {
		if (line.startsWith("s") && line.length() == MAX_LINE_LENGTH) {
			int xPos = ChessBoardXAxisDescription.valueOf(String.valueOf(line.charAt(1))).ordinal();
			int yPos = Character.getNumericValue(line.charAt(2));
			controller.select(xPos, --yPos);
		} else if (line.startsWith("m") && line.length() == MAX_LINE_LENGTH) {
			int xPos = ChessBoardXAxisDescription.valueOf(String.valueOf(line.charAt(1))).ordinal();
			int yPos = Character.getNumericValue(line.charAt(2));
			controller.move(xPos, --yPos);
		}

		return true;
	}

	/**
	 * Checks the inputed line and call functions of controller for exchange
	 * 
	 * @param line
	 *            - String input of the user
	 * @return always true
	 */
	private boolean inputExchange(String line) {
		if (line.equalsIgnoreCase("Knight")) {
			controller.exchangeKnight();
		} else if (line.equalsIgnoreCase("Bishop")) {
			controller.exchangeBishop();
		} else if (line.equalsIgnoreCase("Rook")) {
			controller.exchangeRook();
		} else if (line.equalsIgnoreCase("Queen")) {
			controller.exchangeQueen();
		}
		return true;
	}

	/**
	 * Prints the TextUI
	 */
	public void printTUI() {
		logger.info(newLine + getGameboardString(controller.getFields()));

		if (controller.getExchange()) {
			logger.info(newLine + "Pawn reaches end of the playground. "
					+ "Please choose a new Figure for the exchange");
			logger.info(newLine
					+ "Possible commands are: knight, bishop, rook, queen");
		} else if (controller.isGameover()) {
			logger.info(newLine + controller.getStatusMessage() + " - "
					+ controller.getCheckmateMessage());
			logger.info(newLine
					+ "Please enter a command: q - quit, r - new game");
		} else {
			logger.info(newLine + controller.getStatusMessage() + " - "
					+ controller.getTurnMessage() + " - "
					+ controller.getCheckmateMessage());
			logger.info(newLine
					+ "Please enter a command: q - quit, r - restart,"
					+ " sa1 - selects the figure at position a1,"
					+ " ma3 - moves the selected figure to a3,");
		}
	}

	/**
	 * Converts passed fields array into a printable textual layout.
	 * 
	 * @param IField
	 *            fields[][]
	 * @return String with the current gameboard layout as string
	 */
	private String getGameboardString(final IField fields[][]) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n |");
		for (ChessBoardXAxisDescription yAxisChar : ChessBoardXAxisDescription
				.values()) {
			sb.append("  " + yAxisChar);
		}
		sb.append("  |");
		sb.append("\n-+--------------------------+");

		for (int i = ChessController.SEVEN; i >= ChessController.ZERO; i--) {
			sb.append("\n" + (i + 1) + "|  ");
			for (int k = 0; k <= ChessController.SEVEN; k++) {
				sb.append(fields[k][i] + "  ");
			}
			sb.append("|");
		}

		sb.append("\n-+--------------------------+");
		return sb.toString();
	}
}
