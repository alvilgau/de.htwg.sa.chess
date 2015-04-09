package de.htwg.chess.persistence;

import java.util.UUID;

import de.htwg.chess.model.IField;

public class ChessPojo {

	private String id;

	private String name;

	private int turn;

	private int turnsWhite;

	private int turnsBlack;

	private IField fields[][];

	public ChessPojo() {
		this.id = UUID.randomUUID().toString();
	}

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

	public IField[][] getFields() {
		return this.fields;
	}

	public void setFields(IField[][] fields) {
		this.fields = fields;
	}

}
