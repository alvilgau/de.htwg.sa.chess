package de.htwg.chess.plugins;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.htwg.chess.model.IFigure;
import de.htwg.chess.plugin.selectedfigure.IDisplaySelectedFigurePlugin;

/**
 * @author arkostka
 * @since 2015-04-03
 *
 */
public class GraphicalDisplaySelectedFigure implements
		IDisplaySelectedFigurePlugin {

	private String figurePosition;

	public void displayFigure(IFigure figure) {
		figurePosition = figure.getFigureName();
	}
	
	public JPanel createPanel() {
		return new GrahpicalPanel();
	}

	private class GrahpicalPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private static final int FONT_SIZE = 16;
		private static final int GAP = 10;

		private static final int WIDTH = 150;
		private static final int HEIGHT = 30;

		private JLabel picture;
		private JLabel position;

		/**
		 * Create a Status Panel
		 */
		public GrahpicalPanel() {
			Font font = new Font("Arial", Font.CENTER_BASELINE, FONT_SIZE);
			Dimension dimension = new Dimension(WIDTH, HEIGHT);

			position = new JLabel("");
			position.setFont(font);
			position.setMinimumSize(dimension);
			position.setPreferredSize(dimension);
			position.setMaximumSize(dimension);
			add(position);

			setLayout(new GridLayout(2, 1, GAP, GAP));
			setBackground(Color.WHITE);
			setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		}

		/**
		 * Sets the text for the status label
		 * 
		 * @param positionText
		 *            - current game status
		 */
		public void setPosition(String positionText) {
			position.setText(" Status: " + positionText);
		}
	}
}
