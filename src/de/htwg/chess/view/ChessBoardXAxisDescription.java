package de.htwg.chess.view;

/**
 * Describes the values of x axis of the game board.
 * 
 * @author Artur
 * @since 2015-04-05
 *
 */
public enum ChessBoardXAxisDescription {
	a, b, c, d, e, f, g, h;

	/**
	 * Get the alphabetic description of game board x-axis value.
	 * 
	 * @param int i - the needed position
	 * @return String - alphabetic representation in lower case.
	 */
	public static String getCharCoordinateAsString(int i) {
		return values()[i].toString();
	}

	/**
	 * Same as in function getCharCoordinateAsString(), but upper case.
	 * 
	 * @param int i - the needed position
	 * @return String - alphabetic representation in upper case.
	 */
	public static String getCharCoordinateUpperCaseAsString(int i) {
		return getCharCoordinateAsString(i).toUpperCase();
	}
}
