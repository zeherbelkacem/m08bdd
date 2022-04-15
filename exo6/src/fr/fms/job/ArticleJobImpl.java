package fr.fms.job;

import java.util.List;

import fr.frm.dao.ArticleDao;
import fr.frm.dao.Dao;
import fr.frm.entities.Article;

public class ArticleJobImpl implements ArticleJob{
	Dao<Article> dao = new ArticleDao();
	
	@Override
	public void getAllTraining() {
		List<Article> articles = dao.readAllObjs();
		
	}

	@Override
	public void addTraingToBucket() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTraingFromBucket() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showMyBucket() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateMyBucket() {
		// TODO Auto-generated method stub
		
	}

}
