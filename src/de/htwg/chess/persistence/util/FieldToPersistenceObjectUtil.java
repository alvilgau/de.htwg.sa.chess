package de.htwg.chess.persistence.util;

import java.util.ArrayList;
import java.util.List;

import de.htwg.chess.model.IField;
import de.htwg.chess.persistence.couchdb.IPersistenceCouchDbChessGame;
import de.htwg.chess.persistence.couchdb.IPersistenceCouchDbField;
import de.htwg.chess.persistence.hibernate.IPersistenceHibernateChessGame;
import de.htwg.chess.persistence.hibernate.IPersistenceHibernateField;

public final class FieldToPersistenceObjectUtil {

	private FieldToPersistenceObjectUtil() {
	}

	public static List<IPersistenceHibernateField> transform(IField[][] fields,
			IPersistenceHibernateChessGame chessGame) {

		List<IPersistenceHibernateField> transformedFields = new ArrayList<>();

		for (int i = 0; i < fields.length; i++) {
			for (int j = 0; j < fields.length; j++) {
				transformedFields.add(transform(fields[i][j], chessGame));
			}
		}
		return transformedFields;
	}
	
	public static List<IPersistenceCouchDbField> transform(IField[][] fields,
			IPersistenceCouchDbChessGame chessGame) {
		
		List<IPersistenceCouchDbField> transformedFields = new ArrayList<>();
		
		for (int i = 0; i < fields.length; i++) {
			for (int j = 0; j < fields.length; j++) {
				transformedFields.add(transform(fields[i][j], chessGame));
			}
		}
		return transformedFields;
	}

	private static IPersistenceHibernateField transform(IField field, IPersistenceHibernateChessGame chessGame) {

		IPersistenceHibernateField persistenceField = new de.htwg.chess.persistence.hibernate.PersistenceField();
		String id = chessGame.getId() + "-" + field.getxPos() + "-" + field.getyPos();
		persistenceField.setId(id);
		persistenceField.setSet(field.isSet());
		persistenceField.setxPos(field.getxPos());
		persistenceField.setyPos(field.getyPos());
		persistenceField.setChessgame(chessGame);
		if (field.getFigure() != null) {
			persistenceField.setTeam(field.getFigure().getTeamNumber());
			persistenceField.setFigure(field.getFigure().getFigureType().ordinal());
		} else {
			persistenceField.setTeam(null);
			persistenceField.setFigure(null);

		}

		return persistenceField;
	}
	
	private static IPersistenceCouchDbField transform(IField field, IPersistenceCouchDbChessGame chessGame) {
		
		IPersistenceCouchDbField persistenceField = new de.htwg.chess.persistence.couchdb.PersistenceField();
		String id = chessGame.getId() + "-" + field.getxPos() + "-" + field.getyPos();
		persistenceField.setId(id);
		persistenceField.setSet(field.isSet());
		persistenceField.setxPos(field.getxPos());
		persistenceField.setyPos(field.getyPos());
		if (field.getFigure() != null) {
			persistenceField.setTeam(field.getFigure().getTeamNumber());
			persistenceField.setFigure(field.getFigure().getFigureType().ordinal());
		} else {
			persistenceField.setTeam(null);
			persistenceField.setFigure(null);
			
		}
		
		return persistenceField;
	}

}