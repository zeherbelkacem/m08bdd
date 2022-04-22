package fr.frm.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.frm.entities.Article;
import fr.frm.entities.Category;

public class CategoryDao implements Dao<Category> {

	public CategoryDao() {
	}

	@Override
	public List<Category> readAllObjs() {
		List<Category> categories = new ArrayList<Category>();
		
		try (Statement statement = connection.createStatement()){
			try (ResultSet resultSet = statement.executeQuery("SELECT *FROM category")) {

				while (resultSet.next()) {
					int rsId = resultSet.getInt(1);
					String rsName = resultSet.getString(2);
					String rsDescription = resultSet.getString(3);
					categories.add(new Category(rsId, rsName, rsDescription));
				}
		}
		}catch(

	Exception e)
	{
		System.out.println(e.getMessage());
	}

	return categories;

	}

	@Override
	public boolean deleteObj(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createObj(Category t) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean updateObj(Category t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Category getObjById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> getArticlesByCategory(int idCategory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLastInsertedId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getUserId(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String[]> getInvoice(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

}
