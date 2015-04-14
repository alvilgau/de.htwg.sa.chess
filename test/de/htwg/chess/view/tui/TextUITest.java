package de.htwg.chess.view.tui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.chess.ChessModuleTest;
import de.htwg.chess.controller.IChessController;
import de.htwg.chess.controller.impl.ChessController;

public class TextUITest {

	Injector injector;
	TextUI tui;
	ChessController controller;

	@Before
	public void setUp() {
		PropertyConfigurator.configure("log4j.properties");
		this.injector = Guice.createInjector(new ChessModuleTest());
		this.controller = (ChessController) this.injector.getInstance(IChessController.class);
		this.tui = this.injector.getInstance(TextUI.class);
	}

	@Test
	public void testProcessInputLine() {
		/* Movement input */
		assertFalse(this.tui.processInputLine("q"));
		assertTrue(this.tui.processInputLine("r"));
		assertTrue(this.tui.processInputLine("sa2"));
		assertTrue(this.tui.processInputLine("ma4"));
		assertTrue(this.tui.processInputLine("s2323"));
		assertTrue(this.tui.processInputLine("m4323"));

		/* exchange input */
		this.controller.setExchange(true);
		assertTrue(this.tui.processInputLine("knight"));

		this.controller.setExchange(true);
		assertTrue(this.tui.processInputLine("bishop"));

		this.controller.setExchange(true);
		assertTrue(this.tui.processInputLine("rook"));

		this.controller.setExchange(true);
		assertTrue(this.tui.processInputLine("queen"));

		this.controller.setExchange(true);
		assertTrue(this.tui.processInputLine("sa2"));

		/* game over input */
		this.controller.setExchange(false);
		this.controller.setGameover(true);
		assertTrue(this.tui.processInputLine("sa3"));
	}

	@Test
	public void testPrintTUI() {
		this.tui.printTUI();

		this.controller.setExchange(true);
		this.tui.printTUI();

		this.controller.setExchange(false);
		this.controller.setGameover(true);
		this.tui.printTUI();
	}

}
