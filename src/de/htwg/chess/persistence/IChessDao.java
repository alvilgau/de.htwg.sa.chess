package de.htwg.chess.persistence;

import java.util.List;

public interface IChessDao {

	/**
	 * Saves the given chess game
	 * 
	 * @param game
	 *            to save
	 */
	void saveGame(ChessPojo game);

	/**
	 * Loads a game from the database
	 * 
	 * @param id
	 *            of the chess game that should be loaded
	 * @return corresponding game from the database
	 */
	ChessPojo getGame(String id);

	/**
	 * Checks a game is in the database or not
	 * 
	 * @param id
	 *            of the game
	 * @return true if the game is in database
	 */
	boolean containsGame(String id);

	/**
	 * Deletes a game from database
	 * 
	 * @param id
	 *            of the chess game that should be deleted
	 * @return true if the game could be deleted
	 */
	boolean deleteGame(String id);

	/**
	 * Loads all games from the database
	 * 
	 * @return {@link List} with all games
	 */
	List<ChessPojo> getAllGames();
}
