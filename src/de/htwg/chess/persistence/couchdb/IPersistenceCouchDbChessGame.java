package de.htwg.chess.persistence.couchdb;

import java.util.Collection;

import de.htwg.chess.persistence.IPersistenceChessGame;

public interface IPersistenceCouchDbChessGame extends IPersistenceChessGame {

	Collection<IPersistenceCouchDbField> getFields();

	void setFields(Collection<IPersistenceCouchDbField> fields);
}
