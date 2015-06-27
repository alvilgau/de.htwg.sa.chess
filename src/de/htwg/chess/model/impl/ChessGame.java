package de.htwg.chess.model.impl;

import java.util.Date;
import java.util.UUID;

import de.htwg.chess.model.IChessGame;
import de.htwg.chess.model.IField;

public class ChessGame implements IChessGame {
	
	private static final int FIELDS_SIZE = 8;

	private String id;

	private String name;
	
	private Date saveDate;

	private int turn;

	private int turnsWhite;

	private int turnsBlack;

	private IField fields[][];

	public ChessGame() {
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

	public IField[][] getFields() {
		return this.fields;
	}

	public void setFields(IField[][] fields) {
		this.fields = (fields != null) ? fields : new IField[FIELDS_SIZE][FIELDS_SIZE];
	}
}
