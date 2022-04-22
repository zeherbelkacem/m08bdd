package fr.fms.job;

import java.util.List;
import java.util.Map;

import fr.frm.entities.Article;

public interface ArticleJob {

	List<Article> getAllArticles();

	void addArticleToBucket(int idArticle);
	
	void removeArticleFromBucket(int idArticle);
	
	Map<Integer, Article> getMyBucket();

	List<Article> getArticlesByCategory(int idArticle);
}
