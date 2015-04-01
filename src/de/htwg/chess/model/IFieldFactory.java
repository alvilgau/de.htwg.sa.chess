package de.htwg.chess.model;

public interface IFieldFactory {

	/**
	 * Constructs a new empty Field object
	 * 
	 * @param x
	 *            - position
	 * @param y
	 *            - position
	 */
	IField createEmptyField(int x, int y);

	/**
	 * Constructs a new field with a figure on it
	 * 
	 * @param figure
	 *            - the figure that will be placed on the field
	 */
	IField createField(boolean set, IFigure figure);
}
