package de.htwg.chess.model.impl;

import java.util.ArrayList;
import java.util.List;

import de.htwg.chess.model.IField;

public class Pawn extends Figure {

	private int startPosY;

	/**
	 * Constructs a new Pawn object
	 * 
	 * @param x
	 *            - the x position
	 * @param y
	 *            - the y position
	 * @param team
	 *            - the team of the figure
	 * @param startPosY
	 *            - vertical start position of pawn
	 */
	public Pawn(int x, int y, Team team, int startPosY) {
		setxPos(x);
		setyPos(y);
		setTeam(team);
		this.startPosY = startPosY;
	}

	@Override
	public List<IField> getPossibleMoves(IField[][] fields) {
		List<IField> possibleMoves = new ArrayList<IField>();
		int dY = getTeamNumber() == Team.white.ordinal() ? 1 : -1;

		/* Move 1 field forward */
		IField field = getNeighbour(0, dY, fields);
		if (field != null && !field.isSet()) {
			possibleMoves.add(field);

			/* Move 2 fields forward */
			if (getyPos() == startPosY) {
				int dY2 = getTeamNumber() == Team.white.ordinal() ? 2 : -2;
				field = getNeighbour(0, dY2, fields);
				if (field != null && !field.isSet()) {
					possibleMoves.add(field);
				}
			}
		}

		/* Possible kills */
		possibleKill(1, dY, fields, possibleMoves);
		possibleKill(-1, dY, fields, possibleMoves);

		return possibleMoves;
	}

	private void possibleKill(int dX, int dY, IField[][] fields,
			List<IField> possibleMoves) {
		IField field = getNeighbour(dX, dY, fields);
		if (field != null && field.isSet()
				&& field.getFigur().getTeamNumber() != getTeamNumber()) {
			possibleMoves.add(field);
		}
	}

	@Override
	public boolean move(int x, int y) {
		setxPos(x);
		setyPos(y);

		if (getTeam().equals("white")) {
			return getyPos() == POS_MAX;
		} else {
			return getyPos() == POS_MIN;
		}
	}

	@Override
	public String toString() {
		return "B";
	}

}
