package de.htwg.chess.persistence.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import de.htwg.chess.persistence.IPersistenceChessGame;
import de.htwg.chess.persistence.IPersistenceField;

@Entity
@Table(name = "field")
public class PersistenceField implements IPersistenceField{

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "isSet")
	private Boolean set;

	private Integer xPos;

	private Integer yPos;

	private Integer figure;

	private Integer team;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = PersistenceChessGame.class)
	private IPersistenceChessGame chessgame;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getSet() {
		return this.set;
	}

	public void setSet(Boolean set) {
		this.set = set;
	}

	public Integer getxPos() {
		return this.xPos;
	}

	public void setxPos(Integer xPos) {
		this.xPos = xPos;
	}

	public Integer getyPos() {
		return this.yPos;
	}

	public void setyPos(Integer yPos) {
		this.yPos = yPos;
	}

	public Integer getFigure() {
		return this.figure;
	}

	public void setFigure(Integer figure) {
		this.figure = figure;
	}

	public Integer getTeam() {
		return this.team;
	}

	public void setTeam(Integer team) {
		this.team = team;
	}

	public IPersistenceChessGame getChessgame() {
		return this.chessgame;
	}

	public void setChessgame(IPersistenceChessGame chessgame) {
		this.chessgame = chessgame;
	}
}
