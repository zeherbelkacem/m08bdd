package fr.frm.entities;

import java.util.Date;

public class Order {
	private int id;
	private Date date;
	private double totalPrice;
	private int idUser;
	private int orderItemId;
	
	public Order(int id, Date date, double totalPrice, int idUser, int orderItemId) {
		this.id = id;
		this.date = date;
		this.totalPrice = totalPrice;
		this.idUser = idUser;
		this.setOrderItemId(orderItemId);
	}


	public Order(int id, Date date, double totalPrice, int idUser) {
		this.id = id;
		this.date = date;
		this.totalPrice = totalPrice;
		this.idUser = idUser;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", date=" + date + ", totalPrice=" + totalPrice + ", idUser=" + idUser + "]";
	}


	public int getOrderItemId() {
		return orderItemId;
	}


	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

}
