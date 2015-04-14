package de.htwg.chess.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.chess.model.IFigure.Team;

public class FieldTest {

	Field feld;
	Field feld2;
	Field feld3;

	@Before
	public void setUp() {
		this.feld = new Field();
		this.feld2 = new Field(true, new Bishop(3, 5, Team.black));
		this.feld3 = new Field(1, 1);
	}

	@Test
	public void testPosition() {
		assertEquals(this.feld2.getxPos(), this.feld2.getFigur().getxPos());
		assertEquals(this.feld2.getyPos(), this.feld2.getFigur().getyPos());
		assertEquals(this.feld3.getxPos(), 1);
	}

	@Test
	public void testIsSet() {
		assertEquals(false, this.feld.isSet());
		this.feld.setSet(true);
		assertEquals(true, this.feld.isSet());
		assertTrue(this.feld2.isSet());
	}

	@Test
	public void testGetFigur() {
		this.feld.setFigur(new Pawn(5, 4, Team.white, 1));
		assertNotNull(this.feld.getFigur());
		assertNotNull(this.feld2.getFigur());
	}

	@Test
	public void testToString() {
		assertEquals("L", this.feld2.toString());
		assertEquals("-", this.feld.toString());
	}

	@Test
	public void testReset() {
		this.feld2.clear();
		assertFalse(this.feld2.isSet());
	}

}
