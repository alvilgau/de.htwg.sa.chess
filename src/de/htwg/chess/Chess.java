package de.htwg.chess;

import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.chess.controller.IChessController;
import de.htwg.chess.view.gui.ChessFrame;
import de.htwg.chess.view.tui.TextUI;

public final class Chess {

	private static Scanner scanner;
	private static TextUI tui;
	private static ChessFrame gui;
	private static IChessController controller;
	private static Chess instance;
	
	private Chess(int dbtype) {
		// Set up logging through log4j
		PropertyConfigurator.configure("log4j.properties");

		Injector injector = Guice.createInjector(new ChessModule(dbtype));

		scanner = new Scanner(System.in);
		controller = injector.getInstance(IChessController.class);
		tui = injector.getInstance(TextUI.class);

		// Disable GUI for web deploy
		gui = injector.getInstance(ChessFrame.class);
	}

	public static Chess getInstance(String[] args) {
		if (instance == null) {
			instance = new Chess(extractDatabaseType(args));
		}
		return instance;
	}

	public IChessController getController() {
		return controller;
	}

	public TextUI getTui() {
		return tui;
	}

	public ChessFrame getGui() {
		return gui;
	}

	public static void main(String[] args) {
		getInstance(args);
		tui.printTUI();

		boolean run = true;
		while (run) {
			run = tui.processInputLine(scanner.next());
		}
	}
	
	private static int extractDatabaseType(String[] args) {
		if(args.length > 0) {
			return Integer.valueOf(args[0]);
		}
		else {
			return 0;
		}
	}

}
