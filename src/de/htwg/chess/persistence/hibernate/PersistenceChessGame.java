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
@Table(name = "chess_game")
public class PersistenceChessGame implements IPersistenceHibernateChessGame {

	@Id
	@Column(name = "id")
	private String id;

	private String name;

	private Date saveDate;

	private int turn;

	private int turnsWhite;

	private int turnsBlack;

	@OneToMany(mappedBy = "chessgame", fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = PersistenceField.class)
	private Collection<IPersistenceHibernateField> fields;

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Date getSaveDate() {
		return this.saveDate;
	}

	@Override
	public void setSaveDate(Date saveDate) {
		this.saveDate = saveDate;
	}

	@Override
	public int getTurn() {
		return this.turn;
	}

	@Override
	public void setTurn(int turn) {
		this.turn = turn;
	}

	@Override
	public int getTurnsWhite() {
		return this.turnsWhite;
	}

	@Override
	public void setTurnsWhite(int turnsWhite) {
		this.turnsWhite = turnsWhite;
	}

	@Override
	public int getTurnsBlack() {
		return this.turnsBlack;
	}

	@Override
	public void setTurnsBlack(int turnsBlack) {
		this.turnsBlack = turnsBlack;
	}

	@Override
	public Collection<IPersistenceHibernateField> getFields() {
		return this.fields;
	}

	@Override
	public void setFields(Collection<IPersistenceHibernateField> fields) {
		this.fields = fields;
	}
}
