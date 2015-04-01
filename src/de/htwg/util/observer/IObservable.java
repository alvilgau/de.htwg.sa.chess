package de.htwg.util.observer;

public interface IObservable {

	/**
	 * Adds an observer to the set of observers for this object, 
	 * provided that it is not the same as some observer already in the set.
	 * @param s - new observer object
	 */
	 void addObserver(IObserver s);
	 
	/**
	 * Removes an Observer
	 * @param s - an existing observer object
	 */
	 void removeObserver(IObserver s);
	 
	/**
	 * Removes all existing observers
	 */
	 void removeAllObservers();
	 
	/**
	 * If this object has changed, then notify all of its observers
	 */
	 void notifyObservers();
	 
	/**
	 * If this object has changed, then notify all of its observers
	 * @param e - Event
	 */
	 void notifyObservers(Event e);
}
