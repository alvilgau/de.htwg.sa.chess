package de.htwg.chess.persistence.util;

import de.htwg.chess.model.IChessGame;
import de.htwg.chess.persistence.IPersistenceChessGame;
import de.htwg.chess.persistence.couchdb.IPersistenceCouchDbChessGame;
import de.htwg.chess.persistence.hibernate.IPersistenceHibernateChessGame;

public final class ChessGameToPersistenceObjectUtil {

	private ChessGameToPersistenceObjectUtil() {
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends IPersistenceChessGame>T transform(IChessGame chessGame, Class<T> type) {
		
		if(type.isAssignableFrom(IPersistenceHibernateChessGame.class)) {
			return (T) transformHibernate(chessGame);
		}
		else if(type.isAssignableFrom(IPersistenceCouchDbChessGame.class)) {
			return (T) transformCouchDb(chessGame);
		}
		return null;
	}

	private static IPersistenceHibernateChessGame transformHibernate(IChessGame chessGame) {

		IPersistenceHibernateChessGame persistenceChessGame = new de.htwg.chess.persistence.hibernate.PersistenceChessGame();
		persistenceChessGame.setId(chessGame.getId());
		persistenceChessGame.setTurn(chessGame.getTurn());
		persistenceChessGame.setTurnsBlack(chessGame.getTurnsBlack());
		persistenceChessGame.setTurnsWhite(chessGame.getTurnsWhite());
		persistenceChessGame.setName(chessGame.getName());
		persistenceChessGame.setSaveDate(chessGame.getSaveDate());
		persistenceChessGame.setFields(FieldToPersistenceObjectUtil.transform(
				chessGame.getFields(), persistenceChessGame));
		
		return persistenceChessGame;

	}
	
	private static IPersistenceCouchDbChessGame transformCouchDb(IChessGame chessGame) {
		
		IPersistenceCouchDbChessGame persistenceChessGame = new de.htwg.chess.persistence.couchdb.PersistenceChessGame();
		persistenceChessGame.setId(chessGame.getId());
		persistenceChessGame.setTurn(chessGame.getTurn());
		persistenceChessGame.setTurnsBlack(chessGame.getTurnsBlack());
		persistenceChessGame.setTurnsWhite(chessGame.getTurnsWhite());
		persistenceChessGame.setName(chessGame.getName());
		persistenceChessGame.setSaveDate(chessGame.getSaveDate());
		persistenceChessGame.setFields(FieldToPersistenceObjectUtil.transform(
				chessGame.getFields(), persistenceChessGame));
		
		return persistenceChessGame;
		
	}
}
