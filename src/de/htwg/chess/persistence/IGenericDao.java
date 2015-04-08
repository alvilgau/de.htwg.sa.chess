package de.htwg.chess.persistence;


/**
 * @since 2015-04-08
 *
 */
public interface IGenericDao<T> {
	
	long store(T object);
	
	T find(long id);
	
	boolean delete(T object);
	
	T update(T object);
}
