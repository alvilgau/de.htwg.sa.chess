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
import de.htwg.chess.model.impl.Queen;

public class QueenTest {

	Figure dame;
	Field field[][];

	@Before
	public void setUp() {
		this.dame = new Queen(1, 4, Team.black);
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
		assertEquals(1, this.dame.getxPos());
	}

	@Test
	public void testGetPosY() {
		assertEquals(4, this.dame.getyPos());
	}

	@Test
	public void testPossibleMoves() {
		// Check Collision moving up
		assertTrue(this.dame.getPossibleMoves(this.field).contains(this.field[1][5]));
		this.field[1][5].setSet(true);
		this.field[1][5].setFigur(new Bishop(1, 5, Team.black));
		assertFalse(this.dame.getPossibleMoves(this.field).contains(this.field[1][5]));

		// Check Collision moving down
		assertTrue(this.dame.getPossibleMoves(this.field).contains(this.field[1][3]));
		this.field[1][3].setSet(true);
		this.field[1][3].setFigur(new Bishop(1, 3, Team.black));
		assertFalse(this.dame.getPossibleMoves(this.field).contains(this.field[1][3]));

		// Check Collision moving right
		assertTrue(this.dame.getPossibleMoves(this.field).contains(this.field[2][4]));
		this.field[2][4].setSet(true);
		this.field[2][4].setFigur(new Bishop(2, 4, Team.black));
		assertFalse(this.dame.getPossibleMoves(this.field).contains(this.field[2][4]));

		// Check Collision moving left
		assertTrue(this.dame.getPossibleMoves(this.field).contains(this.field[0][4]));
		this.field[0][4].setSet(true);
		this.field[0][4].setFigur(new Bishop(0, 4, Team.black));
		assertFalse(this.dame.getPossibleMoves(this.field).contains(this.field[0][4]));

		// Check collision moving up left
		assertTrue(this.dame.getPossibleMoves(this.field).contains(this.field[0][5]));
		this.field[0][5].setSet(true);
		this.field[0][5].setFigur(new Bishop(0, 5, Team.black));
		assertFalse(this.dame.getPossibleMoves(this.field).contains(this.field[0][5]));

		// Check collision moving up right
		assertTrue(this.dame.getPossibleMoves(this.field).contains(this.field[2][5]));
		this.field[2][5].setSet(true);
		this.field[2][5].setFigur(new Bishop(2, 5, Team.black));
		assertFalse(this.dame.getPossibleMoves(this.field).contains(this.field[2][5]));

		// Check collision moving down left
		assertTrue(this.dame.getPossibleMoves(this.field).contains(this.field[0][3]));
		this.field[0][3].setSet(true);
		this.field[0][3].setFigur(new Bishop(0, 3, Team.black));
		assertFalse(this.dame.getPossibleMoves(this.field).contains(this.field[0][3]));

		// Check collision moving down right
		assertTrue(this.dame.getPossibleMoves(this.field).contains(this.field[2][3]));
		this.field[2][3].setSet(true);
		this.field[2][3].setFigur(new Bishop(2, 3, Team.black));
		assertFalse(this.dame.getPossibleMoves(this.field).contains(this.field[2][3]));
	}

	@Test
	public void testToString() {
		assertEquals("D", this.dame.toString());
	}

}
