package de.htwg.chess.persistence;

import java.util.ArrayList;
import java.util.List;

public class ChessDummyDao implements IChessDao {

	private List<ChessGame> games;

	public ChessDummyDao() {
		this.games = new ArrayList<>();
	}

	@Override
	public void saveGame(ChessGame game) {
		this.games.add(game);
	}

	@Override
	public ChessGame getGame(String id) {
		for (ChessGame game : this.games) {
			if (game.getId().equals(id)) {
				return game;
			}
		}
		return null;
	}

	@Override
	public boolean containsGame(String id) {
		return getGame(id) != null;
	}

	@Override
	public boolean deleteGame(String id) {
		ChessGame game = getGame(id);
		if (game != null) {
			this.games.remove(game);
			return true;
		}
		return false;
	}

	@Override
	public List<ChessGame> getAllGames() {
		return this.games;
	}

}
