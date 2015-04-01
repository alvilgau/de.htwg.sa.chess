package de.htwg.chess.view.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class StatusPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int FONT_SIZE = 16;
	private static final int GAP = 10;
	
	private JLabel status;
	private JLabel turn;

	/**
	 * Create a Status Panel
	 */
	public StatusPanel() {	
		Font font = new Font("Arial", Font.CENTER_BASELINE, FONT_SIZE);
		
		status = new JLabel("Status:");
		status.setFont(font);
		add(status);
		
		turn = new JLabel("Turn:");
		turn.setFont(font);
		add(turn);
		
		setLayout(new GridLayout(2, 1, GAP, GAP));
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));		
	}
	
	/**
	 * Sets the text for the status label
	 * @param st - current game status
	 * @param mateStatus - current checkmate status
	 */
	public void setStatusText(String st, String mateStatus) {
		status.setText(" Status: "+st+" "+mateStatus);
	}
	
	/**
	 * Sets the text for the turn label
	 * @param tu - current turn status
	 */
	public void setTurnText(String tu) {
		turn.setText(" Turn: "+tu);
	}
}
