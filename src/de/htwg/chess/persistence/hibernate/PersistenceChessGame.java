package de.htwg.chess.persistence.hibernate;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "game")
public class PersistenceChessGame {

	@Id
	@Column(name = "id")
	private String id;

	private Date saveDate;

	private int turn;

	private int turnsWhite;

	private int turnsBlack;

	@OneToMany(mappedBy = "gameId")
	private List<PersistenceField> fields;

	private String name;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the saveDate
	 */
	public Date getSaveDate() {
		return saveDate;
	}

	/**
	 * @param saveDate
	 *            the saveDate to set
	 */
	public void setSaveDate(Date saveDate) {
		this.saveDate = saveDate;
	}

	/**
	 * @return the turn
	 */
	public int getTurn() {
		return turn;
	}

	/**
	 * @param turn
	 *            the turn to set
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
	 * @param turnsWhite
	 *            the turnsWhite to set
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
	 * @param turnsBlack
	 *            the turnsBlack to set
	 */
	public void setTurnsBlack(int turnsBlack) {
		this.turnsBlack = turnsBlack;
	}

	/**
	 * @return the fields
	 */
	public List<PersistenceField> getFields() {
		return fields;
	}

	/**
	 * @param fields
	 *            the fields to set
	 */
	public void setFields(List<PersistenceField> fields) {
		this.fields = fields;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
