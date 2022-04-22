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

	/**
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 *************************************************************/

	@Override
	public List<Article> readAllArticles() throws SQLException, ClassNotFoundException {
		List<Article> articles = new ArrayList<Article>();
		// charger le driver
		loadDatabase();

		//
		Connection connection = DriverManager.getConnection(url, login, password);
		if (connection == null)
			throw new SQLException("La connection à la bdd n'a pas pu être établie");
		Statement statement = connection.createStatement();
		if (statement == null)
			throw new SQLException("La requête n'a pas pu être créee");
		ResultSet resultSet = statement.executeQuery("SELECT *FROM t_articles");
		if (resultSet == null)
			throw new SQLException("La requête n'a pas pu être exécutée");

		while (resultSet.next()) {
			int rsId = resultSet.getInt(1);
			String rsDescription = resultSet.getString(2);
			String rsBrand = resultSet.getString(3);
			double rsPrice = resultSet.getDouble(4);
			// int rsIdCategory = resultSet.getInt(5);
			articles.add(new Article(rsId, rsDescription, rsBrand, rsPrice));
		}
		return articles;
	}

	@Override
	public void createArticle(Article article) throws ClassNotFoundException, SQLException {
		// Chargement du driver
		loadDatabase();

		Connection connection = DriverManager.getConnection(url, login, password);
		if (connection == null)
			throw new SQLException("La connection à la bdd n'a pas pu être établie");

		PreparedStatement preparedStatement = connection
				.prepareStatement("INSERT INTO t_articles(description, brand, unitaryPrice) VALUES(?,?,?)");
		if (preparedStatement == null)
			throw new SQLException("La requête n'a pas pu être préparée");

		preparedStatement.setString(1, article.getDescription());
		preparedStatement.setString(2, article.getBrand());
		preparedStatement.setDouble(3, article.getPrice());

		if (preparedStatement.executeUpdate() == 1)
			System.out.println("Article bien inseré");

	}

	@Override
	public boolean updateArticle(Article article) throws ClassNotFoundException, SQLException {
		// charger le driver
		boolean status = false;
		loadDatabase();
		Connection connection = DriverManager.getConnection(url, login, password);
		if (connection == null)
			throw new SQLException("La connection à la bdd n'a pas pu être établie");

		PreparedStatement preparedStatement = connection.prepareStatement(
				"update t_articles set description = ?, brand = ?, unitaryprice = ? where idarticle = ?;");
		if (preparedStatement == null)
			throw new SQLException("La requête n'a pas pu être préparée");

		preparedStatement.setString(1, article.getDescription());
		preparedStatement.setString(2, article.getBrand());
		preparedStatement.setDouble(3, article.getPrice());
		// preparedStatement.setInt(set, article.getIdCategory());
		preparedStatement.setInt(4, article.getId());
		if (preparedStatement.executeUpdate() == 1)
			status = true;

		return status;
	}

	@Override
	public boolean deleteArticle(int idArticle) throws ClassNotFoundException, SQLException {
		boolean status = false;
		// charger le driver
		loadDatabase();

		Connection connection = DriverManager.getConnection(url, login, password);
		if (connection == null)
			throw new SQLException("La connection à la bdd n'a pas pu être établie");

		PreparedStatement preparedStatement = connection
				.prepareStatement("DELETE FROM t_articles WHERE idarticle = ?;");
		if (preparedStatement == null)
			throw new SQLException("La requête n'a pas pu être préparée");

		preparedStatement.setInt(1, idArticle);
		if (preparedStatement.executeUpdate() == 1)
			status = true;

		return status;
	}

	@Override
	public Article getArticleById(int idArticle) throws ClassNotFoundException, SQLException {
		Article article = new Article(0, null, null, 0);
		// charger le driver
		loadDatabase();

		Connection connection = DriverManager.getConnection(url, login, password);
		
		if (connection == null)
			throw new SQLException("La connection à la bdd n'a pas pu être établie");
		
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM t_articles WHERE idarticle = ?;");
		if (statement == null)
			throw new SQLException("La requête n'a pas pu être préparée");
		statement.setInt(1, idArticle);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			article.setId(resultSet.getInt("idArticle"));
			article.setDescription(resultSet.getString("description"));
			article.setBrand(resultSet.getString("brand"));
			article.setPrice(resultSet.getDouble("unitaryPrice"));
			// article.setIdCategory(resultSet.getInt("idCategory"));
		}else throw new SQLException("La requête n'a pas pu être exécutée");

		return article;

	}

	// factory
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
	 * @throws ClassNotFoundException
	 * 
	 */
	public void loadDatabase() throws ClassNotFoundException  {
		Class.forName(mariaDbDriver);
	}

}
