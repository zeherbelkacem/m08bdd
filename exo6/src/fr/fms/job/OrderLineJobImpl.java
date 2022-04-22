package fr.fms.job;

import fr.frm.dao.Dao;
import fr.frm.dao.DaoFactory;
import fr.frm.entities.OrderLine;

public class OrderLineJobImpl implements OrderLineJob {
	private Dao<OrderLine> orderLineDao = DaoFactory.getOrderLineDao();

	@Override
	public void insertOrderLine(OrderLine orderLine) {
		orderLineDao.createObj(orderLine);

	}

	@Override
	public int getLastOrderItemId() {
		return orderLineDao.getLastInsertedId();
	}

}
