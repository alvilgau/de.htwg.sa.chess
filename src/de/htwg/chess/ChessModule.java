package de.htwg.chess;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

import de.htwg.chess.controller.IChessController;
import de.htwg.chess.model.IFieldFactory;
import de.htwg.chess.model.IFigureFacotry;
import de.htwg.chess.persistence.IGenericDao;
import de.htwg.chess.persistence.hibernate.IFieldDao;
import de.htwg.chess.persistence.hibernate.IGameDao;
import de.htwg.chess.persistence.hibernate.impl.FieldDao;
import de.htwg.chess.persistence.hibernate.impl.GameDao;
import de.htwg.chess.plugins.SelectedFigureStatus;
import de.htwg.chess.plugins.StatusPlugin;
import de.htwg.chess.plugins.TeamTurnsStatus;

public class ChessModule extends AbstractModule {
	
	public enum DatabaseType {
		db4o, hibernate, couchdb
	}
	
	private int dbtype;
	
	public ChessModule(int dbtype) {
		super();
		this.dbtype = dbtype;
	}
	
	public ChessModule() {
		super();
	}
	
	@Override
	protected void configure() {
		bind(IChessController.class).to(de.htwg.chess.controller.impl.ChessController.class).in(
				Singleton.class);
		bind(IFieldFactory.class).to(de.htwg.chess.model.impl.FieldFactory.class);
		bind(IFigureFacotry.class).to(de.htwg.chess.model.impl.FigureFactory.class);
		
		configureDatabase();

		Multibinder<StatusPlugin> plugins = Multibinder.newSetBinder(binder(), StatusPlugin.class);
		plugins.addBinding().to(SelectedFigureStatus.class);
		plugins.addBinding().to(TeamTurnsStatus.class);
	}
	
	private void configureDatabase() {
		DatabaseType dbtype = DatabaseType.values()[this.dbtype];
		
		switch(dbtype) {
			case hibernate:
				//settings for hibernate db
				bind(new TypeLiteral<IGenericDao<IGameDao>>() {}).to((Class<? extends IGenericDao<IGameDao>>) GameDao.class);
				bind(new TypeLiteral<IGenericDao<IFieldDao>>() {}).to((Class<? extends IGenericDao<IFieldDao>>) FieldDao.class);
				break;
				
			case couchdb:
				System.out.println("couchdb");
				break;
				
			case db4o:
			default:
				System.out.println("db4o");
				break;
		}
	}
}
