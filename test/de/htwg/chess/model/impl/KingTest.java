package de.htwg.chess.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.chess.model.IFigure.Team;
import de.htwg.chess.model.impl.Field;
import de.htwg.chess.model.impl.Figure;
import de.htwg.chess.model.impl.King;
import de.htwg.chess.model.impl.Queen;

public class KingTest {

	Figure koenig;
	Field field[][];

	@Before
	public void setUp() {
		this.koenig = new King(0, 0, Team.white);
		this.field = new Field[4][8];

		for (int i = 0; i < 8; i++) {
			this.field[0][i] = new Field();
			this.field[1][i] = new Field();
			this.field[2][i] = new Field();
			this.field[3][i] = new Field();
		}
	}

	@Test
	public void testGetPosX() {
		assertEquals(0, this.koenig.getxPos());
	}

	@Test
	public void testGetPosY() {
		assertEquals(0, this.koenig.getyPos());
	}

	@Test
	public void testPossibleMoves() {
		// Collision with figure of same team
		assertTrue(this.koenig.getPossibleMoves(this.field).contains(this.field[1][1]));
		this.field[1][1].setSet(true);
		this.field[1][1].setFigur(new Queen(2, 1, Team.white));
		assertFalse(this.koenig.getPossibleMoves(this.field).contains(this.field[1][1]));

		// Kill collision
		assertTrue(this.koenig.getPossibleMoves(this.field).contains(this.field[0][1]));
		this.field[0][1].setSet(true);
		this.field[0][1].setFigur(new Queen(0, 1, Team.black));
		assertTrue(this.koenig.getPossibleMoves(this.field).contains(this.field[0][1]));
	}

	@Test
	public void testToString() {
		assertEquals("K", this.koenig.toString());
	}
}
