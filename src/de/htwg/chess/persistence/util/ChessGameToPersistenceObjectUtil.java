package de.htwg.chess.persistence.util;

import de.htwg.chess.model.IChessGame;
import de.htwg.chess.persistence.IPersistenceChessGame;
import de.htwg.chess.persistence.hibernate.PersistenceChessGame;

public class ChessGameToPersistenceObjectUtil {

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
