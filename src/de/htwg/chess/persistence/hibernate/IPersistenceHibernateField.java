package de.htwg.chess.persistence.hibernate;

import de.htwg.chess.persistence.IPersistenceChessGame;
import de.htwg.chess.persistence.IPersistenceField;

public interface IPersistenceHibernateField extends IPersistenceField {

	public IPersistenceChessGame getChessgame();
	
	public void setChessgame(IPersistenceChessGame chessGame);
}
