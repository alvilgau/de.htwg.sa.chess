package de.htwg.chess.view.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.google.inject.Inject;

import de.htwg.chess.controller.IChessController;
import de.htwg.util.observer.Event;
import de.htwg.util.observer.IObserver;

public class ChessFrame extends JFrame implements IObserver {

	private static final long serialVersionUID = 1L;

	private IChessController controller;
	private GamePanel gamePanel;
	private StatusPanel statusPanel;
	private InfoPane infoPane;

	/**
	 * Creates a new GUI
	 * 
	 * @param controller
	 *            - Chess Controller
	 */
	@Inject
	public ChessFrame(final IChessController controller) {
		this.controller = controller;
		controller.addObserver(this);

		JMenuBar menuBar;
		JMenu gameMenu;
		JMenuItem newMenuItem, quitMenuItem;

		/**
		 * Game Menu
		 */
		menuBar = new JMenuBar();

		gameMenu = new JMenu("Game");

		newMenuItem = new JMenuItem("New Game");
		newMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.restart();
			}
		});
		gameMenu.add(newMenuItem);

		quitMenuItem = new JMenuItem("Quit");
		quitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		gameMenu.add(quitMenuItem);
		menuBar.add(gameMenu);

		constructPanels();

		/**
		 * Add components to window
		 */
		setJMenuBar(menuBar);
		add(statusPanel, BorderLayout.NORTH);
		add(gamePanel, BorderLayout.CENTER);

		/**
		 * Window settings
		 */
		setTitle("Chess");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	/**
	 * Constructs the Panels
	 */
	private void constructPanels() {
		statusPanel = new StatusPanel();
		statusPanel.setStatusText(controller.getStatusMessage(),
				controller.getCheckmateMessage());
		statusPanel.setTurnText(controller.getTurnMessage());
		gamePanel = new GamePanel(controller);
		infoPane = new InfoPane(controller);
	}

	@Override
	public void update(Event e) {
		statusPanel.setStatusText(controller.getStatusMessage(),
				controller.getCheckmateMessage());
		statusPanel.setTurnText(controller.getTurnMessage());
		gamePanel.refresh();

		if (controller.isGameover()) {
			infoPane.showGameOver(this);
		} else if (controller.getExchange()) {
			infoPane.handleExchange(this);
		}
	}

}
