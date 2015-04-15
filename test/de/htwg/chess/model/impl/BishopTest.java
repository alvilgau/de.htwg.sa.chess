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

public class BishopTest {

	Figure laeufer;
	Field field[][];

	@Before
	public void setUp() {
		this.laeufer = new Bishop(1, 4, Team.white);
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
		assertEquals(1, this.laeufer.getxPos());
	}

	@Test
	public void testGetPosY() {
		assertEquals(4, this.laeufer.getyPos());
	}

	@Test
	public void testGetTeam() {
		assertEquals("white", this.laeufer.getTeam());
	}

	@Test
	public void testPossibleMoves() {
		// Check collision moving up left
		assertTrue(this.laeufer.getPossibleMoves(this.field).contains(this.field[0][5]));
		this.field[0][5].setSet(true);
		this.field[0][5].setFigure(new Bishop(0, 5, Team.white));
		assertFalse(this.laeufer.getPossibleMoves(this.field).contains(this.field[0][5]));

		// Check collision moving up right
		assertTrue(this.laeufer.getPossibleMoves(this.field).contains(this.field[2][5]));
		this.field[2][5].setSet(true);
		this.field[2][5].setFigure(new Bishop(2, 5, Team.white));
		assertFalse(this.laeufer.getPossibleMoves(this.field).contains(this.field[2][5]));

		// Check collision moving down left
		assertTrue(this.laeufer.getPossibleMoves(this.field).contains(this.field[0][3]));
		this.field[0][3].setSet(true);
		this.field[0][3].setFigure(new Bishop(0, 3, Team.white));
		assertFalse(this.laeufer.getPossibleMoves(this.field).contains(this.field[0][3]));

		// Check collision moving down right
		assertTrue(this.laeufer.getPossibleMoves(this.field).contains(this.field[2][3]));
		this.field[2][3].setSet(true);
		this.field[2][3].setFigure(new Bishop(2, 3, Team.white));
		assertFalse(this.laeufer.getPossibleMoves(this.field).contains(this.field[2][3]));
	}

	@Test
	public void testToString() {
		assertEquals("L", this.laeufer.toString());
	}

}
