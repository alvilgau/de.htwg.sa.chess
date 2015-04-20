package de.htwg.chess.controller.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.chess.ChessModuleTest;
import de.htwg.chess.controller.IChessController;
import de.htwg.chess.model.impl.Bishop;
import de.htwg.chess.model.impl.Knight;
import de.htwg.chess.model.impl.Pawn;
import de.htwg.chess.model.impl.Queen;
import de.htwg.chess.model.impl.Rook;

public class ControllerTest {

	Injector injector;
	ChessController controller;

	@Before
	public void setUp() {
		this.injector = Guice.createInjector(new ChessModuleTest());
		this.controller = (ChessController) this.injector.getInstance(IChessController.class);
	}

	@Test
	public void testSelect() {
		// Invalid select
		this.controller.select(0, 3);
		assertFalse(this.controller.isSelect());

		// Invalid select
		this.controller.select(0, 6);
		assertFalse(this.controller.isSelect());
	}

	@Test
	public void testMove() {
		// Correct move
		assertTrue(this.controller.isWhiteTurn());
		assertFalse(this.controller.getFields()[2][2].isSet());
		this.controller.select(1, 0);
		this.controller.move(2, 2);
		assertTrue(this.controller.getFields()[2][2].isSet());

		// Correct move
		assertFalse(this.controller.isWhiteTurn());
		assertFalse(this.controller.getFields()[0][5].isSet());
		this.controller.select(0, 6);
		this.controller.move(0, 5);
		assertTrue(this.controller.getFields()[0][5].isSet());

		// Invalid move
		assertFalse(this.controller.getFields()[3][3].isSet());
		this.controller.select(0, 1);
		this.controller.move(3, 3);
		assertFalse(this.controller.getFields()[3][3].isSet());
	}

	@Test
	public void testRestart() {
		this.controller.select(1, 0);
		this.controller.move(2, 2);
		assertTrue(this.controller.getFields()[2][2].isSet());

		// Restart
		this.controller.restart();
		assertFalse(this.controller.getFields()[2][2].isSet());
	}

	@Test
	public void testHandleMovement() {
		// Select
		this.controller.handleMovement(0, 1);
		assertTrue(this.controller.isSelect());
		assertFalse(this.controller.getFields()[0][3].isSet());

		// Move
		this.controller.handleMovement(0, 3);
		assertTrue(this.controller.getFields()[0][3].isSet());

		// Fail handle because of exchange
		this.controller.setExchange(true);
		this.controller.handleMovement(0, 3);
		assertFalse(this.controller.isSelect());
		this.controller.handleMovement(0, 5);
		assertFalse(this.controller.getFields()[0][5].isSet());
		this.controller.setExchange(false);

		// Fail handle because of game over
		this.controller.setGameover(true);
		this.controller.handleMovement(0, 3);
		assertFalse(this.controller.isSelect());
		this.controller.handleMovement(0, 5);
		assertFalse(this.controller.getFields()[0][5].isSet());
	}

	@Test
	public void testExchange() {
		this.controller.select(0, 1);
		this.controller.move(0, 3);
		this.controller.select(0, 6);
		assertTrue(this.controller.getFields()[0][6].getFigure() instanceof Pawn);

		// Exchange with Knight
		this.controller.exchangeKnight();
		assertTrue(this.controller.getFields()[0][6].getFigure() instanceof Knight);

		// Exchange with Bishop
		this.controller.exchangeBishop();
		assertTrue(this.controller.getFields()[0][6].getFigure() instanceof Bishop);

		// Exchange with Rook
		this.controller.exchangeRook();
		assertTrue(this.controller.getFields()[0][6].getFigure() instanceof Rook);

		// Exchange with Queen
		this.controller.exchangeQueen();
		assertTrue(this.controller.getFields()[0][6].getFigure() instanceof Queen);
	}

	@Test
	public void testGetStatusMessage() {
		assertEquals("Welcome to Chess", this.controller.getStatusMessage());
	}

	@Test
	public void testGetFieldSize() {
		assertEquals(8, this.controller.getFieldSize());
	}

