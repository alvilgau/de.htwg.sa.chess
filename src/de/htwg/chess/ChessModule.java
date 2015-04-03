package de.htwg.chess;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;

import de.htwg.chess.controller.IChessController;
import de.htwg.chess.model.IFieldFactory;
import de.htwg.chess.model.IFigureFacotry;
import de.htwg.chess.plugin.selectedfigure.IDisplaySelectedFigurePlugin;
import de.htwg.sa.chess.plugins.GraphicalDisplaySelectedFigure;
import de.htwg.sa.chess.plugins.TextualDisplaySelectedFigure;

public class ChessModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IChessController.class).to(de.htwg.chess.controller.impl.ChessController.class).in(Singleton.class);
		bind(IFieldFactory.class).to(de.htwg.chess.model.impl.FieldFactory.class);
		bind(IFigureFacotry.class).to(de.htwg.chess.model.impl.FigureFactory.class);
		
		Multibinder<IDisplaySelectedFigurePlugin> plugins = Multibinder.newSetBinder(binder(), IDisplaySelectedFigurePlugin.class);
		plugins.addBinding().to(GraphicalDisplaySelectedFigure.class);
		plugins.addBinding().to(TextualDisplaySelectedFigure.class);
	}
	
}
