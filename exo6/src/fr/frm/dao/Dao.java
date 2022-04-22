package fr.frm.dao;

import java.sql.Connection;
import java.util.List;

import fr.frm.entities.Article;

public interface Dao<T> {
	//public BddConnection bddConnection = BddConnection.getInstance();
	public Connection connection = BddConnection.getInstance().getConnection();
	List<T> readAllObjs();
	void createObj(T t);
	boolean updateObj(T t) ;
	boolean deleteObj(int id);
	T getObjById(int id);
	List<Article> getArticlesByCategory(int idCategory);
	int getUserId(String name);
	int getLastInsertedId();
	List<String[]> getInvoice(int orderId);
}
