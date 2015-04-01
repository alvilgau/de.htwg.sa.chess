package de.htwg.chess.model;

import de.htwg.chess.model.IFigure.Team;

public interface IFigureFacotry {

	/**
	 * Constructs a new Bishop object
	 * 
	 * @param x
	 *            - position
	 * @param y
	 *            - position
	 * @param team
	 *            - the team of the figure
	 */
	IFigure createBishop(int x, int y, Team team);

	/**
	 * Constructs a new King object
	 * 
	 * @param x
	 *            - position
	 * @param y
	 *            - position
	 * @param team
	 *            - the team of the figure
	 */
	IFigure createKing(int x, int y, Team team);

	/**
	 * Constructs a new Knight object
	 * 
	 * @param x
	 *            - position
	 * @param y
	 *            - position
	 * @param team
	 *            - the team of the figure
	 */
	IFigure createKnight(int x, int y, Team team);

	/**
	 * Constructs a new Pawn object
	 * 
	 * @param x
	 *            - position
	 * @param y
	 *            - position
	 * @param team
	 *            - the team of the figure
	 * @param startPosY
	 *            - vertical start position of pawn
	 */
	IFigure createPawn(int x, int y, Team team, int startPosY);

	/**
	 * Constructs a new Queen object
	 * 
	 * @param x
	 *            - position
	 * @param y
	 *            - position
	 * @param team
	 *            - the team of the figure
	 */
	IFigure createQueen(int x, int y, Team team);

	/**
	 * Constructs a new Rook object
	 * 
	 * @param x
	 *            - position
	 * @param y
	 *            - position
	 * @param team
	 *            - the team of the figure
	 */
	IFigure createRook(int x, int y, Team team);
}
