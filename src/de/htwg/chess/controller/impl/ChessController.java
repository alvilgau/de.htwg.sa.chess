package de.htwg.chess.controller.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.inject.Inject;

import de.htwg.chess.controller.IChessController;
import de.htwg.chess.model.IChessGame;
import de.htwg.chess.model.IField;
import de.htwg.chess.model.IFieldFactory;
import de.htwg.chess.model.IFigure;
import de.htwg.chess.model.IFigure.Team;
import de.htwg.chess.model.IFigureFacotry;
import de.htwg.chess.model.impl.ChessGame;
import de.htwg.chess.persistence.IChessDao;
import de.htwg.util.observer.Observable;

public class ChessController extends Observable implements IChessController {

	public static final int ZERO = 0;
	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int FOUR = 4;
	private static final int FIVE = 5;
	private static final int SIX = 6;
	public static final int SEVEN = 7;
	public static final int FIELD_SIZE = 8;
	private static final int LIST_SIZE = 16;

	// Factories to create figures and fields
	private IFieldFactory fieldFactory;
	private IFigureFacotry figureFacotry;

	// This lists contains all figures of a team
	private List<IFigure> figuresTeamWhite;
	private List<IFigure> figuresTeamBlack;

	// List that contains the possible moves of the current selected figure
	private List<IField> possibleMoves;

	// Checkmate checks if a king is mate or not
	private Checkmate checkmate;

	// The playground
	private IField fields[][];

	// The figure for the actually move
	private IFigure moveFigure;

	// Attribute to check a figure is selected
	private boolean select;

	// Attribute for the exchange of pawn
	private boolean exchange;

	// Attribute to check the game is over or not
	private boolean gameover;

	// Attribute to select the right team for the move
	private int turn;

	private String statusMessage;
	private String turnMessage;

	// count the amount of turns a player has made
	private int turnsWhite;
	private int turnsBlack;

	private IChessDao dao;

	/**
	 * Constructs a new Chess Controller
	 */
	@Inject
	public ChessController(IFieldFactory fieldFactory,
			IFigureFacotry figureFacotry, IChessDao dao) {
		this.fieldFactory = fieldFactory;
		this.figureFacotry = figureFacotry;
		this.dao = dao;
		this.fields = new IField[FIELD_SIZE][FIELD_SIZE];
		this.checkmate = new Checkmate();

		initNewGame();
	}

	@Override
	public void restart() {
		initNewGame();

		this.checkmate.reset();
		notifyObservers();
	}

	private void initNewGame() {
		this.possibleMoves = new ArrayList<>();
		this.moveFigure = null;
		this.select = false;
		this.exchange = false;
		this.gameover = false;

		this.turn = 0;
		this.turnsWhite = 0;
		this.turnsBlack = 0;

		this.statusMessage = "Welcome to Chess";
		this.turnMessage = "Team " + Team.white.name() + "'s turn";

		initTeamWhite();
		initTeamBlack();
		initFieldsRest();
		initFigureList();
	}

	/**
	 * Initialize fields for player white
	 */
	private void initTeamWhite() {
		this.fields[ZERO][ZERO] = this.fieldFactory.createField(true,
				this.figureFacotry.createRook(ZERO, ZERO, Team.white));

		this.fields[ONE][ZERO] = this.fieldFactory.createField(true,
				this.figureFacotry.createKnight(ONE, ZERO, Team.white));

		this.fields[TWO][ZERO] = this.fieldFactory.createField(true,
				this.figureFacotry.createBishop(TWO, ZERO, Team.white));

		this.fields[THREE][ZERO] = this.fieldFactory.createField(true,
				this.figureFacotry.createQueen(THREE, ZERO, Team.white));

		this.fields[FOUR][ZERO] = this.fieldFactory.createField(true,
				this.figureFacotry.createKing(FOUR, ZERO, Team.white));

		this.fields[FIVE][ZERO] = this.fieldFactory.createField(true,
				this.figureFacotry.createBishop(FIVE, ZERO, Team.white));

		this.fields[SIX][ZERO] = this.fieldFactory.createField(true,
				this.figureFacotry.createKnight(SIX, ZERO, Team.white));

		this.fields[SEVEN][ZERO] = this.fieldFactory.createField(true,
				this.figureFacotry.createRook(SEVEN, ZERO, Team.white));

		for (int i = 0; i <= SEVEN; i++) {
			this.fields[i][ONE] = this.fieldFactory.createField(true,
					this.figureFacotry.createPawn(i, ONE, Team.white, ONE));
		}
	}

