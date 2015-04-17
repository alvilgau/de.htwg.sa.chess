package de.htwg.chess.persistence;

import java.util.Collection;
import java.util.Date;

public interface IPersistenceChessGame {
	
	public String getId();

	public void setId(String id);

	public String getName();

	public void setName(String name);

	public Date getSaveDate();

	public void setSaveDate(Date saveDate);

	public int getTurn();

	public void setTurn(int turn);

	public int getTurnsWhite();

	public void setTurnsWhite(int turnsWhite);

	public int getTurnsBlack();

	public void setTurnsBlack(int turnsBlack);

	public Collection<IPersistenceField> getFields();

	public void setFields(Collection<IPersistenceField> fields);
}
