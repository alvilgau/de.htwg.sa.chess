package de.htwg.chess.persistence.hibernate;

import de.htwg.chess.persistence.IPersistenceChessGame;
import de.htwg.chess.persistence.IPersistenceField;

public interface IPersistenceHibernateField extends IPersistenceField {

	IPersistenceChessGame getChessgame();
	
	void setChessgame(IPersistenceChessGame chessGame);
}
