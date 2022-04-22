package fr.frm.dao;

import fr.frm.entities.Article;
import fr.frm.entities.Category;
import fr.frm.entities.Order;
import fr.frm.entities.OrderLine;
import fr.frm.entities.Utilisateur;

public class DaoFactory {

	public static Dao<Article> getArticleDao() {
		return new ArticleDao();
	}

	public static Dao<Utilisateur> getUserDao() {
		return new UserDao();
	}
	
	public static Dao<Category> getCategoryDao() {
		return new CategoryDao();
	}
	
	public static Dao<Order> getOrderDao() {
		return new OrderDao();
	}
	
	public static Dao<OrderLine> getOrderLineDao() {
		return new OrderLineDao();
	}

}
