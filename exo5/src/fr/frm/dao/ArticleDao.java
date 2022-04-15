package fr.frm.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import fr.frm.entities.Article;

public class ArticleDao implements Dao<Article> {
	
	/***************************************************************/
	 Properties prop = readPropertiesFile("config.properties");
	 String mariaDbDriver = prop.getProperty("db.driver.class");
	 String url = prop.getProperty("db.url");
	 String login = prop.getProperty("db.login");
	 String password = prop.getProperty("db.password");
	/***************************************************************/

	@Override
	public List<Article> readAllArticles() {
		List<Article> articles = new ArrayList<Article>();
		// charger le driver
		loadDatabase();

		//
		try (Connection connection = DriverManager.getConnection(url, login, password)) {
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery("SELECT *FROM t_articles")) {

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

	@Override
	public void createArticle(Article article) {
		// Chargement du driver
				loadDatabase();

				try (Connection connection = DriverManager.getConnection(url, login, password)) {
					try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO t_articles(description, brand, unitaryPrice) VALUES(?,?,?)")) {
						preparedStatement.setString(1, article.getDescription());
						preparedStatement.setString(2, article.getBrand());
						preparedStatement.setDouble(3, article.getPrice());
						// preparedStatement.setInt(4, article.getIdCategory());
						if (preparedStatement.executeUpdate() == 1)
							System.out.println("Article bien inseré");
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}

		
	}

	@Override
	public void updateArticle(Article article) {
		// charger le driver
				loadDatabase();
				try {
					try (Connection connection = DriverManager.getConnection(url, login, password)) {
						try (PreparedStatement preparedStatement = connection.prepareStatement("update t_articles set description = ?, brand = ?, unitaryprice = ? where idarticle = ?;")) {
							preparedStatement.setString(1, article.getDescription());
							preparedStatement.setString(2, article.getBrand());
							preparedStatement.setDouble(3, article.getPrice());
							// preparedStatement.setInt(set, article.getIdCategory());
							preparedStatement.setInt(4, article.getId());
							if (preparedStatement.executeUpdate() == 1)
								System.out.println("\nArticle mis à jour");
						}
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
	}

	@Override
	public void deleteArticle(int idArticle) {
		// charger le driver
				loadDatabase();

				try {
					try (Connection connection = DriverManager.getConnection(url, login, password)) {
						try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM t_articles WHERE idarticle = ?;")) {
							preparedStatement.setInt(1, idArticle);
							if (preparedStatement.executeUpdate() == 1)
								System.out.println("\narticle supprimé");
						}
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}

	}

	@Override
	public Article getArticleById(int idArticle) {
		Article article = new Article(0, null, null, 0);
		// charger le driver
		loadDatabase();

		try {
			try (Connection connection = DriverManager.getConnection(url, login, password)) {
				try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM t_articles WHERE idarticle = ?;")) {
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
	
	//factory
	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public Properties readPropertiesFile(String fileName) {
		FileInputStream fis = null;
		Properties prop = null;
		try {
			fis = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return prop;
	}
	
	/**
	 * 
	 */
	public void loadDatabase() {

		try {
			Class.forName(mariaDbDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}


}
