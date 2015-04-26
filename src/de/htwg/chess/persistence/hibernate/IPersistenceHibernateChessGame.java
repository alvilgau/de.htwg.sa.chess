package de.htwg.chess.persistence.hibernate;

import java.util.Collection;

import de.htwg.chess.persistence.IPersistenceChessGame;

public interface IPersistenceHibernateChessGame extends IPersistenceChessGame {

	Collection<IPersistenceHibernateField> getFields();

	void setFields(Collection<IPersistenceHibernateField> fields);
}
