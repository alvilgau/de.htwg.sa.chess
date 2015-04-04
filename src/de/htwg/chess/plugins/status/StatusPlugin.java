package de.htwg.chess.plugins.status;

import javax.swing.JComponent;

import de.htwg.chess.controller.IChessController;
import de.htwg.util.observer.IObserver;

/**
 * @author arkostka
 * @since 2015-04-03
 *
 */
public interface StatusPlugin extends IObserver {

	/**
	 * Start point of every plugin. This method will be executed when a plugin
	 * will be enabled.
	 * 
	 * @param controller
	 *            - the chess controller
	 */
	void start(IChessController controller);

	/**
	 * End point of every plugin. This method will be executed when a plugin
	 * will be disabled.
	 */
	void stop();

	/**
	 * @return the name of the plugin
	 */
	String getName();

	/**
	 * @return a component that will be shown up in the GUI when this plugin
	 *         will be enabled
	 */
	JComponent getComponent();

}