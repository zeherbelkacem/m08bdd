package fr.fms.job;

import java.util.List;

import fr.frm.entities.Order;

public interface OrderJob {
	void insertOrderLineToOrder(Order order);

	int getLastOrderId();

	void updateOrder(Order order);

	List<String[]> getInvoiceElements(int orderId);
}
