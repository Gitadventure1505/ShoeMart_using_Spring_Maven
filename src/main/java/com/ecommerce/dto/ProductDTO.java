package com.ecommerce.dto;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ecommerce.model.Category;


public class ProductDTO 
{

	private Long id; 
	private String  Name; 
	private int categoryId; 
	private double price;
	private double weight;
	private String description;
	private String imageName;
	public ProductDTO(Long id, String name, int categoryId, double price, double weight, String description,
			String imageName) {
		super();
		this.id = id;
		Name = name;
		this.categoryId = categoryId;
		this.price = price;
		this.weight = weight;
		this.description = description;
		this.imageName = imageName;
	}
	public ProductDTO() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	
	
	

	
	
	
	
	
	
}


