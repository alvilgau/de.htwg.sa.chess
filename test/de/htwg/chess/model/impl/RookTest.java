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
import de.htwg.chess.model.impl.Rook;

public class RookTest {

	Figure turm;
	Field field[][];

	@Before
	public void setUp() {
		this.turm = new Rook(1, 2, Team.white);
		this.field = new Field[8][8];

		for (int i = 0; i < 8; i++) {
			this.field[0][i] = new Field();
			this.field[1][i] = new Field();
			this.field[2][i] = new Field();
			this.field[3][i] = new Field();
			this.field[4][i] = new Field();
			this.field[5][i] = new Field();
			this.field[6][i] = new Field();
			this.field[7][i] = new Field();
		}
	}

	@Test
	public void testGetPosX() {
		assertEquals(1, this.turm.getxPos());
	}

	@Test
	public void testGetPosY() {
		assertEquals(2, this.turm.getyPos());
	}

	@Test
	public void testPossibleMoves() {
		// Collision moving up
		assertTrue(this.turm.getPossibleMoves(this.field).contains(this.field[1][4]));
		this.field[1][4].setSet(true);
		this.field[1][4].setFigure(new Bishop(1, 4, Team.white));
		assertFalse(this.turm.getPossibleMoves(this.field).contains(this.field[1][4]));

		// Collision moving down
		assertTrue(this.turm.getPossibleMoves(this.field).contains(this.field[1][0]));
		this.field[1][0].setSet(true);
		this.field[1][0].setFigure(new Bishop(1, 0, Team.white));
		assertFalse(this.turm.getPossibleMoves(this.field).contains(this.field[1][0]));

		// Collision moving right
		assertTrue(this.turm.getPossibleMoves(this.field).contains(this.field[2][2]));
		this.field[2][2].setSet(true);
		this.field[2][2].setFigure(new Bishop(2, 2, Team.white));
		assertFalse(this.turm.getPossibleMoves(this.field).contains(this.field[2][2]));

		// Collision moving left
		assertTrue(this.turm.getPossibleMoves(this.field).contains(this.field[0][2]));
		this.field[0][2].setSet(true);
		this.field[0][2].setFigure(new Bishop(0, 2, Team.white));
		assertFalse(this.turm.getPossibleMoves(this.field).contains(this.field[0][2]));
	}

	@Test
	public void testToString() {
		assertEquals("T", this.turm.toString());
	}

}
