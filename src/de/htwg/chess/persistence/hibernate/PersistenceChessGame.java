package de.htwg.chess.persistence.hibernate;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import de.htwg.chess.persistence.IPersistenceChessGame;
import de.htwg.chess.persistence.IPersistenceField;

@Entity
@Table(name = "game")
public class PersistenceChessGame implements IPersistenceChessGame {

	@Id
	@Column(name = "id")
	private String id;

	private String name;

	private Date saveDate;

	private int turn;

	private int turnsWhite;

	private int turnsBlack;

	@OneToMany(mappedBy = "chessgame", fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = PersistenceField.class)
	private Collection<IPersistenceField> fields;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getSaveDate() {
		return this.saveDate;
	}

	public void setSaveDate(Date saveDate) {
		this.saveDate = saveDate;
	}

	public int getTurn() {
		return this.turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public int getTurnsWhite() {
		return this.turnsWhite;
	}

	public void setTurnsWhite(int turnsWhite) {
		this.turnsWhite = turnsWhite;
	}

	public int getTurnsBlack() {
		return this.turnsBlack;
	}

	public void setTurnsBlack(int turnsBlack) {
		this.turnsBlack = turnsBlack;
	}

	public Collection<IPersistenceField> getFields() {
		return this.fields;
	}

	public void setFields(Collection<IPersistenceField> fields) {
		this.fields = fields;
	}

}