	/**
	 * Initialize fields for player black
	 */
	private void initTeamBlack() {
		this.fields[ZERO][SEVEN] = this.fieldFactory.createField(true,
				this.figureFacotry.createRook(ZERO, SEVEN, Team.black));

		this.fields[ONE][SEVEN] = this.fieldFactory.createField(true,
				this.figureFacotry.createKnight(ONE, SEVEN, Team.black));

		this.fields[TWO][SEVEN] = this.fieldFactory.createField(true,
				this.figureFacotry.createBishop(TWO, SEVEN, Team.black));

		this.fields[THREE][SEVEN] = this.fieldFactory.createField(true,
				this.figureFacotry.createQueen(THREE, SEVEN, Team.black));

		this.fields[FOUR][SEVEN] = this.fieldFactory.createField(true,
				this.figureFacotry.createKing(FOUR, SEVEN, Team.black));

		this.fields[FIVE][SEVEN] = this.fieldFactory.createField(true,
				this.figureFacotry.createBishop(FIVE, SEVEN, Team.black));

		this.fields[SIX][SEVEN] = this.fieldFactory.createField(true,
				this.figureFacotry.createKnight(SIX, SEVEN, Team.black));

		this.fields[SEVEN][SEVEN] = this.fieldFactory.createField(true,
				this.figureFacotry.createRook(SEVEN, SEVEN, Team.black));

		for (int i = 0; i <= SEVEN; i++) {
			this.fields[i][SIX] = this.fieldFactory.createField(true,
					this.figureFacotry.createPawn(i, SIX, Team.black, SIX));
		}
	}

	/**
	 * Initialize the figure lists with the fields
	 */
	private void initFigureList() {
		this.figuresTeamWhite = new ArrayList<IFigure>(LIST_SIZE);
		this.figuresTeamBlack = new ArrayList<IFigure>(LIST_SIZE);
		for (int i = 0; i < FIELD_SIZE; i++) {
			for (int j = 0; j < FIELD_SIZE; j++) {
				IFigure figure = this.fields[i][j].getFigure();
				if (figure != null) {
					if (figure.getTeamNumber() == Team.white.ordinal()) {
						this.figuresTeamWhite.add(figure);
					} else {
						this.figuresTeamBlack.add(figure);
					}
				}
			}
		}
	}

	/**
	 * Initialize the remaining fields
	 */
	private void initFieldsRest() {
		for (int i = 2; i <= FIVE; i++) {
			for (int k = 0; k <= SEVEN; k++) {
				this.fields[k][i] = this.fieldFactory.createEmptyField(k, i);
			}
		}
	}

	/**
	 * Selects the team for the next move
	 */
	private void nextTurn() {
		if (this.turn == 0) {
			this.turn = 1;
			this.turnMessage = "Team black's turn";
		} else {
			this.turn = 0;
			this.turnMessage = "Team white's turn";
		}
	}

	/**
	 * Increments amount of total turns for a team.
	 */
	private void incrementTurnsForTeam() {
		if (this.moveFigure.getTeam().equals(Team.white.name())) {
			this.turnsWhite++;
		} else {
			this.turnsBlack++;
		}
	}

	/**
	 * Updates the checkmate states of the kings
	 */
	private void updateCheckmate() {
		this.checkmate.update(this.figuresTeamWhite, this.figuresTeamBlack,
				this.fields);

		if (this.checkmate.isCheckWhite() && this.turn == 1) {
			this.checkmate.nextStateWhite();
		} else if (this.checkmate.isCheckBlack() && this.turn == 0) {
			this.checkmate.nextStateBlack();
		}

		this.gameover = this.checkmate.isMateBlack()
				|| this.checkmate.isMateWhite();
	}

	/**
	 * Sets the exchange status
	 * 
	 * @param exchange
	 *            - new exchange status
	 */
	public void setExchange(boolean exchange) {
		this.exchange = exchange;
	}

	@Override
	public boolean getExchange() {
		return this.exchange;
	}

	@Override
	public String getStatusMessage() {
		return this.statusMessage;
	}

	@Override
	public String getTurnMessage() {
		return this.turnMessage;
	}

	@Override
	public String getCheckmateMessage() {
		return this.checkmate.getStatusMessage();
	}

	@Override
	public boolean isGameover() {
		return this.gameover;
	}

	@Override
	public boolean isSelect() {
		return this.select;
	}

	@Override
	public boolean isWhiteTurn() {
		return this.turn == 0;
	}

	@Override
	public int getFieldSize() {
		return FIELD_SIZE;
	}

	@Override
	public IFigure getSelectedFigure() {
		return this.moveFigure;
	}

	@Override
	public IField[][] getFields() {
		return this.fields;
	}

