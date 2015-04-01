package de.htwg.chess.controller;

import de.htwg.chess.controller.impl.Checkmate;
import de.htwg.chess.model.IFigure;
import de.htwg.util.observer.IObservable;

public interface IChessController extends IObservable {

	/**
	 * Getter for the status message
	 * 
	 * @return status message
	 */
	String getStatusMessage();

	/**
	 * Getter for the turn message
	 * 
	 * @return turn message
	 */
	String getTurnMessage();

	/**
	 * Getter for the checkmate message
	 * 
	 * @return checkmate message
	 */
	String getCheckmateMessage();

	/**
	 * Checks game is over or not
	 * 
	 * @return true if game is over
	 */
	boolean isGameover();

	/**
	 * Checks a figure is selected or not
	 * 
	 * @return true if a figure is selected
	 */
	boolean isSelect();

	/**
	 * @return size of the playground
	 */
	int getFieldSize();

	/**
	 * @return the exchange status
	 */
	boolean getExchange();

	/**
	 * @return true if team whites turn
	 */
	boolean isWhiteTurn();

	/**
	 * @return the selected figure
	 */
	IFigure getSelectedFigure();

	/**
	 * Handles the selection and movement of a figure
	 * 
	 * @param x
	 *            - horizontal position
	 * @param y
	 *            - vertical position
	 */
	void handleMovement(int x, int y);

	/**
	 * Selects a figure to move it later
	 * 
	 * @param x
	 *            - vertical position of the figure
	 * @param y
	 *            - horizontal position of the figure
	 */
	void select(int x, int y);

	/**
	 * Moves a figure from one to another field
	 * 
	 * @param x
	 *            - new vertical position
	 * @param y
	 *            - new horizontal position
	 */
	void move(int x, int y);

	/**
	 * Restarts the game
	 */
	void restart();

	/**
	 * Exchanges the Pawn with a new Knight
	 */
	void exchangeKnight();

	/**
	 * Exchanges the Pawn with a new Bishop
	 */
	void exchangeBishop();

	/**
	 * Exchanges the Pawn with a new Rook
	 */
	void exchangeRook();

	/**
	 * Exchanges the Pawn with a new Queen
	 */
	void exchangeQueen();

	/**
	 * Converts the controller to JSON
	 * 
	 * @return controller with JSON
	 */
	String toJson();

	/**
	 * Convert the chess playground to JSON String
	 * 
	 * @return game board as JSON String
	 */
	String getGameBoardAsJson();

	/**
	 * @param x
	 *            - vertical position
	 * @param y
	 *            - horizontal position
	 * @return value from the field formatted as String
	 */
	String getFieldValue(int x, int y);

	/**
	 * Gets the possible moves of the current selected figure
	 * 
	 * @return array with the possible moves
	 */
	int[][] getPossibleMoves();

	/**
	 * Gets the check mate status of the game
	 * 
	 * @return check mate status
	 */
	Checkmate getCheckmate();
}
