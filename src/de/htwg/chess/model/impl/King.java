package de.htwg.chess.model.impl;

import java.util.List;

import de.htwg.chess.model.FigureType;
import de.htwg.chess.model.IField;

public class King extends Figure {

	private static final int[][] MOVES = { { -1, 0 }, { -1, 1 }, { -1, -1 }, { 0, 1 }, { 0, -1 },
			{ 1, 0 }, { 1, 1 }, { 1, -1 } };

	/**
	 * Constructs a new King object
	 * 
	 * @param x
	 *            - the x position
	 * @param y
	 *            - the y position
	 * @param team
	 *            - the team of the figure
	 */
	public King(int x, int y, Team team) {
		setyPos(y);
		setxPos(x);
		setTeam(team);
		setFigureType(FigureType.King);
	}

	@Override
	public List<IField> getPossibleMoves(IField[][] fields) {
		return this.moveValidator.simpleMoveValidation(this, fields, MOVES);
	}

	@Override
	public boolean isKing() {
		return true;
	}

	@Override
	public String toString() {
		return "K";
	}

}
