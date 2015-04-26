package de.htwg.chess.persistence.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import de.htwg.chess.persistence.IPersistenceChessGame;

@Entity
@Table(name = "chess_field")
public class PersistenceField implements IPersistenceHibernateField {

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

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public Boolean getSet() {
		return this.set;
	}

	@Override
	public void setSet(Boolean set) {
		this.set = set;
	}

	@Override
	public Integer getxPos() {
		return this.xPos;
	}

	@Override
	public void setxPos(Integer xPos) {
		this.xPos = xPos;
	}

	@Override
	public Integer getyPos() {
		return this.yPos;
	}

	@Override
	public void setyPos(Integer yPos) {
		this.yPos = yPos;
	}

	@Override
	public Integer getFigure() {
		return this.figure;
	}

	@Override
	public void setFigure(Integer figure) {
		this.figure = figure;
	}

	@Override
	public Integer getTeam() {
		return this.team;
	}

	@Override
	public void setTeam(Integer team) {
		this.team = team;
	}

	@Override
	public IPersistenceChessGame getChessgame() {
		return this.chessgame;
	}

	@Override
	public void setChessgame(IPersistenceChessGame chessgame) {
		this.chessgame = chessgame;
	}
}
