package de.htwg.chess.persistence;


public interface IPersistenceField {
	
	public String getId();

	public void setId(String id);

	public Boolean getSet();

	public void setSet(Boolean set);

	public Integer getxPos();

	public void setxPos(Integer xPos);

	public Integer getyPos();

	public void setyPos(Integer yPos);

	public Integer getFigure();

	public void setFigure(Integer figure);

	public Integer getTeam();

	public void setTeam(Integer team);

	public IPersistenceChessGame getChessgame();

	public void setChessgame(IPersistenceChessGame chessgame);
}
