package fr.frm.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
	List<T> readAllArticles() throws SQLException, ClassNotFoundException;
	void createArticle(T article) throws ClassNotFoundException, SQLException;
	boolean updateArticle(T article) throws ClassNotFoundException, SQLException;
	boolean deleteArticle(int idArticle) throws ClassNotFoundException, SQLException;
	T getArticleById(int idArticle) throws ClassNotFoundException, SQLException;
}
