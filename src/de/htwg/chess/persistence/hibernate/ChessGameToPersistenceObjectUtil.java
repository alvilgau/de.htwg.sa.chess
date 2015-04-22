package de.htwg.chess.persistence.hibernate;

import de.htwg.chess.model.IChessGame;
import de.htwg.chess.persistence.IPersistenceChessGame;

public final class ChessGameToPersistenceObjectUtil {

	private ChessGameToPersistenceObjectUtil() {
	}

	public static IPersistenceChessGame transform(IChessGame chessGame) {

		IPersistenceChessGame persistenceChessGame = new PersistenceChessGame();
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
