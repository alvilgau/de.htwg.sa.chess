package de.htwg.chess.model.impl;

import de.htwg.chess.model.IField;
import de.htwg.chess.model.IGame;

/**
 * Game POJO holds game status and fields.
 * 
 *
 */
public class Game implements IGame {

	private long id;
	
	private String name;

	private int turn;

	private int turnsWhite;
	
	private int turnsBlack;

	private IField fields[][];

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the turn
	 */
	public int getTurn() {
		return turn;
	}

	/**
	 * @param turn the turn to set
	 */
	public void setTurn(int turn) {
		this.turn = turn;
	}

	/**
	 * @return the turnsWhite
	 */
	public int getTurnsWhite() {
		return turnsWhite;
	}

	/**
	 * @param turnsWhite the turnsWhite to set
	 */
	public void setTurnsWhite(int turnsWhite) {
		this.turnsWhite = turnsWhite;
	}

	/**
	 * @return the turnsBlack
	 */
	public int getTurnsBlack() {
		return turnsBlack;
	}

	/**
	 * @param turnsBlack the turnsBlack to set
	 */
	public void setTurnsBlack(int turnsBlack) {
		this.turnsBlack = turnsBlack;
	}

	/**
	 * @return the fields
	 */
	public IField[][] getFields() {
		return fields;
	}

	/**
	 * @param fields the fields to set
	 */
	public void setFields(IField[][] fields) {
		this.fields = fields;
	}
}
