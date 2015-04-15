package de.htwg.chess.persistence.hibernate.util.transform;

import java.util.ArrayList;
import java.util.List;

import de.htwg.chess.controller.impl.ChessController;
import de.htwg.chess.model.FigureEnum;
import de.htwg.chess.model.IChessGame;
import de.htwg.chess.model.IField;
import de.htwg.chess.model.impl.Bishop;
import de.htwg.chess.model.impl.King;
import de.htwg.chess.model.impl.Knight;
import de.htwg.chess.model.impl.Pawn;
import de.htwg.chess.model.impl.Queen;
import de.htwg.chess.model.impl.Rook;
import de.htwg.chess.persistence.hibernate.PersistenceField;

public class FieldToPersistenceObjectUtil {

	public static PersistenceField transform(IField field, IChessGame chessGame) {

		if (field.getFigure() == null) {
			return null;
		}
		PersistenceField persistenceField = new PersistenceField();

		persistenceField.setTeam(field.getFigure().getTeamNumber());
		persistenceField.setxPos(field.getxPos());
		persistenceField.setyPos(field.getyPos());

		int figure = -1;

		if (field.getFigure() instanceof King) {
			figure = FigureEnum.King.ordinal();
		} else if (field.getFigure() instanceof Queen) {
			figure = FigureEnum.Queen.ordinal();
		} else if (field.getFigure() instanceof Bishop) {
			figure = FigureEnum.Bishop.ordinal();
		} else if (field.getFigure() instanceof Knight) {
			figure = FigureEnum.Knight.ordinal();
		} else if (field.getFigure() instanceof Rook) {
			figure = FigureEnum.Rook.ordinal();
		} else if (field.getFigure() instanceof Pawn) {
			figure = FigureEnum.Pawn.ordinal();
		}
		persistenceField.setFigure(figure);

		String id = chessGame.getId() + "-" + field.getxPos() + field.getyPos();
		persistenceField.setId(id);

		return persistenceField;
	}
	
	public static List<PersistenceField> transform(IField[][] fields, IChessGame chessGame) {
		
		List<PersistenceField> transformedFields = new ArrayList<>();
		
		for(int i = 0; i < ChessController.FIELD_SIZE; i++) {
			for(int j = 0; j < ChessController.FIELD_SIZE; j++) {
				transformedFields.add(transform(fields[i][j], chessGame));
			}
		}		
		return transformedFields;
		
	}
	
	
	
}