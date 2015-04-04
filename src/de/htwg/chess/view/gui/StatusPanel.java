package de.htwg.chess.view.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int FONT_SIZE = 16;

	private JLabel status;
	private JLabel turn;

	/**
	 * Create a Status Panel
	 */
	public StatusPanel() {
		Font font = new Font("Arial", Font.CENTER_BASELINE, FONT_SIZE);

		this.status = new JLabel("Status:");
		this.status.setFont(font);
		add(this.status);

		this.turn = new JLabel("Turn:");
		this.turn.setFont(font);
		add(this.turn);

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	}

	/**
	 * Sets the text for the status label
	 * 
	 * @param st
	 *            - current game status
	 * @param mateStatus
	 *            - current checkmate status
	 */
	public void setStatusText(String st, String mateStatus) {
		this.status.setText(" Status: " + st + " " + mateStatus);
	}

	/**
	 * Sets the text for the turn label
	 * 
	 * @param tu
	 *            - current turn status
	 */
	public void setTurnText(String tu) {
		this.turn.setText(" Turn: " + tu);
	}
}
