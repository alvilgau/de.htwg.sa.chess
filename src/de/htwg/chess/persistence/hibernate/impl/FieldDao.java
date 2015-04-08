package de.htwg.chess.persistence.hibernate.impl;

import de.htwg.chess.model.IField;
import de.htwg.chess.persistence.hibernate.IFieldDao;

public class FieldDao implements IFieldDao {

	@Override
	public long store(IField object) {
		return 0;
	}

	@Override
	public IField find(long id) {
		return null;

	}

	@Override
	public boolean delete(IField object) {
		return false;
	}

	@Override
	public IField update(IField object) {
		return null;
	}

}
