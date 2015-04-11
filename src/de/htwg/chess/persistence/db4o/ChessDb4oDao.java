package de.htwg.chess.persistence.db4o;

import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import de.htwg.chess.persistence.ChessGame;
import de.htwg.chess.persistence.IChessDao;

public class ChessDb4oDao implements IChessDao {

	private ObjectContainer db;

	public ChessDb4oDao() {
		this.db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "chess.data");
	}

	@Override
	public void saveGame(ChessGame game) {
		this.db.store(game);
	}

	@Override
	public ChessGame getGame(String id) {
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
		ChessGame game = getGame(id);
		if (game != null) {
			this.db.delete(game);
			return true;
		}
		return false;
	}

	@Override
	public List<ChessGame> getAllGames() {
		return this.db.query(ChessGame.class);
	}

}
