package de.htwg.chess.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.chess.model.IFigure.Team;
import de.htwg.chess.model.impl.Bishop;
import de.htwg.chess.model.impl.Field;
import de.htwg.chess.model.impl.Figure;
import de.htwg.chess.model.impl.Knight;

public class KnightTest {

	Figure springer;
	Field field[][];

	@Before
	public void setUp() {
		this.springer = new Knight(1, 3, Team.white);
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
		assertEquals(1, this.springer.getxPos());
	}

	@Test
	public void testGetPosY() {
		assertEquals(3, this.springer.getyPos());
	}

	@Test
	public void testPossibleMoves() {
		// Collision with figure of the same team
		assertTrue(this.springer.getPossibleMoves(this.field).contains(this.field[3][4]));
		this.field[3][4].setSet(true);
		this.field[3][4].setFigur(new Bishop(3, 4, Team.white));
		assertFalse(this.springer.getPossibleMoves(this.field).contains(this.field[3][4]));

		// Kill collision
		assertTrue(this.springer.getPossibleMoves(this.field).contains(this.field[0][5]));
		this.field[0][5].setSet(true);
		this.field[0][5].setFigur(new Bishop(3, 4, Team.black));
		assertTrue(this.springer.getPossibleMoves(this.field).contains(this.field[0][5]));
	}

	@Test
	public void testToString() {
		assertEquals("P", this.springer.toString());
	}
}
