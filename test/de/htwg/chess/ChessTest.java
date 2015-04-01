package de.htwg.chess;

import java.io.StringBufferInputStream;

import org.junit.Before;
import org.junit.Test;

import de.htwg.chess.Chess;

@SuppressWarnings("deprecation")
public class ChessTest {
	
	String input;
	
	@Before
	public void setUp() {
		input = new String("q");
	}
	
	@Test
	public void testMain() {
		System.setIn(new StringBufferInputStream(input));
		Chess.main(new String[]{});
	}
	
}