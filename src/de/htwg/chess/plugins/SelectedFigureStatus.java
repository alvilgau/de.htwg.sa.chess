package de.htwg.chess.plugins;

import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JLabel;

import org.apache.log4j.Logger;

import de.htwg.chess.controller.IChessController;
import de.htwg.chess.model.IFigure;
import de.htwg.chess.plugins.StatusPlugin;
import de.htwg.util.observer.Event;

public class SelectedFigureStatus implements StatusPlugin {

	private static final String COLS = "ABCDEFGH";

	private Logger logger = Logger.getLogger(getClass());
	private JLabel component;
	private IChessController controller;

	public SelectedFigureStatus() {
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
		return "Display Selected Figure";
	}

	@Override
	public JComponent getComponent() {
		return this.component;
	}

	private String getData() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Selected Figure: ");
		if (this.controller.isSelect()) {
			IFigure figure = this.controller.getSelectedFigure();
			int xPos = figure.getxPos();
			int yPos = figure.getyPos() + 1;
			sb.append(figure.getTeam() + " ");
			sb.append(figure.getClass().getSimpleName());
			sb.append(" [" + COLS.charAt(xPos));
			sb.append(yPos + "]");
		} else {
			sb.append("No figure");
		}
		return sb.toString();
	}

	@Override
	public void update(Event e) {
		this.component.setText(getData());
	}
}
