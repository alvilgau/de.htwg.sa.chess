package de.htwg.chess.plugins;

import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JLabel;

import org.apache.log4j.Logger;

import de.htwg.chess.controller.IChessController;
import de.htwg.util.observer.Event;

public class TeamTurnsStatus implements StatusPlugin {

	private Logger logger = Logger.getLogger(getClass());
	private JLabel component;
	private IChessController controller;
	
	public TeamTurnsStatus() {
		this.component = new JLabel();
		this.component.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
	}

	@Override
	public void start(IChessController controller) {
		this.logger.info("Enabled Plugin: " + getName());
		this.controller = controller;
		this.component.setText(getData());
	}

	@Override
	public void stop() {
		this.logger.info("Disabled Plugin: " + getName());	
	}

	@Override
	public String getName() {
		return "Display total turns for team";
	}

	@Override
	public JComponent getComponent() {
		return this.component;
	}
	
	private String getData() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Total turns white: " + this.controller.getTurnsWhite());
		sb.append(", \n");
		sb.append(" total turns black: " + this.controller.getTurnsBlack());
		return sb.toString();
	}
	
	@Override
	public void update(Event e) {
		this.component.setText(getData());
		
	}

}