	@Override
	public Checkmate getCheckmate() {
		return this.checkmate;
	}

	@Override
	public int getTurnsWhite() {
		return this.turnsWhite;
	}

	@Override
	public int getTurnsBlack() {
		return this.turnsBlack;
	}

	@Override
	public final IChessDao getDao() {
		return this.dao;
	}

	@Override
	public String getFieldValue(int x, int y) {
		IFigure fig = this.fields[x][y].getFigure();
		if (fig != null) {
			return fig.getTeam() + fig;
		} else {
			return "empty";
		}
	}

	@Override
	public int[][] getPossibleMoves() {
		int possMoves[][] = new int[this.possibleMoves.size()][2];
		for (int i = 0; i < this.possibleMoves.size(); i++) {
			possMoves[i][0] = this.possibleMoves.get(i).getxPos();
			possMoves[i][1] = this.possibleMoves.get(i).getyPos();
		}

		return possMoves;
	}

	/**
	 * Sets the game over status
	 * 
	 * @param gameover
	 *            - new game over status
	 */
	public void setGameover(boolean gameover) {
		this.gameover = gameover;
	}

	@Override
	public void handleMovement(int x, int y) {
		if (this.gameover || this.exchange) {
			return;
		}

		if (!this.select) {
			select(x, y);
		} else {
			move(x, y);
		}
	}

	@Override
	public void select(int x, int y) {
		if (this.fields[x][y].getFigure() != null
				&& this.turn == this.fields[x][y].getFigure().getTeamNumber()) {
			this.moveFigure = this.fields[x][y].getFigure();
			this.possibleMoves = this.moveFigure.getPossibleMoves(this.fields);
			if (!this.possibleMoves.isEmpty()) {
				this.select = true;
				this.statusMessage = "One Figure is selected.";
				notifyObservers();
			}
		} else {
			this.statusMessage = "No Figure is selected.";
			this.select = false;
			notifyObservers();
		}
	}

	@Override
	public void move(int x, int y) {
		if (this.select && this.possibleMoves.contains(this.fields[x][y])) {
			this.statusMessage = "Figure was moved successfully.";
			int oldPosX = this.moveFigure.getxPos();
			int oldPosY = this.moveFigure.getyPos();

			/* Removes the killed figure from the list */
			this.figuresTeamBlack.remove(this.fields[x][y].getFigure());
			this.figuresTeamWhite.remove(this.fields[x][y].getFigure());

			/* Moves the figure */
			this.fields[x][y].setFigure(this.moveFigure);
			this.fields[x][y].setSet(true);
			this.fields[oldPosX][oldPosY].clear();
			this.exchange = this.moveFigure.move(x, y);

			incrementTurnsForTeam();
			nextTurn();

			/* Checks for checkmate and game over */
			updateCheckmate();
		} else {
			this.statusMessage = "Move failed. No Figure is selected.";
		}

		this.select = false;
		notifyObservers();
	}

	@Override
	public void exchangeKnight() {
		int xPos = this.moveFigure.getxPos();
		int yPos = this.moveFigure.getyPos();
		Team team = Team.valueOf(this.moveFigure.getTeam());

		if (team == Team.black) {
			this.figuresTeamBlack.remove(this.moveFigure);
			this.figuresTeamBlack.add(this.figureFacotry.createKnight(xPos,
					yPos, team));
			this.fields[xPos][yPos].setFigure(this.figuresTeamBlack
					.get(this.figuresTeamBlack.size() - 1));
		} else {
			this.figuresTeamWhite.remove(this.moveFigure);
			this.figuresTeamWhite.add(this.figureFacotry.createKnight(xPos,
					yPos, team));
			this.fields[xPos][yPos].setFigure(this.figuresTeamWhite
					.get(this.figuresTeamWhite.size() - 1));
		}

		this.exchange = false;
		updateCheckmate();
		notifyObservers();
	}

	@Override
	public void exchangeBishop() {
		int xPos = this.moveFigure.getxPos();
		int yPos = this.moveFigure.getyPos();
		Team team = Team.valueOf(this.moveFigure.getTeam());

		if (team == Team.black) {
			this.figuresTeamBlack.remove(this.moveFigure);
			this.figuresTeamBlack.add(this.figureFacotry.createBishop(xPos,
					yPos, team));
			this.fields[xPos][yPos].setFigure(this.figuresTeamBlack
					.get(this.figuresTeamBlack.size() - 1));
		} else {
			this.figuresTeamWhite.remove(this.moveFigure);
			this.figuresTeamWhite.add(this.figureFacotry.createBishop(xPos,
					yPos, team));
			this.fields[xPos][yPos].setFigure(this.figuresTeamWhite
					.get(this.figuresTeamWhite.size() - 1));
		}

		this.exchange = false;
		updateCheckmate();
		notifyObservers();
	}

