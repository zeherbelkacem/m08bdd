package fr.fms.job;

import fr.frm.entities.OrderLine;

public interface OrderLineJob {
	void insertOrderLine(OrderLine orderLine);

	int getLastOrderItemId();
}
