package de.htwg.chess;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;

import de.htwg.chess.controller.IChessController;
import de.htwg.chess.model.IFieldFactory;
import de.htwg.chess.model.IFigureFacotry;
import de.htwg.chess.persistence.IChessDao;
import de.htwg.chess.plugins.StatusPlugin;

public class ChessModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IChessController.class).to(de.htwg.chess.controller.impl.ChessController.class).in(
				Singleton.class);
		bind(IFieldFactory.class).to(de.htwg.chess.model.impl.FieldFactory.class);
		bind(IFigureFacotry.class).to(de.htwg.chess.model.impl.FigureFactory.class);
		bind(IChessDao.class).to(de.htwg.chess.persistence.db4o.ChessDb4oDao.class);
		//bind(IChessDao.class).to(de.htwg.chess.persistence.hibernate.ChessHibernateDao.class);

		Multibinder<StatusPlugin> plugins = Multibinder.newSetBinder(binder(), StatusPlugin.class);
		plugins.addBinding().to(de.htwg.chess.plugins.SelectedFigureStatus.class);
		plugins.addBinding().to(de.htwg.chess.plugins.TeamTurnsStatus.class);
	}

}
