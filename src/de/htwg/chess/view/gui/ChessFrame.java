package de.htwg.chess.view.gui;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.google.inject.Inject;

import de.htwg.chess.controller.IChessController;
import de.htwg.chess.plugin.selectedfigure.IDisplaySelectedFigurePlugin;
import de.htwg.util.observer.Event;
import de.htwg.util.observer.IObserver;

public class ChessFrame extends JFrame implements IObserver {

	private static final long serialVersionUID = 1L;

	private IChessController controller;
	private GamePanel gamePanel;
	private StatusPanel statusPanel;
	private InfoPane infoPane;

	private List<JPanel> iDisplaySelectedFigurePlugins = new ArrayList<JPanel>();

	/**
	 * Creates a new GUI
	 * 
	 * @param controller
	 *            - Chess Controller
	 */
	@Inject
	public ChessFrame(final IChessController controller,
			Set<IDisplaySelectedFigurePlugin> plugins) {
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

		/**
		 * Create nested layout. Therefore we got a BorderLayout with nested
		 * BorderLayout and GridBagLayout for plugins.
		 */
		JPanel statusAndPluginPanel = new JPanel();
		statusAndPluginPanel.setLayout(new BorderLayout());
		statusAndPluginPanel.add(statusPanel, BorderLayout.NORTH);

		Iterator<IDisplaySelectedFigurePlugin> iter = plugins.iterator();

		/**
		 * Add plugins to GridBagLayout
		 */
		if (iter.hasNext()) {
			JPanel pluginPanel = new JPanel();
			pluginPanel.setLayout(new GridBagLayout());

			while (iter.hasNext()) {
				final IDisplaySelectedFigurePlugin plugin = iter.next();
				JPanel panel = plugin.createPanel();
				iDisplaySelectedFigurePlugins.add(panel);
				pluginPanel.add(panel);
			}
			statusAndPluginPanel.add(pluginPanel, BorderLayout.CENTER);
		}
		
		/**
		 * Add status and plugin panel and the game panel to frame
		 */
		add(statusAndPluginPanel, BorderLayout.NORTH);
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
