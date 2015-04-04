package de.htwg.chess.plugin.selectedfigure;

import javax.swing.JPanel;

import de.htwg.chess.model.IFigure;

/**
 * @author arkostka
 * @since 2015-04-03
 *
 */
public interface IDisplaySelectedFigurePlugin {
	
	/**
	 * Displays figure information in gui.
	 * 
	 * @param IFigure figure
	 * 
	 * @return void
	 */
	void displayFigure(IFigure figure);
	
	/**
	 * Creates JPanel for added plugins.
	 * 
	 * @return JPanel
	 */
	JPanel createPanel();
}