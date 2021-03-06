package de.htwg.chess.persistence.couchdb;

import org.ektorp.support.CouchDbDocument;

public class PersistenceField extends CouchDbDocument implements IPersistenceCouchDbField {
	
	private static final long serialVersionUID = 8020935469601114063L;

	private String id;

	private Boolean set;

	private Integer xPos;

	private Integer yPos;

	private Integer figure;

	private Integer team;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getSet() {
		return set;
	}

	public void setSet(Boolean set) {
		this.set = set;
	}

	public Integer getxPos() {
		return xPos;
	}

	public void setxPos(Integer xPos) {
		this.xPos = xPos;
	}

	public Integer getyPos() {
		return yPos;
	}

	public void setyPos(Integer yPos) {
		this.yPos = yPos;
	}

	public Integer getFigure() {
		return figure;
	}

	public void setFigure(Integer figure) {
		this.figure = figure;
	}

	public Integer getTeam() {
		return team;
	}

	public void setTeam(Integer team) {
		this.team = team;
	}
}