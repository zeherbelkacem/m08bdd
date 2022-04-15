package exo3;

public class Category {
	private int id;
	private String catName;
	private String description;
	public Category(int id, String catName, String description) {
		super();
		this.id = id;
		this.catName = catName;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Category [id=" + id + ", catName=" + catName + ", description=" + description + "]";
	}
	

}
