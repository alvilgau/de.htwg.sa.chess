package de.htwg.chess.persistence.db4o;

import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

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
		List<ChessGame> games = this.db.query(new Predicate<ChessGame>() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean match(ChessGame game) {
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
		List<ChessGame> games = this.db.query(new Predicate<ChessGame>() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean match(ChessGame game) {
				return game.getId().equals(id);
			}
		});

		return games.size() > 0;
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
