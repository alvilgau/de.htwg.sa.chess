package de.htwg.chess.controller.impl;

import java.util.List;

import de.htwg.chess.model.IField;
import de.htwg.chess.model.IFigure;

public class Checkmate {

	public static enum CheckMate {
		save, check, mate;
		private CheckMate next() {
			return CheckMate.values()[this.ordinal() + 1];
		}
	}

	private CheckMate checkmateWhite;
	private CheckMate checkmateBlack;
	private String statusMessageWhite;
	private String statusMessageBlack;

	/**
	 * Constructs a new Checkmate object
	 */
	public Checkmate() {
		this.checkmateWhite = CheckMate.save;
		this.checkmateBlack = CheckMate.save;
		this.statusMessageWhite = "";
		this.statusMessageBlack = "";
	}

	/**
	 * Sets all attributes to the default value
	 */
	public void reset() {
		this.checkmateWhite = CheckMate.save;
		this.checkmateBlack = CheckMate.save;
		this.statusMessageWhite = "";
		this.statusMessageBlack = "";
	}

	/**
	 * @return true if at least one king is mate
	 */
	public boolean isMateWhite() {
		return this.checkmateWhite == CheckMate.mate;
	}

	/**
	 * @return true if at least one king is mate
	 */
	public boolean isMateBlack() {
		return this.checkmateBlack == CheckMate.mate;
	}

	/**
	 * @return true if the white king is in a check
	 */
	public boolean isCheckWhite() {
		return this.checkmateWhite == CheckMate.check;
	}

	/**
	 * @return true if the black king is in a check
	 */
	public boolean isCheckBlack() {
		return this.checkmateBlack == CheckMate.check;
	}

	/**
	 * Switches to next state of the white king
	 */
	public void nextStateWhite() {
		this.checkmateWhite = this.checkmateWhite.next();
	}

	/**
	 * Switches to next state of the black king
	 */
	public void nextStateBlack() {
		this.checkmateBlack = this.checkmateBlack.next();
	}

	/**
	 * Checks if the kings are checkmated
	 * 
	 * @param teamWhite
	 *            - list of all figures of the white team
	 * @param teamBlack
	 *            - list of all figures of the black team
	 * @param fields
	 *            - the current playground
	 */
	public void update(List<IFigure> teamWhite, List<IFigure> teamBlack,
			IField fields[][]) {
		updateTeamWhite(teamWhite, teamBlack, fields);
		updateTeamBlack(teamWhite, teamBlack, fields);
	}

	/**
	 * Checks if the white king is mate
	 * 
	 * @param teamWhite
	 *            - list of all figures of the white team
	 * @param teamBlack
	 *            - list of all figures of the black team
	 * @param fields
	 *            - the current playground
	 */
	private void updateTeamWhite(List<IFigure> teamWhite,
			List<IFigure> teamBlack, IField fields[][]) {
		int xPos = teamWhite.get(0).getxPos();
		int yPos = teamWhite.get(0).getyPos();
		boolean updated = false;

		for (IFigure fig : teamBlack) {
			if (fig.getPossibleMoves(fields).contains(fields[xPos][yPos])) {
				nextStateWhite();
				updated = true;
				break;
			}
		}

		if (!updated) {
			this.checkmateWhite = CheckMate.save;
		}
	}

	/**
	 * Checks if the black king is mate
	 * 
	 * @param teamWhite
	 *            - list of all figures of the white team
	 * @param teamBlack
	 *            - list of all figures of the black team
	 * @param fields
	 *            - the current playground
	 */
	private void updateTeamBlack(List<IFigure> teamWhite,
			List<IFigure> teamBlack, IField fields[][]) {
		int xPos = teamBlack.get(0).getxPos();
		int yPos = teamBlack.get(0).getyPos();
		boolean updated = false;

		for (IFigure fig : teamWhite) {
			if (fig.getPossibleMoves(fields).contains(fields[xPos][yPos])) {
				nextStateBlack();
				updated = true;
				break;
			}
		}

		if (!updated) {
			this.checkmateBlack = CheckMate.save;
		}
	}

	/**
	 * Getter for the status message of the kings
	 * 
	 * @return status message of the kings
	 */
	public String getStatusMessage() {
		switch (this.checkmateWhite) {
		case save:
			this.statusMessageWhite = "";
			break;
		case check:
			this.statusMessageWhite = "White King is in a check! ";
			break;
		default:
			this.statusMessageWhite = "White King is mate. Team Black has won. - Game Over! ";
			break;
		}

		switch (this.checkmateBlack) {
		case save:
			this.statusMessageBlack = "";
			break;
		case check:
			this.statusMessageBlack = "Black King is in a check!";
			break;
		default:
			this.statusMessageBlack = "Black King is mate. Team White has won. - Game Over!";
			break;
		}

		return this.statusMessageWhite + "" + this.statusMessageBlack;
	}

}
