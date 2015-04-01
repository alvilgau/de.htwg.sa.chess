package de.htwg.chess;

import com.google.inject.AbstractModule;

import de.htwg.chess.controller.IChessController;
import de.htwg.chess.model.IFieldFactory;
import de.htwg.chess.model.IFigureFacotry;

public class ChessModuleWeb extends AbstractModule {

	@Override
	protected void configure() {
		bind(IChessController.class).to(de.htwg.chess.controller.impl.ChessController.class);
		bind(IFieldFactory.class).to(de.htwg.chess.model.impl.FieldFactory.class);
		bind(IFigureFacotry.class).to(de.htwg.chess.model.impl.FigureFactory.class);
	}

}
