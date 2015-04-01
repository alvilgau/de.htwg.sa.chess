package de.htwg.chess.model;

public interface IField {

	/**
	 * Getter for the figure
	 * 
	 * @return the figure of a field
	 */
	IFigure getFigur();

	/**
	 * References the field with a new figure
	 * 
	 * @param figure
	 *            - the new figure
	 */
	void setFigur(IFigure figure);

	/**
	 * Getter for set
	 * 
	 * @return value of set
	 */
	boolean isSet();

	/**
	 * Changes the set status of a field
	 * 
	 * @param set
	 *            - the new set value
	 */
	void setSet(boolean set);

	/**
	 * @return x position of the filed
	 */
	int getxPos();

	/**
	 * @return y position of the filed
	 */
	int getyPos();

	/**
	 * Checks the figure at the field
	 * 
	 * @return true if the figure is a king
	 */
	boolean isKing();

	/**
	 * Sets the attributes of a field to a default value
	 */
	void clear();
}
