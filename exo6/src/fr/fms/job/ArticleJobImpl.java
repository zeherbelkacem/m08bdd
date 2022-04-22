package fr.fms.job;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import fr.frm.dao.ArticleDao;
import fr.frm.dao.Dao;
import fr.frm.dao.DaoFactory;
import fr.frm.entities.Article;

public class ArticleJobImpl implements ArticleJob {
	
	private  Map<Integer, Article> bucketMap = new HashMap<Integer, Article>();
	
	Dao<Article> dao = DaoFactory.getArticleDao();

	@Override
	public List<Article> getAllArticles() {
		return dao.readAllObjs();
	}

	@Override
	public void addArticleToBucket(int idArticle){
		//gestion du panier daans metier  
		
		/** verifier l'existanec de l'ARTICLE ID */
		Article article = dao.getObjById(idArticle);
		
		if (article != null) {
			if (bucketMap.containsKey(idArticle)) {// add the same product -> quantity incremented
				article.setQuantity(article.getQuantity()+1);
				bucketMap.put(article.getId(), article);
				
			} else  // new product in the bucket (first time)
				bucketMap.put(article.getId(), article);
		}
	}

	@Override
	public void removeArticleFromBucket(int idArticle) {
		if (bucketMap.get(idArticle).getQuantity() > 1)
			bucketMap.get(idArticle).setQuantity(bucketMap.get(idArticle).getQuantity() - 1);
		else
			bucketMap.remove(idArticle);

	}

	@Override
	public Map<Integer, Article> getMyBucket() {
		return bucketMap;
	}

	@Override
	public List<Article> getArticlesByCategory(int idArticle) {
		return dao.getArticlesByCategory(idArticle);
		
	}

	

}
