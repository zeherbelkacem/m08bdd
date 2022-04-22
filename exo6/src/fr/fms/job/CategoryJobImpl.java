package fr.fms.job;

import java.util.List;

import fr.frm.dao.Dao;
import fr.frm.dao.DaoFactory;
import fr.frm.entities.Category;

public class CategoryJobImpl implements CategoryJob {
	private Dao<Category> categoryDao = DaoFactory.getCategoryDao();
	@Override
	public List<Category> getAllCategories() {
		return categoryDao.readAllObjs();
	}

}
