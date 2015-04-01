package de.htwg.chess.model.impl;

import java.util.List;

import de.htwg.chess.model.IField;

public class Knight extends Figure {

	private static final int[][] MOVES = { { -2, 1 }, { -1, 2 }, { 1, 2 },
			{ 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 } };

	/**
	 * Constructs a new Knight object
	 * 
	 * @param x
	 *            - the x position
	 * @param y
	 *            - the y position
	 * @param team
	 *            - the team of the figure
	 */
	public Knight(int x, int y, Team team) {
		setxPos(x);
		setyPos(y);
		setTeam(team);
	}

	@Override
	public List<IField> getPossibleMoves(IField[][] fields) {
		return moveValidator.simpleMoveValidation(this, fields, MOVES);
	}

	@Override
	public String toString() {
		return "P";
	}

}
