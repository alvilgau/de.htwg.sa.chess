package de.htwg.chess.model.impl;

import java.util.List;

import de.htwg.chess.model.FigureType;
import de.htwg.chess.model.IField;

public class Rook extends Figure {

	/**
	 * Constructs a new Rook object
	 * 
	 * @param x
	 *            - the x position
	 * @param y
	 *            - the y position
	 * @param team
	 *            - the team of the figure
	 */
	public Rook(int x, int y, Team team) {
		setxPos(x);
		setyPos(y);
		setTeam(team);
		setFigureType(FigureType.Rook);
	}

	@Override
	public List<IField> getPossibleMoves(IField[][] fields) {
		List<IField> possibleMoves = this.moveValidator.horizontalMoveValidation(this, fields);
		possibleMoves.addAll(this.moveValidator.verticalMoveValidation(this, fields));
		return possibleMoves;
	}

	@Override
	public String toString() {
		return "T";
	}

}
