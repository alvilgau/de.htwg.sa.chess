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
public class TextualDisplaySelectedFigure implements
		IDisplaySelectedFigurePlugin {

	private String figureName;
	private String figurePosition;

	public JPanel createPanel() {
		return new TextualPanel();
	}

	public void displayFigure(IFigure figure) {
		figureName = figure.getFigureName();
		figurePosition = figure.getStringPositionInformation();
	}

	private class TextualPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private static final int FONT_SIZE = 16;
		private static final int GAP = 10;

		private static final int WIDTH = 150;
		private static final int HEIGHT = 30;

		private JLabel selection;
		private JLabel position;

		/**
		 * Create a Status Panel
		 */
		public TextualPanel() {
			Font font = new Font("Arial", Font.CENTER_BASELINE, FONT_SIZE);
			Dimension dimension = new Dimension(WIDTH, HEIGHT);

			selection = new JLabel("Selected figure: ");
			selection.setFont(font);
			selection.setFont(font);
			selection.setMinimumSize(dimension);
			selection.setPreferredSize(dimension);
			selection.setMaximumSize(dimension);
			add(selection);

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
		 * Sets the text for the position label
		 * 
		 * @param positionText
		 *            - current position
		 */
		public void setPosition(String positionText) {
			position.setText(" Position: " + positionText);
		}
	}
}
