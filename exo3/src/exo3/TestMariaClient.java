package exo3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestMariaClient {

	static String url = "jdbc:mariadb://localhost:3306/shop";
	static String login = "root";
	static String password = "fms2022";
	// requetes sql
	static String readSql = "SELECT *FROM t_articles";
	static String createSql = "INSERT INTO t_articles(description, brand, unitaryPrice) VALUES(?,?,?)";
	static String updateSql = "update t_articles set description = ?, brand = ?, unitaryprice = ? where idarticle = ?;";
	static String deleteSql = "DELETE FROM t_articles WHERE idarticle = ?;";
	static String getArticleByIdSql = "SELECT * FROM t_articles WHERE idarticle = ?;";
	static String joinSql = "SELECT idarticle, description, brand, unitaryprice, catname FROM t_articles INNER JOIN category   WHERE t_articles.idcategory = category.idcategory and idarticle = ?;";

	public static void main(String[] args) {

		/** CREATE **/
		Article article = new Article(0, "Casque", "New bee", 20);
		// createArticle(article);

		/** READ **/
		List<Article> articles = readAllArticles();
		for (Article a : articles)
			System.out.println(a.getId() + "- " + a.getDescription() + "  -  " + a.getBrand() + "  -  " + a.getPrice());
		/** UPDATE **/
		Article articleToUpdate = new Article(1, "souris ergo", "Logitoch", 127);
		updateArticle(articleToUpdate);

		/** DELETE **/
		deleteArticle(15);

		/** GET ARTICLE BY ID **/
		Article researchedArticle = getArticleById(1);
		System.out.println("\n"+researchedArticle.toString());

		/** MORE DETAILS ARTICLE BY ID **/
		Article researchedArticle2 = moreArticleDetails(1);
		System.out.println("\n"+researchedArticle2);

	}

	private static Article moreArticleDetails(int idArticle) {
		Article article = new Article(idArticle, deleteSql, createSql, idArticle);
		// TODO Auto-generated method stub
		return null;
	}

	private static Article getArticleById(int idArticle) {
		Article article = new Article(0, null, null, 0);
		// charger le driver
		loadDatabase();

		try {
			try (Connection connection = DriverManager.getConnection(url, login, password)) {
				try (PreparedStatement statement = connection.prepareStatement(getArticleByIdSql)) {
					statement.setInt(1, idArticle);
					try (ResultSet resultSet = statement.executeQuery()) {
						if (resultSet.next()) {
							article.setId(resultSet.getInt(idArticle));
							article.setDescription(resultSet.getString("description"));
							article.setBrand(resultSet.getString("brand"));
							article.setPrice(resultSet.getDouble("unitaryPrice"));
							// article.setIdCategory(resultSet.getInt("idCategory"));
						}
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return article;
	}

	/**
	 * 
	 * @param idArticle
	 */
	private static void deleteArticle(int idArticle) {
		// charger le driver
		loadDatabase();

		try {
			try (Connection connection = DriverManager.getConnection(url, login, password)) {
				try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
					preparedStatement.setInt(1, idArticle);
					if (preparedStatement.executeUpdate() == 1)
						System.out.println("\narticle supprim??");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 */
	private static void updateArticle(Article article) {
		// charger le driver
		loadDatabase();
		try {
			try (Connection connection = DriverManager.getConnection(url, login, password)) {
				try (PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
					preparedStatement.setString(1, article.getDescription());
					preparedStatement.setString(2, article.getBrand());
					preparedStatement.setDouble(3, article.getPrice());
					//preparedStatement.setInt(set, article.getIdCategory());
					preparedStatement.setInt(4, article.getId());
					if (preparedStatement.executeUpdate() == 1)
						System.out.println("\nArticle mis ?? jour");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param article
	 */
	private static void createArticle(Article article) {
		// Chargement du driver
		loadDatabase();

		try (Connection connection = DriverManager.getConnection(url, login, password)) {
			try (PreparedStatement preparedStatement = connection.prepareStatement(createSql)) {
				preparedStatement.setString(1, article.getDescription());
				preparedStatement.setString(2, article.getBrand());
				preparedStatement.setDouble(3, article.getPrice());
				// preparedStatement.setInt(4, article.getIdCategory());
				if (preparedStatement.executeUpdate() == 1)
					System.out.println("Article bien inser??");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 */
	private static void loadDatabase() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @return
	 */
	private static List<Article> readAllArticles() {
		List<Article> articles = new ArrayList<Article>();
		// charger le driver
		loadDatabase();

		//
		try (Connection connection = DriverManager.getConnection(url, login, password)) {
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(readSql)) {

					while (resultSet.next()) {
						int rsId = resultSet.getInt(1);
						String rsDescription = resultSet.getString(2);
						String rsBrand = resultSet.getString(3);
						double rsPrice = resultSet.getDouble(4);
						// int rsIdCategory = resultSet.getInt(5);
						articles.add(new Article(rsId, rsDescription, rsBrand, rsPrice));
					}

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articles;
	}

}
