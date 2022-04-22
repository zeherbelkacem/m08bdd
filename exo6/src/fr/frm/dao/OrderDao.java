package fr.frm.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import fr.frm.entities.Article;
import fr.frm.entities.Order;
import fr.frm.entities.Utilisateur;

public class OrderDao implements Dao<Order> {

	public OrderDao() {
	}

	@Override
	public List<Order> readAllObjs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createObj(Order t) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"INSERT INTO t_orders(orderDate, totalPrice, idUser, idOrderItems) VALUES(?,?,?,?)")) {
			java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
			preparedStatement.setDate(1, currentDate);
			preparedStatement.setDouble(2, t.getTotalPrice());
			preparedStatement.setInt(3, t.getIdUser());
			preparedStatement.setInt(4, t.getOrderItemId());

			if (preparedStatement.executeUpdate() == 1)
				System.out.println("Article bien inseré");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean updateObj(Order t) {
		boolean status = false;
		// db connection
		// Connection connection = connect.connection();
		try (PreparedStatement preparedStatement = connection
				.prepareStatement("update t_order set orderDate = ?, totalPrice = ?, idUser = ? where idOrder = ?;")) {
			java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
			preparedStatement.setDate(1, currentDate);
			preparedStatement.setDouble(2, t.getTotalPrice());
			preparedStatement.setInt(3, t.getIdUser());
			// preparedStatement.setInt(set, article.getIdCategory());
			preparedStatement.setInt(4, t.getId());
			if (preparedStatement.executeUpdate() == 1)
				status = true;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return status;
	}

	@Override
	public boolean deleteObj(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Order getObjById(int id) {
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
		int rsIdOrder = 0;
		try (Statement statement = connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery("SELECT MAX(idOrder) FROM t_order")) {

				while (resultSet.next()) {
					rsIdOrder = resultSet.getInt(1);

				}
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return rsIdOrder;
	}

	@Override
	public int getUserId(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String[]> getInvoice(int orderId) {
		List<String[]> invoiceElements = new ArrayList<String[]>();
		try {
			try (PreparedStatement statement = connection.prepareStatement(
					"select t_orders.idorder, t_orders.orderdate, t_orders.totalprice, t_articles.Description, t_articles.brand, orderItems.itemquantity, t_users.login from (((t_orders inner join orderItems on orderItems.virtualFK=t_orders.idorder) inner join t_users on t_users.idUser=t_orders.iduser) inner join t_articles on t_articles.idarticle=orderItems.idarticle) where idorder=?;")) {
				statement.setInt(1, orderId);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						String[] arrayElem = new String[7];
						arrayElem[0] = String.valueOf(resultSet.getInt("idOrder"));
						arrayElem[1] = new SimpleDateFormat("yyyy-MM-dd").format(resultSet.getDate("orderDate"));
						arrayElem[2] = String.valueOf(resultSet.getDouble("totalPrice"));
						arrayElem[3] = resultSet.getString("description");
						arrayElem[4] = resultSet.getString("Brand");
						arrayElem[5] = String.valueOf(resultSet.getInt("itemQuantity"));
						arrayElem[6] = resultSet.getString("login");
						invoiceElements.add(arrayElem);// asList({rsOrderId, rsDate});
					}
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return invoiceElements;
	}

}
