package de.htwg.chess.model.impl;

import java.util.ArrayList;
import java.util.List;

import de.htwg.chess.model.IField;
import de.htwg.chess.model.IFigure;

public final class MoveValidator {

	/**
	 * Checks for collision
	 * 
	 * @param fig
	 *            - the figure that want to get move
	 * @param field
	 *            - the field that have to be checked for other figures
	 * @param possibleMoves
	 *            - list where the possible moves will be saved
	 * @return true if a collsion will be detected
	 */
	private boolean checkCollision(IFigure fig, IField field,
			List<IField> possibleMoves) {
		if (field != null) {
			if (field.isSet()) {
				if (field.getFigur().getTeamNumber() != fig.getTeamNumber()) {
					possibleMoves.add(field);
				}
				return true;
			} else {
				possibleMoves.add(field);
			}
		}
		return false;
	}

	/**
	 * Checks which moves can be done by a figure
	 * 
	 * @param fig
	 *            - the figure that want to get moved
	 * @param fields
	 *            - current playground
	 * @param moves
	 *            - moves that the figure can do
	 * @return a list with the possible moves of the figure
	 */
	public List<IField> simpleMoveValidation(Figure fig, IField[][] fields,
			int[][] moves) {
		List<IField> possibleMoves = new ArrayList<IField>();
		for (int[] move : moves) {
			checkCollision(fig, fig.getNeighbour(move[0], move[1], fields),
					possibleMoves);
		}
		return possibleMoves;
	}

	/**
	 * Checks for possible moves when moving left or right
	 * 
	 * @param fig
	 *            - figure that want to get moved
	 * @param fields
	 *            - current playground
	 * @return a list with the possible moves of the figure
	 */
	public List<IField> verticalMoveValidation(IFigure fig, IField[][] fields) {
		List<IField> possibleMoves = new ArrayList<IField>();

		// moving right
		for (int i = fig.getxPos() + 1; i <= Figure.POS_MAX; i++) {
			if (checkCollision(fig, fields[i][fig.getyPos()], possibleMoves)) {
				break;
			}
		}

		// moving left
		for (int i = fig.getxPos() - 1; i >= Figure.POS_MIN; i--) {
			if (checkCollision(fig, fields[i][fig.getyPos()], possibleMoves)) {
				break;
			}
		}
		return possibleMoves;
	}

	/**
	 * Checks for possible moves when moving up or down
	 * 
	 * @param fig
	 *            - figure that want to get moved
	 * @param fields
	 *            - current playground
	 * @return a list with the possible moves of the figure
	 */
	public List<IField> horizontalMoveValidation(IFigure fig, IField[][] fields) {
		List<IField> possibleMoves = new ArrayList<IField>();

		// moving up
		for (int i = fig.getyPos() + 1; i <= Figure.POS_MAX; i++) {
			if (checkCollision(fig, fields[fig.getxPos()][i], possibleMoves)) {
				break;
			}
		}

		// moving down
		for (int i = fig.getyPos() - 1; i >= Figure.POS_MIN; i--) {
			if (checkCollision(fig, fields[fig.getxPos()][i], possibleMoves)) {
				break;
			}
		}
		return possibleMoves;
	}

	/**
	 * Checks for possible moves when moving diagonally
	 * 
	 * @param fig
	 *            - figure that want to get moved
	 * @param fields
	 *            - current playground
	 * @return a list with the possible moves of the figure
	 */
	public List<IField> diagonalMoveValidation(IFigure fig, IField[][] fields) {
		List<IField> possibleMoves = new ArrayList<IField>();
		int x = fig.getxPos() - 1;
		int y = fig.getyPos() + 1;

		// moving left-up
		while (x >= Figure.POS_MIN && y <= Figure.POS_MAX) {
			if (checkCollision(fig, fields[x][y], possibleMoves)) {
				break;
			}
			x--;
			y++;
		}

		// moving left-down
		x = fig.getxPos() - 1;
		y = fig.getyPos() - 1;
		while (x >= Figure.POS_MIN && y >= Figure.POS_MIN) {
			if (checkCollision(fig, fields[x][y], possibleMoves)) {
				break;
			}
			x--;
			y--;
		}

		// moving right-up
		x = fig.getxPos() + 1;
		y = fig.getyPos() + 1;
		while (x <= Figure.POS_MAX && y <= Figure.POS_MAX) {
			if (checkCollision(fig, fields[x][y], possibleMoves)) {
				break;
			}
			x++;
			y++;
		}

		// moving right-down
		x = fig.getxPos() + 1;
		y = fig.getyPos() - 1;
		while (x <= Figure.POS_MAX && y >= Figure.POS_MIN) {
			if (checkCollision(fig, fields[x][y], possibleMoves)) {
				break;
			}
			x++;
			y--;
		}

		return possibleMoves;
	}
}
