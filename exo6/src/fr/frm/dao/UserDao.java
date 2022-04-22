package fr.frm.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.frm.entities.Article;
import fr.frm.entities.Utilisateur;

public class UserDao implements Dao<Utilisateur> {

	@Override
	public List<Utilisateur> readAllObjs() {
		List<Utilisateur> users = new ArrayList<Utilisateur>();
	
		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery("SELECT *FROM t_users")) {

				while (resultSet.next()) {
					int rsId = resultSet.getInt(1);
					String rsLogin = resultSet.getString(2);
					String rsPassword = resultSet.getString(3);
					users.add(new Utilisateur(rsId, rsLogin, rsPassword));
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public void createObj(Utilisateur t) {
		if (t== null) throw new NullPointerException("L'objet user est null");

		try (PreparedStatement preparedStatement = connection
				.prepareStatement("INSERT INTO t_users(login, password) VALUES(?,?)")) {
			preparedStatement.setString(1, t.getLogin());
			preparedStatement.setString(2, t.getPassword());
			if (preparedStatement.executeUpdate() == 1)
				System.out.println("Utilisateur bien inseré");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean updateObj(Utilisateur t) {
		boolean status = false;
		try {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement("update t_users set login = ?, password = ? where idUser = ?;")) {
				preparedStatement.setString(1, t.getLogin());
				preparedStatement.setString(2, t.getPassword());
				preparedStatement.setInt(3, t.getId());
				if (preparedStatement.executeUpdate() == 1)
					status = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean deleteObj(int id) {
		// db connection
		// Connection connection = connect.connection();
		boolean status  = false;
		try {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM t_users WHERE idUser = ?;")) {
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
	public Utilisateur getObjById(int id) {
		Utilisateur user = new Utilisateur(0, null, null);
		// db connection
		// Connection connection = connect.connection();

		try {
			try (PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM t_articles WHERE idUser = ?;")) {
				statement.setInt(1, id);
				try (ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next()) {
						user.setId(resultSet.getInt(id));
						user.setLogin("login");
						user.setPassword("password");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<Article> getArticlesByCategory(int idCategory) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getUserId(String name) {
		 	int rsUserId = 0;
			try (PreparedStatement statement = connection
					.prepareStatement("SELECT *FROM t_users WHERE login LIKE ?;")) {

				statement.setString(1, name);
				try (ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next()) 
						rsUserId = resultSet.getInt("idUser");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		return rsUserId;
	}

	@Override
	public int getLastInsertedId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String[]> getInvoice(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

}
