package de.htwg.chess.persistence.couchdb;

import java.util.Collection;
import java.util.Date;

import org.ektorp.support.CouchDbDocument;

import de.htwg.chess.persistence.IPersistenceChessGame;
import de.htwg.chess.persistence.IPersistenceField;

public class PersistenceChessGame extends CouchDbDocument implements IPersistenceChessGame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2521164001857616783L;

	private String id;

	private String name;

	private Date saveDate;

	private int turn;

	private int turnsWhite;

	private int turnsBlack;

	private Collection<IPersistenceField> fields;

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

	public Collection<IPersistenceField> getFields() {
		return fields;
	}

	public void setFields(Collection<IPersistenceField> fields) {
		this.fields = fields;
	}
}
