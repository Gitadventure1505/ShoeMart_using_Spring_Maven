package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;


@Service
public class ProductService 
{
	@Autowired
	 ProductRepository productrepository;

	public List<Product> getAllProduct()
	{
		return productrepository.findAll();
		
	}
	
	
	public void addProduct(Product product)
	{
		productrepository.save(product);
		
	}
	
	
	public void removeProductById(Long id)
	{
		productrepository.deleteById(id);
	}
	
	
	public Optional<Product> getProductById(Long id)
	{
			return productrepository.findById(id);
	}
	
	public List<Product> getAllProductsByCategoryId(int id)
	{
		return productrepository.findAllByCategory_Id(id);
	}
	
	
}
