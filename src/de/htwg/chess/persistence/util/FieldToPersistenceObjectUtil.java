package de.htwg.chess.persistence.util;

import java.util.ArrayList;
import java.util.List;

import de.htwg.chess.model.IField;
import de.htwg.chess.persistence.IPersistenceChessGame;
import de.htwg.chess.persistence.IPersistenceField;
import de.htwg.chess.persistence.hibernate.PersistenceField;

public class FieldToPersistenceObjectUtil {

	private FieldToPersistenceObjectUtil() {
	}

	public static List<IPersistenceField> transform(IField[][] fields,
			IPersistenceChessGame chessGame) {

		List<IPersistenceField> transformedFields = new ArrayList<>();

		for (int i = 0; i < fields.length; i++) {
			for (int j = 0; j < fields.length; j++) {
				transformedFields.add(transform(fields[i][j], chessGame));
			}
		}
		return transformedFields;
	}

	private static IPersistenceField transform(IField field, IPersistenceChessGame chessGame) {

		IPersistenceField persistenceField = new PersistenceField();
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

}