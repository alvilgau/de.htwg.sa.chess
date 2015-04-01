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
		return set;
	}

	@Override
	public Figure getFigur() {
		return figur;
	}

	@Override
	public void setFigur(IFigure figure) {
		this.figur = (Figure) figure;
	}

	@Override
	public int getxPos() {
		return xPos;
	}

	@Override
	public int getyPos() {
		return yPos;
	}

	@Override
	public boolean isKing() {
		return figur.toString().equals("K");
	}

	@Override
	public void clear() {
		set = false;
		figur = null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (set) {
			sb.append(figur.toString());
		} else {
			sb.append("-");
		}
		return sb.toString();
	}

}
