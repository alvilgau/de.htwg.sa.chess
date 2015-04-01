package de.htwg.chess.model.impl;

import de.htwg.chess.model.IFigure;
import de.htwg.chess.model.IFigureFacotry;
import de.htwg.chess.model.IFigure.Team;

public class FigureFactory implements IFigureFacotry {

	@Override
	public IFigure createBishop(int x, int y, Team team) {
		return new Bishop(x, y, team);
	}

	@Override
	public IFigure createKing(int x, int y, Team team) {
		return new King(x, y, team);
	}

	@Override
	public IFigure createKnight(int x, int y, Team team) {
		return new Knight(x, y, team);
	}

	@Override
	public IFigure createPawn(int x, int y, Team team, int startPosY) {
		return new Pawn(x, y, team, startPosY);
	}

	@Override
	public IFigure createQueen(int x, int y, Team team) {
		return new Queen(x, y, team);
	}

	@Override
	public IFigure createRook(int x, int y, Team team) {
		return new Rook(x, y, team);
	}

}
