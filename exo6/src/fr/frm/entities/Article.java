package fr.frm.entities;

public class Article {
	private int id;
	private String description;
	private String brand;
	private double price;
	private int idCategory;
	private int quantity;
	
	public Article(int id, String description, String brand, double price, int quantity) {
		this.id = id;
		this.description = description;
		this.brand = brand;
		this.price = price;
		this.setQuantity(quantity);
	}

	public Article(int id, String description, String brand, double price) {
		this.id = id;
		this.description = description;
		this.brand = brand;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", description=" + description + ", brand=" + brand + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	
	
	

}
