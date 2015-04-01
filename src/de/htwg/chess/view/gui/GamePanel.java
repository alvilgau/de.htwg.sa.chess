package de.htwg.chess.view.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import de.htwg.chess.controller.IChessController;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final String COLS = "ABCDEFGH";
	private static final Color LIGHT = Color.decode("#E5CEA4");
	private static final Color DARK = Color.decode("#A4785B");
	private static final Color LIGHT_BLUE = Color.decode("#B8CFE5");

	private JButton[][] chessField = new JButton[8][8];
	private IChessController controller;

	/**
	 * Creates a GamePanel
	 * 
	 * @param controller
	 *            - Chess Controller
	 */
	public GamePanel(final IChessController controller) {
		this.controller = controller;
		setLayout(new GridLayout(0, 9));

		// create the single fields
		for (int column = 0; column < this.chessField.length; column++) {
			for (int row = 0; row < this.chessField.length; row++) {
				JButton field = new JButton();
				field.setMargin(new Insets(0, 0, 0, 0));
				field.setActionCommand(row + ";" + column);

				field.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String[] pos = e.getActionCommand().split(";");
						int x = Integer.parseInt(pos[0]);
						int y = Integer.parseInt(pos[1]);
						controller.handleMovement(x, y);
					}
				});

				this.chessField[row][column] = field;
			}
		}

		// setting field style
		configureFields();

		// add column descriptions
		this.add(new JLabel(""));
		for (int i = 0; i < this.chessField.length; i++) {
			this.add(new JLabel(COLS.substring(i, i + 1), SwingConstants.CENTER));
		}

		// add fields
		for (int column = this.chessField.length - 1; column >= 0; column--) {
			for (int row = 0; row < this.chessField.length; row++) {
				switch (row) {
				case 0:
					this.add(new JLabel("" + (column + 1),
							SwingConstants.CENTER));
				default:
					this.add(this.chessField[row][column]);
				}
			}
		}
	}

	/**
	 * Setting field style e.g. background color
	 */
	private void configureFields() {
		for (int column = 0; column < this.chessField.length; column++) {
			for (int row = 0; row < this.chessField.length; row++) {
				// setting background color
				if ((column + row) % 2 == 1) {
					this.chessField[row][column].setBackground(DARK);
				} else {
					this.chessField[row][column].setBackground(LIGHT);
				}

				// setting figure images
				String imageName = this.controller.getFieldValue(row, column);
				if (imageName.equals("empty")) {
					this.chessField[row][column].setIcon(null);
				} else {
					this.chessField[row][column].setIcon(new ImageIcon("res/"
							+ imageName + ".png"));
				}
			}
		}
	}

	/**
	 * Refreshes the game panel
	 */
	public void refresh() {
		configureFields();
		if (this.controller.isSelect()) {
			int x = this.controller.getSelectedFigure().getxPos();
			int y = this.controller.getSelectedFigure().getyPos();
			this.chessField[x][y].setBackground(LIGHT_BLUE);
			for (int[] possMove : this.controller.getPossibleMoves()) {
				this.chessField[possMove[0]][possMove[1]]
						.setBackground(LIGHT_BLUE);
			}
		}
	}
}
