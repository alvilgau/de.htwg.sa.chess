package de.htwg.chess.persistence.db4o;

import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import de.htwg.chess.persistence.ChessPojo;
import de.htwg.chess.persistence.IChessDao;

public class ChessDb4oDao implements IChessDao {

	private ObjectContainer db;

	public ChessDb4oDao() {
		this.db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "chess.data");
	}

	@Override
	public void saveGame(ChessPojo game) {
		this.db.store(game);
	}

	@Override
	public ChessPojo getGame(String id) {
		List<ChessPojo> games = this.db.query(new Predicate<ChessPojo>() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean match(ChessPojo game) {
				return game.getId().equals(id);
			}
		});

		if (games.size() > 0) {
			return games.get(0);
		}
		return null;
	}

	@Override
	public boolean containsGame(String id) {
		List<ChessPojo> games = this.db.query(new Predicate<ChessPojo>() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean match(ChessPojo game) {
				return game.getId().equals(id);
			}
		});

		return games.size() > 0;
	}

	@Override
	public boolean deleteGame(String id) {
		ChessPojo game = getGame(id);
		if (game != null) {
			this.db.delete(game);
			return true;
		}
		return false;
	}

	@Override
	public List<ChessPojo> getAllGames() {
		return this.db.query(ChessPojo.class);
	}

}
