package de.htwg.chess.persistence.hibernate.util.transform;

import de.htwg.chess.model.IChessGame;
import de.htwg.chess.persistence.hibernate.PersistenceChessGame;

public class ChessGameToPersistenceObjectUtil {

	public static PersistenceChessGame transform(IChessGame chessGame) {
		
		PersistenceChessGame persistenceChessGame = new PersistenceChessGame();
		persistenceChessGame.setId(chessGame.getId());
		persistenceChessGame.setTurn(chessGame.getTurn());
		persistenceChessGame.setTurnsBlack(chessGame.getTurnsBlack());
		persistenceChessGame.setTurnsWhite(chessGame.getTurnsWhite());
		persistenceChessGame.setName(chessGame.getName());
		persistenceChessGame.setSaveDate(chessGame.getSaveDate());
		persistenceChessGame.setFields(FieldToPersistenceObjectUtil.transform(chessGame.getFields(), chessGame));
		
		return persistenceChessGame;

	}
}
