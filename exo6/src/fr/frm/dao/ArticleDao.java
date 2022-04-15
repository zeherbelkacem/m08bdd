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

import fr.frm.config.ConfigFile;
import fr.frm.entities.Article;
import fr.frm.entities.Utilisateur;

public class ArticleDao implements Dao<Article> {

	@Override
	public List<Article> readAllObjs() {
		List<Article> articles = new ArrayList<Article>();
		// db connection
		//Connection connection = connect.connection();
		//
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

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articles;
	}

	@Override
	public void createObj(Article t) {
		// db connection
		//Connection connection = connect.connection();

		try (PreparedStatement preparedStatement = connection
				.prepareStatement("INSERT INTO t_articles(description, brand, unitaryPrice) VALUES(?,?,?)")) {
			preparedStatement.setString(1, t.getDescription());
			preparedStatement.setString(2, t.getBrand());
			preparedStatement.setDouble(3, t.getPrice());
			// preparedStatement.setInt(4, article.getIdCategory());
			if (preparedStatement.executeUpdate() == 1)
				System.out.println("Article bien inseré");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateObj(Article t) {
		// db connection
		//Connection connection = connect.connection();
		try {
			try (PreparedStatement preparedStatement = connection.prepareStatement(
					"update t_articles set description = ?, brand = ?, unitaryprice = ? where idarticle = ?;")) {
				preparedStatement.setString(1, t.getDescription());
				preparedStatement.setString(2, t.getBrand());
				preparedStatement.setDouble(3, t.getPrice());
				// preparedStatement.setInt(set, article.getIdCategory());
				preparedStatement.setInt(4, t.getId());
				if (preparedStatement.executeUpdate() == 1)
					System.out.println("\nArticle mis à jour");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteObj(int id) {
		// charger le driver
		// db connection
		//Connection connection = connect.connection();

		try {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM t_articles WHERE idarticle = ?;")) {
				preparedStatement.setInt(1, id);
				if (preparedStatement.executeUpdate() == 1)
					System.out.println("\nArticle supprimé");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Article getObjById(int id) {
		Article article = new Article(0, null, null, 0);
		// db connection
		//Connection connection = connect.connection();

		try {
			try (PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM t_articles WHERE idarticle = ?;")) {
				statement.setInt(1, id);
				try (ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next()) {
						article.setId(resultSet.getInt(id));
						article.setDescription(resultSet.getString("description"));
						article.setBrand(resultSet.getString("brand"));
						article.setPrice(resultSet.getDouble("unitaryPrice"));
						// article.setIdCategory(resultSet.getInt("idCategory"));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return article;

	}

	// factory

	/**
	 * 
	 */
	@Override
	public void loadDatabase() {
//		try {
//			Class.forName(mariaDbDriver);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
	}
}
