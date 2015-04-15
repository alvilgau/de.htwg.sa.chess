package de.htwg.chess.model.impl;

import de.htwg.chess.model.IField;
import de.htwg.chess.model.IFigure;

public class Field implements IField {

	private boolean set;
	private Figure figur;
	private int xPos;
	private int yPos;

	/**
	 * Constructs a new Field object with default values
	 */
	public Field() {
		this.set = false;
		this.figur = null;
	}

	/**
	 * Constructs a new Field object
	 * 
	 * @param x
	 *            - position
	 * @param y
	 *            - position
	 */
	public Field(int x, int y) {
		this.xPos = x;
		this.yPos = y;
		this.set = false;
		this.figur = null;
	}

	/**
	 * Constructs a new Field object
	 * 
	 * @param set
	 *            - field is set or not
	 * @param figur
	 *            - reference to a figure object
	 */
	public Field(boolean set, IFigure figur) {
		this.set = set;
		this.figur = (Figure) figur;
		this.xPos = figur.getxPos();
		this.yPos = figur.getyPos();
	}

	@Override
	public void setSet(boolean set) {
		this.set = set;
	}

	@Override
	public boolean isSet() {
		return this.set;
	}

	@Override
	public Figure getFigure() {
		return this.figur;
	}

	@Override
	public void setFigure(IFigure figure) {
		this.figur = (Figure) figure;
	}

	@Override
	public int getxPos() {
		return this.xPos;
	}

	@Override
	public int getyPos() {
		return this.yPos;
	}

	@Override
	public void clear() {
		this.set = false;
		this.figur = null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (this.set) {
			sb.append(this.figur.toString());
		} else {
			sb.append("-");
		}
		return sb.toString();
	}

}
