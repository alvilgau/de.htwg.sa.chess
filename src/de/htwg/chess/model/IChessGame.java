package de.htwg.chess.model;

import java.util.Date;

public interface IChessGame {

	String getId();

	void setId(String id);

	String getName();

	void setName(String name);

	Date getSaveDate();

	void setSaveDate(Date saveDate);

	int getTurn();

	void setTurn(int turn);

	int getTurnsWhite();

	void setTurnsWhite(int turnsWhite);

	int getTurnsBlack();

	void setTurnsBlack(int turnsBlack);

	IField[][] getFields();

	void setFields(IField[][] fields);
}