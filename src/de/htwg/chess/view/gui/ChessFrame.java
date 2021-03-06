package de.htwg.chess.view.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.google.inject.Inject;

import de.htwg.chess.controller.IChessController;
import de.htwg.chess.plugins.StatusPlugin;
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
	public ChessFrame(final IChessController controller, Set<StatusPlugin> plugins) {
		this.controller = controller;
		controller.addObserver(this);

		JMenuBar menuBar;
		JMenu gameMenu;
		JMenuItem newMenuItem, saveMenuItem, loadMenuItem, deleteMenuItem, quitMenuItem;

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

		// add save menu item
		saveMenuItem = new JMenuItem("Save Game");
		saveMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChessFrame.this.infoPane.handleSaveGame(ChessFrame.this);
			}
		});
		gameMenu.add(saveMenuItem);

		// add load menu item
		loadMenuItem = new JMenuItem("Load Game");
		loadMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String idOfSelectedGame = ChessFrame.this.infoPane.handleLoadGame(ChessFrame.this,
						controller.getDao().getAllGames(), "Load");
				if (idOfSelectedGame != null) {
					controller.loadFromDB(idOfSelectedGame);
				}
			}

		});
		gameMenu.add(loadMenuItem);
		
		// add delete menu item
		deleteMenuItem = new JMenuItem("Delete Game");
		deleteMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String idOfSelectedGame = ChessFrame.this.infoPane.handleLoadGame(ChessFrame.this,
						controller.getDao().getAllGames(), "Delete");
				if (idOfSelectedGame != null) {
					controller.deleteFromDB(idOfSelectedGame);
				}
			}
			
		});
		gameMenu.add(deleteMenuItem);

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
		 * Add status plugins
		 */
		if (plugins.size() > 0) {
			JMenu pluginMenu = new JMenu("Status Plugins");
			for (final StatusPlugin plugin : plugins) {
				final JCheckBoxMenuItem checkBox = new JCheckBoxMenuItem(plugin.getName());
				checkBox.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (checkBox.isSelected()) {
							controller.addObserver(plugin);
							plugin.start(controller);
							ChessFrame.this.statusPanel.add(plugin.getComponent());
						} else {
							controller.removeObserver(plugin);
							ChessFrame.this.statusPanel.remove(plugin.getComponent());
							plugin.stop();
						}
						ChessFrame.this.statusPanel.revalidate();
						ChessFrame.this.statusPanel.updateUI();
					}
				});
				pluginMenu.add(checkBox);
			}
			menuBar.add(pluginMenu);
		}

		/**
		 * Add components to window
		 */
		setJMenuBar(menuBar);

		/**
		 * Add status and plugin panel and the game panel to frame
		 */
		add(this.statusPanel, BorderLayout.NORTH);
		add(this.gamePanel, BorderLayout.CENTER);

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
		this.statusPanel = new StatusPanel();
		this.statusPanel.setStatusText(this.controller.getStatusMessage(),
				this.controller.getCheckmateMessage());
		this.statusPanel.setTurnText(this.controller.getTurnMessage());
		this.gamePanel = new GamePanel(this.controller);
		this.infoPane = new InfoPane(this.controller);
	}

	@Override
	public void update(Event e) {
		this.statusPanel.setStatusText(this.controller.getStatusMessage(),
				this.controller.getCheckmateMessage());
		this.statusPanel.setTurnText(this.controller.getTurnMessage());
		this.gamePanel.refresh();

		if (this.controller.isGameover()) {
			this.infoPane.showGameOver(this);
		} else if (this.controller.getExchange()) {
			this.infoPane.handleExchange(this);
		}
	}

}
