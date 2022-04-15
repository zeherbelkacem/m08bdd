package fr.fms.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.frm.dao.BddConnection;
import fr.frm.entities.Utilisateur;

public class ValidateLogin {
	
	//BddConnection connect = BddConnection.getConnection();
	Connection connection = BddConnection.getConnection();
	
	public boolean validateObjLogin(Utilisateur t) {
		boolean status = false;
		
		// db connection
		//Connection connection = connect.connection();
		try {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement("select * from t_users where login = ? and password = ? ")) {

				preparedStatement.setString(1, t.getLogin());
				preparedStatement.setString(2, t.getPassword());

				if (preparedStatement.executeQuery().next())
					status = true;
				else
					status = false;

//					ResultSet rs = preparedStatement.executeQuery();
//					status = rs.next();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;

	}
}