	@Override
	public void exchangeRook() {
		int xPos = this.moveFigure.getxPos();
		int yPos = this.moveFigure.getyPos();
		Team team = Team.valueOf(this.moveFigure.getTeam());

		if (team == Team.black) {
			this.figuresTeamBlack.remove(this.moveFigure);
			this.figuresTeamBlack.add(this.figureFacotry.createRook(xPos, yPos,
					team));
			this.fields[xPos][yPos].setFigure(this.figuresTeamBlack
					.get(this.figuresTeamBlack.size() - 1));
		} else {
			this.figuresTeamWhite.remove(this.moveFigure);
			this.figuresTeamWhite.add(this.figureFacotry.createRook(xPos, yPos,
					team));
			this.fields[xPos][yPos].setFigure(this.figuresTeamWhite
					.get(this.figuresTeamWhite.size() - 1));
		}

		this.exchange = false;
		updateCheckmate();
		notifyObservers();
	}

	@Override
	public void exchangeQueen() {
		int xPos = this.moveFigure.getxPos();
		int yPos = this.moveFigure.getyPos();
		Team team = Team.valueOf(this.moveFigure.getTeam());

		if (team == Team.black) {
			this.figuresTeamBlack.remove(this.moveFigure);
			this.figuresTeamBlack.add(this.figureFacotry.createQueen(xPos,
					yPos, team));
			this.fields[xPos][yPos].setFigure(this.figuresTeamBlack
					.get(this.figuresTeamBlack.size() - 1));
		} else {
			this.figuresTeamWhite.remove(this.moveFigure);
			this.figuresTeamWhite.add(this.figureFacotry.createQueen(xPos,
					yPos, team));
			this.fields[xPos][yPos].setFigure(this.figuresTeamWhite
					.get(this.figuresTeamWhite.size() - 1));
		}

		this.exchange = false;
		updateCheckmate();
		notifyObservers();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String toJson() {
		JSONObject obj = new JSONObject();
		JSONArray possMoves = new JSONArray();
		obj.put("statusMessage", getStatusMessage());
		obj.put("checkmateMessage", getCheckmateMessage());
		obj.put("turnMessage", getTurnMessage());
		obj.put("select", isSelect());
		obj.put("exchange", getExchange());
		obj.put("gameover", isGameover());
		for (int[] move : getPossibleMoves()) {
			JSONArray arr = new JSONArray();
			arr.add(move[0]);
			arr.add(move[1]);
			possMoves.add(arr);
		}
		obj.put("possMoves", possMoves);
		return obj.toJSONString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getGameBoardAsJson() {
		JSONArray gameBoard = new JSONArray();
		for (int y = 0; y < FIELD_SIZE; y++) {
			for (int x = 0; x < FIELD_SIZE; x++) {
				JSONObject field = new JSONObject();
				field.put("figure", getFieldValue(x, y));
				gameBoard.add(field);
			}
		}
		return gameBoard.toJSONString();
	}

	@Override
	public void saveToDB(String gameName) {
		if (this.exchange || this.gameover) {
			return;
		}

		IChessGame game = new ChessGame();
		game.setName(gameName);
		game.setSaveDate(new Date());
		game.setTurn(this.turn);
		game.setTurnsBlack(this.turnsBlack);
		game.setTurnsWhite(this.turnsWhite);
		game.setFields(this.fields);
		this.dao.saveGame(game);
	}

	@Override
	public void loadFromDB(String id) {
		IChessGame chessGame = this.dao.getGame(id);

		this.fields = chessGame.getFields();
		this.possibleMoves.clear();
		this.checkmate.reset();
		this.moveFigure = null;
		this.select = false;

		this.turn = chessGame.getTurn();
		this.turnsWhite = chessGame.getTurnsWhite();
		this.turnsBlack = chessGame.getTurnsBlack();

		String dateTimeString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(chessGame.getSaveDate());
		this.statusMessage = "Loaded game " + chessGame.getName() + " - "
				+ dateTimeString;
		String playerColorTurn = (chessGame.getTurn() == Team.white.ordinal()) ? Team.white
				.name() : Team.black.name();
		this.turnMessage = "Team " + playerColorTurn + "'s turn";

		initFigureList();
		updateCheckmate();
		notifyObservers();
	}
	
	@Override
	public void deleteFromDB(String id) {
		IChessGame chessGame = this.dao.getGame(id);
		this.dao.deleteGame(chessGame.getId());
	}
}
