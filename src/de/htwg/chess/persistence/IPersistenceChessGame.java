package de.htwg.chess.persistence;

import java.util.Date;

public interface IPersistenceChessGame {

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
}
