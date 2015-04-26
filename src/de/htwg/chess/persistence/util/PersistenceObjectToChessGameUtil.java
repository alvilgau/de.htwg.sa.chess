package de.htwg.chess.persistence.util;

import de.htwg.chess.model.IChessGame;
import de.htwg.chess.model.impl.ChessGame;
import de.htwg.chess.persistence.IPersistenceChessGame;
import de.htwg.chess.persistence.couchdb.IPersistenceCouchDbChessGame;
import de.htwg.chess.persistence.hibernate.IPersistenceHibernateChessGame;

public final class PersistenceObjectToChessGameUtil {

	private PersistenceObjectToChessGameUtil() {
	}

	public static IChessGame transform(IPersistenceHibernateChessGame persistenceChessGame) {

		IChessGame chessGame = transform((IPersistenceChessGame) persistenceChessGame);
		chessGame.setFields(PersistenceObjectToFieldUtil.transform(persistenceChessGame.getFields()));
		
		return chessGame;
	}

	public static IChessGame transform(IPersistenceCouchDbChessGame persistenceChessGame) {
		
		IChessGame chessGame = transform((IPersistenceChessGame) persistenceChessGame);
		chessGame.setFields(PersistenceObjectToFieldUtil.transform(persistenceChessGame.getFields()));
		
		return chessGame;
	}
	
	private static IChessGame transform(IPersistenceChessGame persistenceChessGame) {
		
		IChessGame chessGame = new ChessGame();
		chessGame.setId(persistenceChessGame.getId());
		chessGame.setName(persistenceChessGame.getName());
		chessGame.setSaveDate(persistenceChessGame.getSaveDate());
		chessGame.setTurn(persistenceChessGame.getTurn());
		chessGame.setTurnsBlack(persistenceChessGame.getTurnsBlack());
		chessGame.setTurnsWhite(persistenceChessGame.getTurnsWhite());

		return chessGame;

	}

}
