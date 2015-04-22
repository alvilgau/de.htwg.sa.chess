package de.htwg.chess.persistence.util;

import java.util.Collection;

import de.htwg.chess.controller.impl.ChessController;
import de.htwg.chess.model.FigureType;
import de.htwg.chess.model.IField;
import de.htwg.chess.model.IFieldFactory;
import de.htwg.chess.model.IFigure;
import de.htwg.chess.model.IFigure.Team;
import de.htwg.chess.model.IFigureFacotry;
import de.htwg.chess.model.impl.FieldFactory;
import de.htwg.chess.model.impl.FigureFactory;
import de.htwg.chess.persistence.IPersistenceField;

public class PersistenceObjectToFieldUtil {

	private PersistenceObjectToFieldUtil() {
	}

	public static IField[][] transform(Collection<IPersistenceField> persistenceFields) {

		IField[][] fields = new IField[ChessController.FIELD_SIZE][ChessController.FIELD_SIZE];

		for (IPersistenceField persistenceField : persistenceFields) {
			fields[persistenceField.getxPos()][persistenceField.getyPos()] = transform(persistenceField);
		}
		return fields;
	}

	private static IField transform(IPersistenceField persistenceField) {

		IFigureFacotry figureFactory = new FigureFactory();
		IFieldFactory fieldFactory = new FieldFactory();
		int x = persistenceField.getxPos();
		int y = persistenceField.getyPos();

		if (persistenceField.getSet()) {
			IFigure figure = null;
			Team team = Team.values()[persistenceField.getTeam()];
			FigureType figureType = FigureType.values()[persistenceField.getFigure()];
			switch (figureType) {
			case King:
				figure = figureFactory.createKing(x, y, team);
				break;
			case Queen:
				figure = figureFactory.createQueen(x, y, team);
				break;
			case Bishop:
				figure = figureFactory.createBishop(x, y, team);
				break;
			case Knight:
				figure = figureFactory.createKnight(x, y, team);
				break;
			case Rook:
				figure = figureFactory.createRook(x, y, team);
				break;
			case Pawn:
				figure = figureFactory.createPawn(x, y, team, y);
				break;
			}
			return fieldFactory.createField(true, figure);
		} else {
			return fieldFactory.createEmptyField(x, y);
		}
	}

}
