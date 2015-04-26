package de.htwg.chess.persistence.couchdb;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.ViewQuery;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;

import de.htwg.chess.model.IChessGame;
import de.htwg.chess.persistence.IChessDao;
import de.htwg.chess.persistence.util.ChessGameToPersistenceObjectUtil;
import de.htwg.chess.persistence.util.PersistenceObjectToChessGameUtil;

public class ChessCouchDbDao implements IChessDao {

	private CouchDbConnector db = null;
	private Logger logger = Logger.getLogger("de.htwg.chess.persistence.couchdb");

	public ChessCouchDbDao() {
		HttpClient client = null;

		try {
			client = new StdHttpClient.Builder().url("http://lenny2.in.htwg-konstanz.de:5984")
					.build();

		} catch (MalformedURLException e) {
			this.logger.error("Malformed URL", e);
		}

		CouchDbInstance dbInstance = new StdCouchDbInstance(client);
		this.db = dbInstance.createConnector("chess_db_test", true);
		this.db.createDatabaseIfNotExists();
	}

	@Override
	public void saveGame(IChessGame game) {
		IPersistenceCouchDbChessGame persistenceChessGame = ChessGameToPersistenceObjectUtil.transform(game, IPersistenceCouchDbChessGame.class);
		db.create(persistenceChessGame);
	}

	@Override
	public IChessGame getGame(String id) {
		PersistenceChessGame chessGame = db.find(PersistenceChessGame.class, id);
		if(chessGame == null) {
			return null;
		}
		return PersistenceObjectToChessGameUtil.transform(chessGame);
	}
	
	private PersistenceChessGame getGameAsPersistenceObject(String id) {
		PersistenceChessGame chessGame = db.find(PersistenceChessGame.class, id);
		if(chessGame == null) {
			return null;
		}
		return chessGame;
	}

	@Override
	public boolean containsGame(String id) {
		return getGame(id) != null;
	}

	@Override
	public boolean deleteGame(String id) {
		PersistenceChessGame chessGame = getGameAsPersistenceObject(id);
		db.delete(chessGame);
		return true;
	}

	@Override
	public List<IChessGame> getAllGames() {
		ViewQuery query = new ViewQuery().allDocs().includeDocs(true);
		
		List<IChessGame> chessGames = new ArrayList<IChessGame>();
		for(PersistenceChessGame pChessGame: db.queryView(query, PersistenceChessGame.class)) {
			chessGames.add(PersistenceObjectToChessGameUtil.transform(pChessGame));
		}
		
		return chessGames;
	}

 }
