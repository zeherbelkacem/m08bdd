package fr.frm.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import fr.frm.entities.Article;
import fr.frm.entities.OrderLine;

public class OrderLineDao implements Dao<OrderLine>{

	@Override
	public List<OrderLine> readAllObjs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createObj(OrderLine t) {
		try (PreparedStatement preparedStatement = connection
				.prepareStatement("INSERT INTO orderItems(itemQuantity, idArticle, virtualFK) VALUES(?,?,?)")) {
			//preparedStatement.setInt(1, t.getId());
			preparedStatement.setInt(1, t.getQuantity());
			preparedStatement.setInt(2, t.getIdArticle());
			preparedStatement.setInt(3, t.getVirtualFK());
			
			if (preparedStatement.executeUpdate() == 1)
				System.out.println("Ligne de commande bien inserée");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public boolean updateObj(OrderLine t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteObj(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public OrderLine getObjById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> getArticlesByCategory(int idCategory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLastInsertedId() {
		int rsIdItem = 0;
		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery("SELECT MAX(idOrderItems) FROM orderItems")) {

				while (resultSet.next()) {
					 rsIdItem = resultSet.getInt(1);
					
				}
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return rsIdItem;
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
