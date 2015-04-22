package de.htwg.chess.persistence;

public interface IPersistenceField {

	String getId();

	void setId(String id);

	Boolean getSet();

	void setSet(Boolean set);

	Integer getxPos();

	void setxPos(Integer xPos);

	Integer getyPos();

	void setyPos(Integer yPos);

	Integer getFigure();

	void setFigure(Integer figure);

	Integer getTeam();

	void setTeam(Integer team);

	IPersistenceChessGame getChessgame();

	void setChessgame(IPersistenceChessGame chessgame);
}
