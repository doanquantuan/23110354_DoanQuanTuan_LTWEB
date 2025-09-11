package vn.iotstar.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Categories")
public class Category implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID")
	private int categoryId;
	
	@Column(name = "CategoryName", columnDefinition = "NVARCHAR(MAX)")
	private String categoryName;
	
	@Column(name = "Images", columnDefinition = "NVARCHAR(MAX)")
	private String images;
	
	@Column(name = "Status", columnDefinition = "INT")
	private int status;
	
	@Column(name = "UserID", columnDefinition = "INT")
	private int userId;

	public Category() {
		super();
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Category(int categoryId, String categoryName, String images, int status, int userId) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.images = images;
		this.status = status;
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", images=" + images
				+ ", status=" + status + ", userId=" + userId + "]";
	}

	
	
	
}
