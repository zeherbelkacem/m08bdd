package fr.frm.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.frm.entities.Article;

public class ArticleDao implements Dao<Article> {

	/**
	 * 
	 */
	@Override
	public List<Article> readAllObjs() {
		List<Article> articles = new ArrayList<Article>();
		// db connection
		// Connection connection = connect.connection();
		//
		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery("SELECT *FROM t_articles")) {

				while (resultSet.next()) {
					int rsId = resultSet.getInt(1);
					String rsDescription = resultSet.getString(2);
					String rsBrand = resultSet.getString(3);
					double rsPrice = resultSet.getDouble(4);
//					int rsIdCategory = resultSet.getInt(5);
					int rsQuantity = resultSet.getInt(6);
					articles.add(new Article(rsId, rsDescription, rsBrand, rsPrice, rsQuantity));
				}
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return articles;
	}

	/**
	 * 
	 */
	@Override
	public void createObj(Article t) {
		// db connection
		// Connection connection = connect.connection();

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

	/**
	 * 
	 */
	@Override
	public boolean updateObj(Article t) {
		boolean status = false;
		// db connection
		// Connection connection = connect.connection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"update t_articles set description = ?, brand = ?, unitaryprice = ? where idarticle = ?;")) {
			preparedStatement.setString(1, t.getDescription());
			preparedStatement.setString(2, t.getBrand());
			preparedStatement.setDouble(3, t.getPrice());
			// preparedStatement.setInt(set, article.getIdCategory());
			preparedStatement.setInt(4, t.getId());
			if (preparedStatement.executeUpdate() == 1)
				status = true;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return status;
	}

	/**
	 * 
	 */
	@Override
	public boolean deleteObj(int id) {
		// charger le driver
		// db connection
		// Connection connection = connect.connection();
		boolean status = false;
		try {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM t_articles WHERE idarticle = ?;")) {
				preparedStatement.setInt(1, id);
				if (preparedStatement.executeUpdate() == 1)
					status = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Article getObjById(int id) {
		Article article = new Article(id, null, null, id, id);
		// db connection
		// Connection connection = connect.connection();

		try (PreparedStatement statement = connection
				.prepareStatement("SELECT * FROM t_articles WHERE idarticle = ?;")) {

			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					article.setId(resultSet.getInt("idarticle"));
					article.setDescription(resultSet.getString("description"));
					article.setBrand(resultSet.getString("brand"));
					article.setPrice(resultSet.getDouble("unitaryPrice"));
					article.setQuantity(resultSet.getInt("quantity"));
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return article;
	}

	/**
	 * 
	 */
	@Override
	public List<Article> getArticlesByCategory(int idCatgory) {
		List<Article> articles = new ArrayList<Article>();
		try {
			try (PreparedStatement statement = connection.prepareStatement(
					" SELECT idarticle, t_articles.description, brand, unitaryprice FROM t_articles INNER JOIN category   WHERE t_articles.idcategory = category.idcategory and category.idCategory = ?;")) {
				statement.setInt(1, idCatgory);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {

						articles.add(new Article(resultSet.getInt("idarticle"), resultSet.getString("description"),
								resultSet.getString("brand"), resultSet.getDouble("unitaryPrice")));
					}
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return articles;
	}

	@Override
	public int getLastInsertedId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getUserId(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String[]> getInvoice(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}
}
