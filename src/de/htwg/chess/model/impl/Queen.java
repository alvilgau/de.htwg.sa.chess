package de.htwg.chess.model.impl;

import java.util.List;

import de.htwg.chess.model.IField;

public class Queen extends Figure {

	/**
	 * Constructs a new Queen object
	 * 
	 * @param x
	 *            - the x position
	 * @param y
	 *            - the y position
	 * @param team
	 *            - the team of the figure
	 */
	public Queen(int x, int y, Team team) {
		setxPos(x);
		setyPos(y);
		setTeam(team);
	}

	@Override
	public List<IField> getPossibleMoves(IField[][] fields) {
		List<IField> possibleMoves = moveValidator.horizontalMoveValidation(
				this, fields);
		possibleMoves
				.addAll(moveValidator.verticalMoveValidation(this, fields));
		possibleMoves
				.addAll(moveValidator.diagonalMoveValidation(this, fields));
		return possibleMoves;
	}

	@Override
	public String toString() {
		return "D";
	}

}
