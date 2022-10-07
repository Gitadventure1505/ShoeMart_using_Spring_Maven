package com.ecommerce.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.OrdersplacedService;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UserService;

@Controller
public class AdminController 
{
	public static String uploadDir = System.getProperty("user.dir")+ "/src/main/resources/static/productImages" ;
	
	
	@Autowired
	public CategoryService categoryService;
	
	@Autowired
	public ProductService productService;
	
	@Autowired
	public UserService userservice;
	
	
	@Autowired
	OrdersplacedService ordersplacedservice;
	
	
	@GetMapping("/admin")
	public String adminHome()
	{
		return "adminHome";
	}
	
	@GetMapping("/admin/listofusers")
	public String listOfUsers(@RequestParam(value = "Name", required = false)String name, Model model)
	{
		
		if(name == null)
		{
			model.addAttribute("allusers", userservice.getAllUsers());		
		}
		else
		{
			model.addAttribute("allusers",userservice.getUsersByFirstNameAndLastName(name, name));
		}
		return "listofusers";
		}

	
	@GetMapping("/admin/purchasereport")
	public String getPurchaseReport
		   (@RequestParam(value = "startdate", required = false)String a, 
			@RequestParam(value = "enddate", required = false)String b,
			Model model)
	{
		Date startdate = null;; 
		Date enddate = null;
		
		
		System.out.println(a+b);
		if(startdate == null)
		{
			model.addAttribute("allorders", ordersplacedservice.getAllOrders());
		}
		else
		{
			model.addAttribute("allorders", ordersplacedservice.getAllOrdersInbetween(startdate, enddate));
		}
	
		return "purchaseReport"	;
	}
	
	
	
	
	
	@GetMapping("/admin/categories")
	public String getcat(Model model)
	{
		model.addAttribute("categories", categoryService.getAllCategory());
		return "categories";
	}
	
	@GetMapping("/admin/categories/add")
	public String getcatAdd(Model model)
	{
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}
	
	
	
	@PostMapping("/admin/categories/add")
	public String postcatAdd(@ModelAttribute("category") Category category)
	{
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}
	
	
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCat(@PathVariable int id)
	{
		categoryService.removeCategoryById(id);
		return "redirect:/admin/categories";
	}
	
	
	@GetMapping("/admin/categories/update/{id}")
	public String updateCat(@PathVariable int id, Model model)
	{
		Optional<Category> category = categoryService.getCategoryById(id);
		if(category.isPresent())
		{
			model.addAttribute("category", category.get());
			return "categoriesAdd";
		}
		else
		{
			return "404";
		}
	}
	
	
	@GetMapping("/admin/products")
	public String products(Model model)
	{
	
		model.addAttribute("products" , productService.getAllProduct());
		return "products";
		
	}
	

	
	@GetMapping("admin/products/add")
	public String ProductAddGet(Model model) 
	{
		model.addAttribute("productDTO", new ProductDTO());
		model.addAttribute("categories", categoryService.getAllCategory());
		return "productsAdd";
	
}
	
	
	@PostMapping("admin/products/add")
	public String productAddPost(@ModelAttribute("productDTO")ProductDTO productDTO, @RequestParam("productImage")MultipartFile file, @RequestParam("imgName")String imgName)
	throws IOException
	{
		
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
		product.setPrice(productDTO.getPrice());
		product.setWeight(productDTO.getWeight());
		product.setDescription(productDTO.getDescription());
		
		String imageUUID;
		if(!file.isEmpty())
		{
			imageUUID = file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, file.getBytes());
			
		}
		else
		{
			imageUUID = imgName;
			
		}
		product.setImageName(imageUUID);
		productService.addProduct(product);
		
		
		
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable long id)
	{
		productService.removeProductById(id);
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/product/update/{id}")
	public String updateProductGet(@PathVariable long id, Model model)
	{
		Product product = productService.getProductById(id).get();
		
		
		ProductDTO productDTO =  new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setDescription(product.getDescription());
		productDTO.setImageName(product.getImageName());
		productDTO.setName(product.getName());
		productDTO.setPrice(product.getPrice());
		productDTO.setWeight(product.getWeight());
		productDTO.setCategoryId(product.getCategory().getId());
		
		
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("productDTO", productDTO);
		
			 
		return "productsAdd";
	}
	
	

	
	
	
}
