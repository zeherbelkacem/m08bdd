package exo2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestMariaClient {

	public static void main(String[] args) {
		
		List<Article> articles = new ArrayList<Article>();
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//
		String url = "jdbc:mariadb://localhost:3306/shop";
		String login = "root";
		String passwordString = "fms2022";
		
		try(Connection connection = DriverManager.getConnection(url, login, passwordString)) {
			String strSql = "SELEcT *FROM t_articles";
			try(Statement statement = connection.createStatement()){
				try(ResultSet resultSet = statement.executeQuery(strSql)){
					
					while(resultSet.next()) {
						int rsId = resultSet.getInt(1);
						String rsDescription = resultSet.getString(2);
						String rsBrand = resultSet.getString(3);
						double rsPrice = resultSet.getDouble(4);
						String rsCategory = resultSet.getString(5);
						articles.add(new Article(rsId, rsDescription, rsBrand, rsPrice, rsCategory));
					}
					
				}
			}
			for(Article a: articles)
				System.out.println(a.getId()+ "- "+ a.getDescription()+"  -  "+a.getBrand()+"  -  "+a.getPrice()+"  -  "+a.getCategory());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
