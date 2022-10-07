package com.ecommerce.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ecommerce.dto.OrdersplacedDTO;
import com.ecommerce.dto.ProductDTO;
import com.ecommerce.global.GlobalData;
import com.ecommerce.model.Ordersplaced;
import com.ecommerce.model.Product;
import com.ecommerce.service.OrdersplacedService;
import com.ecommerce.service.ProductService;

@Controller
public class CartController 
{
	@Autowired
	ProductService productservice;
	
	@Autowired
	OrdersplacedService ordersplacedservice;
	
	
	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable long id )
	{
		GlobalData.cart.add(productservice.getProductById(id).get());
		return "redirect:/shop";
	}
	
	
	@GetMapping("/cart")
	public String cartGet(Model model)
	{
		
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		model.addAttribute("cart" , GlobalData.cart);
		return "cart";
		
	}
	
	//cart/removeItem/{index}
	
	@GetMapping("/cart/removeItem/{index}")
	public String cartItemRemove(@PathVariable int index)
	{
		
		GlobalData.cart.remove(index);
		return "redirect:/cart";

	}
	
	
	@GetMapping("/checkout")
	public String checkout(Model model)
	{
		model.addAttribute("ordersplacedDTO", new OrdersplacedDTO());
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		return "checkout";
		
		
	}
	
	
	
	@PostMapping("/checkout")
	public String payment(@ModelAttribute("ordersplacedDTO") OrdersplacedDTO ordersplacedDTO) 
	{
		 Long datetime = System.currentTimeMillis();
		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		 
		//java.sql.Date sqldate = new java.sql.Date(Integer.parseInt(arr[0]),Integer.parseInt(arr[1]),Integer.parseInt(arr[2]));
		 ordersplacedDTO.setOrderplacedtime(new Date(datetime));
		 
		 
		 Ordersplaced ordersplaced = new Ordersplaced();
		 ordersplaced.setAdditionalinfo(ordersplacedDTO.getAdditionalinfo());
		 ordersplaced.setAddress1(ordersplacedDTO.getAddress1());
		 ordersplaced.setAddress2(ordersplacedDTO.getAddress2());
		 ordersplaced.setAmountpayable(ordersplacedDTO.getAmountpayable());
		 ordersplaced.setCity(ordersplacedDTO.getCity());
		 ordersplaced.setEmail(ordersplacedDTO.getEmail());
		 ordersplaced.setFirstname(ordersplacedDTO.getFirstname());
		 ordersplaced.setLastname(ordersplacedDTO.getLastname());
		 ordersplaced.setId(ordersplacedDTO.getId());
		 ordersplaced.setOrderplacedtime(ordersplacedDTO.getOrderplacedtime());
		 ordersplaced.setPhone(ordersplacedDTO.getPhone());
		 ordersplaced.setPostcode(ordersplacedDTO.getPostcode());
		 
		 ordersplacedservice.addOrders(ordersplaced);
		 GlobalData.cart.clear();
		 
		 return "PaymentSuccessful";

		 

	}
	

	

	
}
