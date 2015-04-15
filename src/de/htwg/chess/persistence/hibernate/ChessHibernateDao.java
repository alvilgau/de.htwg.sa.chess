package de.htwg.chess.persistence.hibernate;

import java.util.List;

import org.hibernate.Session;

import de.htwg.chess.model.IChessGame;
import de.htwg.chess.model.impl.ChessGame;
import de.htwg.chess.persistence.IChessDao;
import de.htwg.chess.persistence.hibernate.util.HibernateUtil;

public class ChessHibernateDao implements IChessDao {
	
	private Session session;
	
	public ChessHibernateDao() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}

	@Override
	public void saveGame(IChessGame game) {
		session.beginTransaction();
		session.save(game);
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
