package de.htwg.chess.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.htwg.chess.model.IField;
import de.htwg.chess.model.IFigure.Team;
import de.htwg.chess.model.impl.Field;
import de.htwg.chess.model.impl.Figure;
import de.htwg.chess.model.impl.Pawn;

public class PawnTest {

	Figure bauer;
	Figure bauer2;
	Figure bauer3;
	Figure bauer4;
	Field field[][];

	@Before
	public void setUp() {
		this.bauer = new Pawn(1, 1, Team.white, 1);
		this.bauer2 = new Pawn(2, 2, Team.black, 6);
		this.bauer3 = new Pawn(0, 7, Team.white, 1);
		this.bauer4 = new Pawn(1, 6, Team.black, 6);
		this.field = new Field[8][8];
		for (int i = 0; i <= 7; i++) {
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
		assertEquals(1, this.bauer.getxPos());
	}

	@Test
	public void testGetPosY() {
		assertEquals(1, this.bauer.getyPos());
	}

	@Test
	public void testGetTeam() {
		assertEquals("white", this.bauer.getTeam());
	}

	@Test
	public void testPossibleMoves() {
		this.field[1][1].setFigur(this.bauer);
		this.field[1][1].setSet(true);
		this.field[2][2].setFigur(this.bauer2);
		this.field[2][2].setSet(true);
		this.field[0][2].setFigur(new Pawn(0, 2, Team.white, 1));
		this.field[0][2].setSet(true);
		this.field[0][7].setFigur(this.bauer3);
		this.field[0][7].setSet(true);
		this.field[1][6].setFigur(this.bauer4);
		this.field[1][6].setSet(true);

		List<IField> possibleMoves = this.bauer.getPossibleMoves(this.field);
		List<IField> possibleMoves2 = this.bauer2.getPossibleMoves(this.field);
		List<IField> possibleMoves3 = this.bauer3.getPossibleMoves(this.field);
		List<IField> possibleMoves4 = this.bauer4.getPossibleMoves(this.field);

		// Move back
		assertFalse(possibleMoves.contains(this.field[1][0]));

		// Move 1 field
		assertTrue(possibleMoves.contains(this.field[1][2]));
		assertTrue(possibleMoves2.contains(this.field[2][1]));

		// Move 2 fields from the start point
		assertTrue(possibleMoves.contains(this.field[1][3]));
		assertTrue(possibleMoves4.contains(this.field[1][4]));

		// Move 3 fields
		assertFalse(possibleMoves.contains(this.field[1][4]));

		// Move diagonally for a kill
		assertTrue(possibleMoves.contains(this.field[1][2]));

		// Move on figure of the same team
		assertFalse(possibleMoves.contains(this.field[0][2]));

		// No possible moves availabe
		assertTrue(possibleMoves3.isEmpty());
	}

	@Test
	public void testCheckExchange() {
		// White Pawn reaches end
		assertFalse(this.bauer.move(0, 5));
		assertTrue(this.bauer.move(0, 7));

		// Black Pawn reaches end
		assertFalse(this.bauer2.move(0, 2));
		assertTrue(this.bauer2.move(0, 0));
	}

	@Test
	public void testToString() {
		assertEquals("B", this.bauer.toString());
	}
}
