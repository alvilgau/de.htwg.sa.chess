package de.htwg.chess.persistence.couchdb;

import java.util.Collection;
import java.util.Date;

import org.ektorp.support.CouchDbDocument;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class PersistenceChessGame extends CouchDbDocument implements IPersistenceCouchDbChessGame {

	private static final long serialVersionUID = -2521164001857616783L;

	private String id;

	private String name;

	private Date saveDate;

	private int turn;

	private int turnsWhite;

	private int turnsBlack;

	@JsonDeserialize(as=Collection.class, contentAs=PersistenceField.class)
	private Collection<IPersistenceCouchDbField> fields;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getSaveDate() {
		return saveDate;
	}

	public void setSaveDate(Date saveDate) {
		this.saveDate = saveDate;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public int getTurnsWhite() {
		return turnsWhite;
	}

	public void setTurnsWhite(int turnsWhite) {
		this.turnsWhite = turnsWhite;
	}

	public int getTurnsBlack() {
		return turnsBlack;
	}

	public void setTurnsBlack(int turnsBlack) {
		this.turnsBlack = turnsBlack;
	}

	public Collection<IPersistenceCouchDbField> getFields() {
		return this.fields;
	}

	public void setFields(Collection<IPersistenceCouchDbField> fields) {
		this.fields = fields;
	}
}
