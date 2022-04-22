package ArticlApp;

import java.sql.SQLException;
import java.util.List;

import fr.frm.dao.ArticleDao;
import fr.frm.dao.Dao;
import fr.frm.entities.Article;

public class TestArticle {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		
		try {
			Dao<Article> dao = new ArticleDao();
			/** CREATE **/
//			Article article = new Article(0, "Casque", "New bee", 20);
//			dao.createArticle(article);

			/** READ **/
			List<Article> articles = dao.readAllArticles();
			for (Article a : articles)
				System.out.println(
						a.getId() + "- " + a.getDescription() + "  -  " + a.getBrand() + "  -  " + a.getPrice());
			/** UPDATE **/
			Article articleToUpdate = new Article(1, "souris ergo", "Logitoch", 127);
			dao.updateArticle(articleToUpdate);

			/** DELETE **/
			dao.deleteArticle(15);

			/** GET ARTICLE BY ID **/
			Article researchedArticle = dao.getArticleById(100);
			System.out.println("\n" + researchedArticle.toString());

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

}
