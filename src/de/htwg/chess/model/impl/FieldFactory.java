package de.htwg.chess.model.impl;

import de.htwg.chess.model.IField;
import de.htwg.chess.model.IFieldFactory;
import de.htwg.chess.model.IFigure;

public class FieldFactory implements IFieldFactory {

	@Override
	public IField createEmptyField(int x, int y) {
		return new Field(x, y);
	}

	@Override
	public IField createField(boolean set, IFigure figure) {
		return new Field(set, figure);
	}

}
