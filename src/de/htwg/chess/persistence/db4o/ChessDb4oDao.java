package de.htwg.chess.persistence.db4o;

import java.io.File;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import de.htwg.chess.model.IChessGame;
import de.htwg.chess.model.impl.ChessGame;
import de.htwg.chess.persistence.IChessDao;

public class ChessDb4oDao implements IChessDao {

	private ObjectContainer db;

	public ChessDb4oDao() {
		File currentDirFile = new File("");
		String fullPath = currentDirFile.getAbsolutePath() + "\\chess.data";
		this.db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), fullPath);
	}

	@Override
	public void saveGame(IChessGame game) {
		this.db.store(game);
	}

	@Override
	public IChessGame getGame(String id) {
		Query query = this.db.query();
		query.constrain(ChessGame.class);
		query.descend("id").constrain(id);
		List<ChessGame> games = query.execute();

		if (games.size() > 0) {
			return games.get(0);
		}
		return null;
	}

	@Override
	public boolean containsGame(String id) {
		return this.getGame(id) != null;
	}

	@Override
	public boolean deleteGame(String id) {
		IChessGame game = getGame(id);
		if (game != null) {
			this.db.delete(game);
			return true;
		}
		return false;
	}

	@Override
	public List<IChessGame> getAllGames() {
		return this.db.query(IChessGame.class);
	}

}
