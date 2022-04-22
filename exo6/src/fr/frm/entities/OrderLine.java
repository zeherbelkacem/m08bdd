package fr.frm.entities;

public class OrderLine {
	private int id;
	private int quantity;
	private int idOrder;
	private int idArticle;
	private int virtualFK;
	
//	public OrderLine(int id, int quantity, int idOrder, int idArticle) {
//		this.id = id;
//		this.quantity = quantity;
//		this.setIdOrder(idOrder);
//		this.idArticle = idArticle;
//	}
	public OrderLine(int id, int quantity, int idArticle, int virtualFK) {
		this.id = id;
		this.quantity = quantity;
		this.idArticle = idArticle;
		this.setVirtualFK(virtualFK);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}
	@Override
	public String toString() {
		return "OrderLine [id=" + id + ", quantity=" + quantity + ", idArticle=" + idArticle + "]";
	}
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public int getVirtualFK() {
		return virtualFK;
	}
	public void setVirtualFK(int virtualFK) {
		this.virtualFK = virtualFK;
	}
	
	
}
