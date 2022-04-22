package fr.fms.job;

import java.util.List;

import fr.frm.dao.Dao;
import fr.frm.dao.DaoFactory;
import fr.frm.entities.Order;

public class OrderJobImpl implements OrderJob {
	
	private Dao<Order> orderDao = DaoFactory.getOrderDao();
	@Override
	public void insertOrderLineToOrder(Order orderLine) {
		orderDao.createObj(orderLine);

	}
	@Override
	public int getLastOrderId() {
		return orderDao.getLastInsertedId();
	}
	@Override
	public void updateOrder(Order order) {
		orderDao.updateObj(order);
	}
	@Override
	public List<String[]> getInvoiceElements(int orderId) {
		
		return orderDao.getInvoice(orderId);
	}

}
