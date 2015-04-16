package de.htwg.chess.persistence.hibernate.util.transform;

import de.htwg.chess.model.IChessGame;
import de.htwg.chess.model.impl.ChessGame;
import de.htwg.chess.persistence.hibernate.PersistenceChessGame;

public class PersistenceObjectToChessGameUtil {

	public static IChessGame transform(PersistenceChessGame persistenceChessGame) {
		
		IChessGame chessGame = new ChessGame();
		chessGame.setId(persistenceChessGame.getId());
		chessGame.setName(persistenceChessGame.getName());
		chessGame.setSaveDate(persistenceChessGame.getSaveDate());
		chessGame.setTurn(persistenceChessGame.getTurn());
		chessGame.setTurnsBlack(persistenceChessGame.getTurnsBlack());
		chessGame.setTurnsWhite(persistenceChessGame.getTurnsWhite());
		chessGame.setFields(PersistenceObjectToFieldUtil.transform(persistenceChessGame.getFields()));
		
		return chessGame;
	}
}