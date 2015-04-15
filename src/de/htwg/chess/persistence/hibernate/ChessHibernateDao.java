package de.htwg.chess.persistence.hibernate;

import java.util.List;

import org.hibernate.Session;

import de.htwg.chess.model.IChessGame;
import de.htwg.chess.model.impl.ChessGame;
import de.htwg.chess.persistence.IChessDao;
import de.htwg.chess.persistence.hibernate.util.HibernateUtil;
import de.htwg.chess.persistence.hibernate.util.transform.ChessGameToPersistenceObjectUtil;

public class ChessHibernateDao implements IChessDao {
	
	private Session session;
	
	public ChessHibernateDao() {
		session = HibernateUtil.getSessionJavaConfigFactory().getCurrentSession();
	}

	@Override
	public void saveGame(IChessGame chessGame) {
		PersistenceChessGame persistenceChessGame = ChessGameToPersistenceObjectUtil.transform(chessGame);
		
		session.beginTransaction();
		session.save(persistenceChessGame);
		session.getTransaction().commit();
	}

	@Override
	public ChessGame getGame(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsGame(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteGame(String id) {
		session.beginTransaction();
		session.delete(getGame(id));
		session.getTransaction().commit();
		return true;
	}

	@Override
	public List<IChessGame> getAllGames() {
		// TODO Auto-generated method stub
		return null;
	}

}
