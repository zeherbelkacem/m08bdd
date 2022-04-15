package fr.frm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.frm.entities.Utilisateur;

public class UserDao implements Dao<Utilisateur> {

	@Override
	public List<Utilisateur> readAllObjs() {
		List<Utilisateur> users = new ArrayList<Utilisateur>();
		// db connection
	//	Connection connection = connect.connection();

		// loadDatabase();

		// try (Connection connection = DriverManager.getConnection(url, login,
		// password)) {
		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery("SELECT *FROM t_users")) {

				while (resultSet.next()) {
					int rsId = resultSet.getInt(1);
					String rsLogin = resultSet.getString(2);
					String rsPassword = resultSet.getString(3);
					users.add(new Utilisateur(rsId, rsLogin, rsPassword));
				}

			}
			// }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public void createObj(Utilisateur t) {
		// db connection
		//Connection connection = connect.connection();

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
	public void updateObj(Utilisateur t) {
		// db connection
		//Connection connection = connect.connection();
		try {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement("update t_users set login = ?, password = ? where idUser = ?;")) {
				preparedStatement.setString(1, t.getLogin());
				preparedStatement.setString(2, t.getPassword());
				preparedStatement.setInt(3, t.getId());
				if (preparedStatement.executeUpdate() == 1)
					System.out.println("\nUser mis à jour");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteObj(int id) {
		// db connection
		//Connection connection = connect.connection();

		try {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM t_users WHERE idUser = ?;")) {
				preparedStatement.setInt(1, id);
				if (preparedStatement.executeUpdate() == 1)
					System.out.println("\nUtilisateur supprimé");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Utilisateur getObjById(int id) {
		Utilisateur user = new Utilisateur(0, null, null);
		// db connection
		//Connection connection = connect.connection();

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
