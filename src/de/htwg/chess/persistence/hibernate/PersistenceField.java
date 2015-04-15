package de.htwg.chess.persistence.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "field")
public class PersistenceField {

	@Id
	@Column(name = "id")
	private String id;

	private Boolean set;
	private int xPos;
	private int yPos;
	private int figure;
	private int team;

	@ManyToOne
	@JoinColumn(name = "id")
	private PersistenceChessGame gameId;

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
	 * @return the set
	 */
	public Boolean getSet() {
		return set;
	}

	/**
	 * @param set
	 *            the set to set
	 */
	public void setSet(Boolean set) {
		this.set = set;
	}

	/**
	 * @return the gameId
	 */
	public PersistenceChessGame getField() {
		return gameId;
	}

	/**
	 * @param gameId
	 *            the gameId to set
	 */
	public void setField(PersistenceChessGame gameId) {
		this.gameId = gameId;
	}

	/**
	 * @return the xPos
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 * @param xPos
	 *            the xPos to set
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	/**
	 * @return the yPos
	 */
	public int getyPos() {
		return yPos;
	}

	/**
	 * @param yPos
	 *            the yPos to set
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	/**
	 * @return the figure
	 */
	public int getFigure() {
		return figure;
	}

	/**
	 * @param figure
	 *            the figure to set
	 */
	public void setFigure(int figure) {
		this.figure = figure;
	}

	/**
	 * @return the team
	 */
	public int getTeam() {
		return team;
	}

	/**
	 * @param team
	 *            the team to set
	 */
	public void setTeam(int team) {
		this.team = team;
	}
}
