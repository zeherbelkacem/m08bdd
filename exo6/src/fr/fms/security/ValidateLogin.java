package fr.fms.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.lang.model.element.NestingKind;

import fr.frm.dao.BddConnection;
import fr.frm.entities.Utilisateur;

public class  ValidateLogin {

	
	// BddConnection connect = BddConnection.getConnection();

	public boolean validateObjLogin(Utilisateur t) {
		boolean status = false;
		try {
			try (Connection connection = BddConnection.getConnection()) {
				try (PreparedStatement preparedStatement = connection
						.prepareStatement("select * from t_users where login = ? and password = ? ")) {
					preparedStatement.setString(1, t.getLogin());
					preparedStatement.setString(2, t.getPassword());
					
					if (preparedStatement.executeQuery().next())
						status = true;
					else {
						throw new SQLException("Utilisateur enixistant");
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return status;
		
	}
}
