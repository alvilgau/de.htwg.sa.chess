package de.htwg.chess.persistence.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import de.htwg.chess.model.IChessGame;
import de.htwg.chess.persistence.IChessDao;
import de.htwg.chess.persistence.hibernate.util.HibernateUtil;
import de.htwg.chess.persistence.hibernate.util.transform.ChessGameToPersistenceObjectUtil;

public class ChessHibernateDao implements IChessDao {

	private Session session;

	public ChessHibernateDao() {
		session = HibernateUtil.getSessionAnnotationFactory().getCurrentSession();
	}

	@Override
	public void saveGame(IChessGame chessGame) {
		session.beginTransaction();
		PersistenceChessGame persistenceChessGame = ChessGameToPersistenceObjectUtil
				.transform(chessGame);
		session.save(persistenceChessGame);
		session.getTransaction().commit();
	}

	@Override
	public IChessGame getGame(String id) {
		session.beginTransaction();
		Query query = session
				.createQuery("FROM PersistenceChessGame WHERE id = :id");
		query.setParameter("id", id);
		IChessGame chessGame = (IChessGame) query.list().get(0);
		session.getTransaction().commit();
		
		return chessGame;
	}

	@Override
	public boolean containsGame(String id) {
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
		session.beginTransaction();
		Query query = session.createQuery("FROM PersistenceChessGame");
		List<IChessGame> list = query.list();
		session.getTransaction().commit();
		return list;
	}
}
