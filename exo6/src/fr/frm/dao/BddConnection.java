package fr.frm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import fr.frm.config.ConfigFile;

public class BddConnection {
	private static BddConnection bddConnection = new BddConnection();

	private BddConnection() {
	}
	
	public static BddConnection getInstance() {
		return bddConnection;
	}
	
	public static Connection getConnection() {
		/************************** CREDENTIALS ****************************/
		ConfigFile configFile = new ConfigFile();
		Properties prop = configFile.readPropertiesFile("config.properties");
		/*******************************************************************/
		//charger le driver
		try {
			Class.forName(prop.getProperty("db.driver.class"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//get db connection
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.login"), prop.getProperty("db.password"));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
		return connection;
	}
}
