package de.htwg.chess.persistence.hibernate.util.transform;

import java.util.List;

import de.htwg.chess.controller.impl.ChessController;
import de.htwg.chess.model.FigureEnum;
import de.htwg.chess.model.IField;
import de.htwg.chess.model.IFigure.Team;
import de.htwg.chess.model.impl.Field;
import de.htwg.chess.model.impl.FigureFactory;
import de.htwg.chess.persistence.hibernate.PersistenceField;

public class PersitenceObjectToFieldUtil {

	public static IField transform(PersistenceField persistenceField) {
		
		int x = persistenceField.getxPos();
		int y = persistenceField.getyPos();
		
		Team team = Team.values()[persistenceField.getTeam()];
		
		IField field = new Field(x, y);
		field.setSet(persistenceField.getSet());
		
		FigureFactory figureFactory = new FigureFactory();
		FigureEnum figureEnum = FigureEnum.values()[persistenceField.getFigure()];
		
		switch(figureEnum) {
			case King:
				field.setFigure(figureFactory.createKing(x, y, team));
				break;
			case Queen:
				field.setFigure(figureFactory.createQueen(x, y, team));
				break;
			case Bishop:
				field.setFigure(figureFactory.createBishop(x, y, team));
				break;
			case Knight:
				field.setFigure(figureFactory.createKnight(x, y, team));
				break;
			case Rook:
				field.setFigure(figureFactory.createRook(x, y, team));
				break;
			case Pawn:
				field.setFigure(figureFactory.createPawn(x, y, team, y));
				break;
		}
		
		return field;
	}
	
	public static IField[][] transform(List<PersistenceField> persistenceFields) {
		
		IField[][] fields = new IField[ChessController.FIELD_SIZE][ChessController.FIELD_SIZE];
		
		for(PersistenceField persistenceField : persistenceFields) {
			fields[persistenceField.getxPos()][persistenceField.getyPos()] = transform(persistenceField);
		}
		return fields;
	}
}
