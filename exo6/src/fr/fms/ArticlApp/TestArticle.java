package fr.fms.ArticlApp;

import java.util.List;

import fr.fms.security.ValidateLogin;
import fr.frm.dao.ArticleDao;
import fr.frm.dao.Dao;
import fr.frm.dao.UserDao;
import fr.frm.entities.Article;
import fr.frm.entities.Utilisateur;

public class TestArticle {

	public static void main(String[] args) {
		
		Dao<Article> dao = new ArticleDao();
		Dao<Utilisateur> daoUser = new UserDao();
		ValidateLogin validateLogin = new ValidateLogin();
		
		Boolean status = false;
		Utilisateur utilisateur1 = new Utilisateur(0, "belkacem@localhost", "belkacem");
		status = validateLogin.validateObjLogin(utilisateur1);
		
		if (status) {
			/** CREATE **/
			//daoUser.createObj(new Utilisateur(0, "jccharles@localhost", "jceancharles"));
			// createArticle(new Article(0, "Casque", "New bee", 20));
			
		List<Utilisateur> users =	daoUser.readAllObjs();
//			for (Utilisateur u : users)
//				System.out.println(u.getId() + "- " + u.getLogin() + "  -  " + u.getPassword());
			/** READ **/
			List<Article> articles = dao.readAllObjs();
			for (Article a : articles)
				System.out.println(a.getId() + "- " + a.getDescription() + "  -  " + a.getBrand() + "  -  " + a.getPrice());
			/** UPDATE **/
			Article articleToUpdate = new Article(1, "souris ergo", "Logitoch", 127);
			dao.updateObj(articleToUpdate);

			/** DELETE **/
			dao.deleteObj(15);

			/** GET ARTICLE BY ID **/
			Article researchedArticle = dao.getObjById(1);
			System.out.println("\n" + researchedArticle.toString());

		}else System.out.println("Vous n'êtes pas autorisé");
		
	}
	

}
