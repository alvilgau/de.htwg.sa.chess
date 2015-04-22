//package de.htwg.chess.persistence.hibernate;
//
//import java.util.Date;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import com.google.inject.Guice;
//import com.google.inject.Injector;
//
//import de.htwg.chess.ChessModuleTest;
//import de.htwg.chess.controller.IChessController;
//import de.htwg.chess.model.IChessGame;
//import de.htwg.chess.model.impl.ChessGame;
//
//public class ChessHibernateDaoTest {
//
//	private static final String GAME_ID = "123";
//	private static ChessHibernateDao dao;
//	private static IChessController controller;
//	private IChessGame game;
//
//	@BeforeClass
//	public static void setUpOnce() {
//		Injector injector = Guice.createInjector(new ChessModuleTest());
//		controller = injector.getInstance(IChessController.class);
//		dao = new ChessHibernateDao();
//	}
//
//	@Before
//	public void setUp() {
//		this.game = new ChessGame();
//		this.game.setId(GAME_ID);
//		this.game.setName("blaa");
//		this.game.setSaveDate(new Date());
//		this.game.setTurn(0);
//		this.game.setTurnsBlack(0);
//		this.game.setTurnsWhite(0);
//		this.game.setFields(controller.getFields());
//	}
//
//	@Test
//	public void testSaveLoadGame() {
//		Assert.assertNull(dao.getGame(GAME_ID));
//		dao.saveGame(this.game);
//		Assert.assertTrue(dao.containsGame(GAME_ID));
//	}
//
//	@Test
//	public void testDeleteGame() {
//		int games = dao.getAllGames().size();
//		Assert.assertTrue(dao.deleteGame(GAME_ID));
//		Assert.assertFalse(dao.containsGame(GAME_ID));
//		Assert.assertEquals(dao.getAllGames().size(), games - 1);
//		Assert.assertFalse(dao.deleteGame(GAME_ID));
//	}
//
// }
