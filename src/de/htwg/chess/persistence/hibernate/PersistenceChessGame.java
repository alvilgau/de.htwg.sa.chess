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

@Entity
@Table(name = "game")
public class PersistenceChessGame {

	@Id
	@Column(name = "id")
	private String id;

	private String name;

	private Date saveDate;

	private int turn;

	private int turnsWhite;

	private int turnsBlack;

	@OneToMany(mappedBy = "chessgame", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<PersistenceField> fields;

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

	public Collection<PersistenceField> getFields() {
		return this.fields;
	}

	public void setFields(Collection<PersistenceField> fields) {
		this.fields = fields;
	}

}
