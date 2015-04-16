package de.htwg.chess.persistence.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import de.htwg.chess.model.IChessGame;
import de.htwg.chess.persistence.IChessDao;
import de.htwg.chess.persistence.hibernate.util.HibernateUtil;
import de.htwg.chess.persistence.hibernate.util.transform.ChessGameToPersistenceObjectUtil;
import de.htwg.chess.persistence.hibernate.util.transform.PersistenceObjectToChessGameUtil;

public class ChessHibernateDao implements IChessDao {

	private Session session;

	public ChessHibernateDao() {
		this.session = HibernateUtil.getSessionFactory().openSession();
	}

	@Override
	public void saveGame(IChessGame chessGame) {
		this.session.beginTransaction();
		PersistenceChessGame persistenceChessGame = ChessGameToPersistenceObjectUtil
				.transform(chessGame);
		this.session.save(persistenceChessGame);
		this.session.getTransaction().commit();
	}

	@Override
	@SuppressWarnings("unchecked")
	public IChessGame getGame(String id) {
		this.session.beginTransaction();
		Query query = this.session.createQuery("FROM PersistenceChessGame WHERE id = :id");
		query.setParameter("id", id);
		List<PersistenceChessGame> games = query.list();
		this.session.getTransaction().commit();

		if (games.size() > 0) {
			return PersistenceObjectToChessGameUtil.transform(games.get(0));
		}
		return null;
	}

	@Override
	public boolean containsGame(String id) {
		return getGame(id) != null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean deleteGame(String id) {
		this.session.beginTransaction();
		Criteria criteria = this.session.createCriteria(PersistenceChessGame.class);
		criteria.add(Restrictions.eq("id", id));
		List<PersistenceChessGame> games = criteria.list();
		this.session.getTransaction().commit();

		if (games.size() < 1) {
			return false;
		}

		PersistenceChessGame game = games.get(0);
		this.session.beginTransaction();
		this.session.delete(game);
		this.session.getTransaction().commit();
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<IChessGame> getAllGames() {
		this.session.beginTransaction();
		Criteria criteria = this.session.createCriteria(PersistenceChessGame.class);
		List<PersistenceChessGame> games = criteria.list();
		this.session.getTransaction().commit();

		List<IChessGame> chessGames = new ArrayList<IChessGame>();
		for (PersistenceChessGame game : games) {
			chessGames.add(PersistenceObjectToChessGameUtil.transform(game));
		}
		return chessGames;
	}
}
