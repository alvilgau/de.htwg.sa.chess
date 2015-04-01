package de.htwg.chess.controller.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.chess.ChessModule;
import de.htwg.chess.controller.IChessController;
import de.htwg.chess.controller.impl.ChessController;

public class ControllerTest {

	Injector injector;
	ChessController controller;
	String start;

	@Before
	public void setUp() {
		this.injector = Guice.createInjector(new ChessModule());
		this.controller = (ChessController) this.injector.getInstance(IChessController.class);
		this.start = this.controller.toString();
	}

	@Test
	public void testToString() {
		assertTrue(this.controller.toString().contains("T  P  L  D  K  L  P  T  "));
	}

	@Test
	public void testSelect() {
		// Invalid select
		this.controller.select(0, 3);
		this.controller.move(3, 3);
		assertFalse(this.controller.isSelect());
		assertTrue(this.start.equals(this.controller.toString()));

		// Invalid select
		this.controller.select(0, 6);
		this.controller.move(0, 5);
		assertTrue(this.start.equals(this.controller.toString()));
	}

	@Test
	public void testMove() {
		// Correct move
		assertTrue(this.controller.isWhiteTurn());
		this.controller.select(1, 0);
		this.controller.move(2, 2);
		assertFalse(this.start.equals(this.controller.toString()));
		this.start = this.controller.toString();

		// Correct move
		assertFalse(this.controller.isWhiteTurn());
		this.controller.select(0, 6);
		this.controller.move(0, 5);
		assertFalse(this.start.equals(this.controller.toString()));
		this.start = this.controller.toString();

		// Invalid move
		this.controller.select(0, 1);
		this.controller.move(3, 3);
		assertTrue(this.start.equals(this.controller.toString()));
	}

	@Test
	public void testRestart() {
		this.controller.select(1, 0);
		this.controller.move(2, 2);
		assertFalse(this.start.equals(this.controller.toString()));

		// Restart
		this.controller.restart();
		assertEquals(this.start, this.controller.toString());
	}

	@Test
	public void testHandleMovement() {
		// Select
		this.controller.handleMovement(0, 1);
		// Move
		this.controller.handleMovement(0, 3);
		assertFalse(this.start.equals(this.controller.toString()));
		this.start = this.controller.toString();

		// Fail handle because of exchange
		this.controller.setExchange(true);
		this.controller.handleMovement(0, 3);
		this.controller.handleMovement(0, 5);
		assertTrue(this.start.equals(this.controller.toString()));
		this.controller.setExchange(false);

		// Fail handle because of game over
		this.controller.setGameover(true);
		this.controller.handleMovement(0, 3);
		this.controller.handleMovement(0, 5);
		assertTrue(this.start.equals(this.controller.toString()));
	}

	@Test
	public void testExchange() {
		this.controller.select(0, 1);
		this.controller.move(0, 3);
		this.controller.select(0, 7);

		// Exchange with Knight
		this.controller.exchangeKnight();
		assertTrue(this.controller.toString().contains("P  P  L  D  K  L  P  T  "));

		// Exchange with Bishop
		this.controller.exchangeBishop();
		assertTrue(this.controller.toString().contains("L  P  L  D  K  L  P  T  "));

		// Exchange with Rook
		this.controller.exchangeRook();
		assertTrue(this.controller.toString().contains("T  P  L  D  K  L  P  T  "));

		// Exchange with Queen
		this.controller.exchangeQueen();
		assertTrue(this.controller.toString().contains("D  P  L  D  K  L  P  T  "));
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
	public void testGetTurnMessage() {
		assertEquals("Team white's turn", this.controller.getTurnMessage());
	}

	@Test
	public void testToJson() {
		this.controller.select(0, 1);
		String json = "{\"checkmateMessage\":\"\",\"select\":true,";
		assertTrue(this.controller.toJson().startsWith(json));
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
}