	@Test
	public void testUpdateCheckmateWhite() {
		// Move team white
		this.controller.select(4, 1);
		this.controller.move(4, 3);

		// Move team black
		this.controller.select(3, 6);
		this.controller.move(3, 4);

		// Move team white
		this.controller.select(4, 3);
		this.controller.move(4, 4);

		// Move team black
		this.controller.select(3, 7);
		this.controller.move(3, 5);

		// Move team white
		this.controller.select(0, 1);
		this.controller.move(0, 2);

		// Move team black
		this.controller.select(3, 5);
		this.controller.move(4, 4);

		// Move team white
		this.controller.select(5, 0);
		this.controller.move(4, 1);

		// Move team black
		this.controller.select(0, 6);
		this.controller.move(0, 5);
		assertFalse(this.controller.isGameover());

		// Move team white
		this.controller.select(4, 1);
		this.controller.move(3, 2);
		assertTrue(this.controller.isGameover());
	}

	@Test
	public void testUpdateCheckmateBlack() {
		// Move team white
		this.controller.select(3, 1);
		this.controller.move(3, 3);

		// Move team black
		this.controller.select(4, 6);
		this.controller.move(4, 4);

		// Move team white
		this.controller.select(3, 0);
		this.controller.move(3, 2);

		// Move team black
		this.controller.select(4, 4);
		this.controller.move(4, 3);

		// Move team white
		this.controller.select(3, 2);
		this.controller.move(4, 3);

		// Move team black
		this.controller.select(3, 7);
		this.controller.move(4, 6);

		// Move team white
		this.controller.select(0, 1);
		this.controller.move(0, 2);
		assertFalse(this.controller.isGameover());

		// Move team black
		this.controller.select(4, 6);
		this.controller.move(5, 5);
		assertTrue(this.controller.isGameover());
	}

	@Test
	public void testTurnCount() {
		assertEquals(this.controller.getTurnsBlack(), 0);
		assertEquals(this.controller.getTurnsWhite(), 0);

		this.controller.select(0, 1);
		this.controller.move(0, 3);
		assertEquals(this.controller.getTurnsWhite(), 1);

		this.controller.select(0, 6);
		this.controller.move(0, 5);
		assertEquals(this.controller.getTurnsBlack(), 1);
	}

	@Test
	public void testGetTurnMessage() {
		assertEquals("Team white's turn", this.controller.getTurnMessage());
	}

	@Test
	public void testToJson() {
		this.controller.select(0, 1);
		String json = "\"select\":true,";
		assertTrue(this.controller.toJson().contains(json));
	}

	@Test
	public void testGameBoardAsJson() {
		String json = "[{\"figure\":\"whiteT\"},{\"figure\":\"whiteP\"},{\"figure\":\"whiteL\"}";
		assertTrue(this.controller.getGameBoardAsJson().startsWith(json));
	}

	@Test
	public void testGetFieldValue() {
		String fieldValue;
		fieldValue = this.controller.getFieldValue(0, 0);
		assertEquals(fieldValue, "whiteT");

		fieldValue = this.controller.getFieldValue(0, 1);
		assertEquals(fieldValue, "whiteB");

		fieldValue = this.controller.getFieldValue(1, 7);
		assertEquals(fieldValue, "blackP");

		fieldValue = this.controller.getFieldValue(3, 3);
		assertEquals(fieldValue, "empty");
	}

	@Test
	public void testPossibleMoves() {
		assertEquals(this.controller.getSelectedFigure(), null);
		this.controller.select(0, 1);
		int[][] possMoves = this.controller.getPossibleMoves();
		assertEquals(possMoves[0][0], 0);
		assertEquals(possMoves[0][1], 2);
	}

	@Test
	public void testDB() {
		assertEquals(this.controller.getDao().getAllGames().size(), 0);
		this.controller.saveToDB("test");
		assertEquals(this.controller.getDao().getAllGames().size(), 1);

		this.controller.setGameover(true);
		this.controller.saveToDB("test2");
		assertEquals(this.controller.getDao().getAllGames().size(), 1);

		this.controller.select(0, 1);
		this.controller.move(0, 2);
		assertEquals(this.controller.getTurnsWhite(), 1);
		this.controller.loadFromDB(this.controller.getDao().getAllGames().get(0).getId());
		assertEquals(this.controller.getTurnsWhite(), 0);
	}
}
