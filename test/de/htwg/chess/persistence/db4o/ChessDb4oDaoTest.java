package de.htwg.chess.persistence.db4o;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import de.htwg.chess.model.impl.ChessGame;
import de.htwg.chess.persistence.IChessDao;

public class ChessDb4oDaoTest {

	private static final String GAME_ID = "123";
	private static IChessDao dao;

	@BeforeClass
	public static void setUpOnce() {
		dao = new ChessDb4oDao();
	}

	@Test
	public void testSaveLoadGame() {
		ChessGame game = new ChessGame();
		game.setId(GAME_ID);

		Assert.assertNull(dao.getGame(GAME_ID));
		dao.saveGame(game);
		Assert.assertTrue(dao.containsGame(GAME_ID));
	}

	@Test
	public void testDeleteGame() {
		Assert.assertEquals(dao.getAllGames().size(), 1);
		Assert.assertTrue(dao.deleteGame(GAME_ID));
		Assert.assertFalse(dao.containsGame(GAME_ID));
		Assert.assertEquals(dao.getAllGames().size(), 0);
		Assert.assertFalse(dao.deleteGame(GAME_ID));
	}
}
