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
import de.htwg.chess.controller.impl.Checkmate.CheckMate;

public class CheckmateTest {

	Injector injector;
	IChessController controller;

	@Before
	public void setUp() {
		this.injector = Guice.createInjector(new ChessModuleTest());
		this.controller = this.injector.getInstance(IChessController.class);
	}

	@Test
	public void testCheckmateWhite() {
		// Move team white
		this.controller.select(4, 1);
		this.controller.move(4, 3);
		assertEquals("", this.controller.getCheckmateMessage());
		assertFalse(this.controller.getCheckmate().isCheckBlack());
		assertFalse(this.controller.getCheckmate().isCheckWhite());
		assertFalse(this.controller.getCheckmate().isMateBlack());
		assertFalse(this.controller.getCheckmate().isMateWhite());

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
		assertFalse(this.controller.isGameover());
		// Test check
		assertTrue(this.controller.getCheckmate().isCheckWhite());
		assertEquals("White King is in a check! ", this.controller.getCheckmateMessage());

		// Move team white
		this.controller.select(0, 2);
		this.controller.move(0, 3);
		assertTrue(this.controller.isGameover());
		// Test check mate
		assertTrue(this.controller.getCheckmate().isMateWhite());
		assertEquals("White King is mate. Team Black has won. - Game Over! ",
				this.controller.getCheckmateMessage());
	}

	@Test
	public void testCheckmateBlack() {
		// Move team white
		this.controller.select(3, 1);
		this.controller.move(3, 3);
		assertEquals("", this.controller.getCheckmateMessage());
		assertFalse(this.controller.getCheckmate().isCheckBlack());
		assertFalse(this.controller.getCheckmate().isCheckWhite());
		assertFalse(this.controller.getCheckmate().isMateBlack());
		assertFalse(this.controller.getCheckmate().isMateWhite());

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
		assertFalse(this.controller.isGameover());
		// Test check
		assertTrue(this.controller.getCheckmate().isCheckBlack());
		assertEquals("Black King is in a check!", this.controller.getCheckmateMessage());

		// Move team black
		this.controller.select(0, 6);
		this.controller.move(0, 5);
		assertTrue(this.controller.isGameover());
		// Test check mate
		assertTrue(this.controller.getCheckmate().isMateBlack());
		assertEquals("Black King is mate. Team White has won. - Game Over!",
				this.controller.getCheckmateMessage());
	}

	@Test
	public void testEnum() {
		CheckMate cMate = CheckMate.valueOf("check");
		assertEquals("check", cMate.toString());
	}
}
