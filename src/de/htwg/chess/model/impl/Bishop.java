package de.htwg.chess.model.impl;

import java.util.List;

import de.htwg.chess.model.IField;

public class Bishop extends Figure {

	/**
	 * Constructs a new Bishop object
	 * 
	 * @param x
	 *            - the x position
	 * @param y
	 *            - the y position
	 * @param team
	 *            - the team of the figure
	 */
	public Bishop(int x, int y, Team team) {
		setyPos(y);
		setxPos(x);
		setTeam(team);
	}

	@Override
	public List<IField> getPossibleMoves(IField[][] fields) {
		return moveValidator.diagonalMoveValidation(this, fields);
	}

	@Override
	public String toString() {
		return "L";
	}

}
