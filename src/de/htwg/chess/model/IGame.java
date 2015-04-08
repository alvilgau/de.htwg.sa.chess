package de.htwg.chess.model;

public interface IGame {

	/**
	 * @return the id
	 */
	public long getId();

	/**
	 * @param id the id to set
	 */
	public void setId(long id) ;

	/**
	 * @return the name
	 */
	public String getName();
	/**
	 * @param name the name to set
	 */
	public void setName(String name);

	/**
	 * @return the turn
	 */
	public int getTurn();

	/**
	 * @param turn the turn to set
	 */
	public void setTurn(int turn);

	/**
	 * @return the turnsWhite
	 */
	public int getTurnsWhite();

	/**
	 * @param turnsWhite the turnsWhite to set
	 */
	public void setTurnsWhite(int turnsWhite);

	/**
	 * @return the turnsBlack
	 */
	public int getTurnsBlack();

	/**
	 * @param turnsBlack the turnsBlack to set
	 */
	public void setTurnsBlack(int turnsBlack);
	/**
	 * @return the fields
	 */
	public IField[][] getFields();

	/**
	 * @param fields the fields to set
	 */
	public void setFields(IField[][] fields);
}
