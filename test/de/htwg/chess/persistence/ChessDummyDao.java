package de.htwg.chess.persistence;

import java.util.ArrayList;
import java.util.List;

import de.htwg.chess.model.IChessGame;

public class ChessDummyDao implements IChessDao {

	private List<IChessGame> games;

	public ChessDummyDao() {
		this.games = new ArrayList<>();
	}

	@Override
	public void saveGame(IChessGame game) {
		this.games.add(game);
	}

	@Override
	public IChessGame getGame(String id) {
		for (IChessGame game : this.games) {
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
		IChessGame game = getGame(id);
		if (game != null) {
			this.games.remove(game);
			return true;
		}
		return false;
	}

	@Override
	public List<IChessGame> getAllGames() {
		return this.games;
	}

}
