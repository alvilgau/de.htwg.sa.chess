package de.htwg.chess.model.impl;

import de.htwg.chess.model.IField;
import de.htwg.chess.model.IFigure;

public abstract class Figure implements IFigure {

	static final int POS_MAX = 7;
	static final int POS_MIN = 0;

	protected MoveValidator moveValidator = new MoveValidator();

	private int xPos;
	private int yPos;
	private Team team;

	@Override
	public int getxPos() {
		return this.xPos;
	}

	@Override
	public int getyPos() {
		return this.yPos;
	}

	/**
	 * Method to change the x position of a figure
	 * 
	 * @param xPos
	 *            - the new x position
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	/**
	 * Method to change the y position of a figure
	 * 
	 * @param yPos
	 *            - the new y position
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	/**
	 * Sets the team of a figure
	 * 
	 * @param team
	 *            - the new team
	 */
	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public String getTeam() {
		return this.team.toString();
	}

	@Override
	public int getTeamNumber() {
		return this.team.ordinal();
	}

	@Override
	public boolean move(int x, int y) {
		this.xPos = x;
		this.yPos = y;
		return false;
	}

	/**
	 * Gets a neighbor field
	 * 
	 * @param x
	 *            - vertical distance
	 * @param y
	 *            - horizontal distance
	 * @param fields
	 *            - current playground
	 * @return the corresponding field
	 */
	public IField getNeighbour(int x, int y, IField[][] fields) {
		int newXPos = this.xPos + x;
		int newYPos = this.yPos + y;

		if (newXPos > POS_MAX || newYPos > POS_MAX || newXPos < POS_MIN || newYPos < POS_MIN) {
			return null;
		}

		return fields[newXPos][newYPos];
	}

}
