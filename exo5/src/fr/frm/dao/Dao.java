package fr.frm.dao;

import java.util.List;

public interface Dao<T> {
	List<T> readAllArticles();
	void createArticle(T article);
	void updateArticle(T article);
	void deleteArticle(int idArticle);
	T getArticleById(int idArticle);
}
