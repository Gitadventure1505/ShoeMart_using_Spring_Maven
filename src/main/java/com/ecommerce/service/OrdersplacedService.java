package com.ecommerce.service;


import java.time.LocalDateTime;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Ordersplaced;
import com.ecommerce.repository.OrdersplacedRepository;

@Service
public class OrdersplacedService 
{

	@Autowired
	private OrdersplacedRepository ordersplacedrepository;
	
	public List<Ordersplaced> getAllOrders()
	{
		
		return ordersplacedrepository.findAll();
		
	}
	
	public List<Ordersplaced> getAllOrdersWithMail(String email)
	{
		
		return ordersplacedrepository.findByEmail(email);
		
	}
	
	
	
	public List<Ordersplaced> getAllOrdersInbetween(LocalDateTime startdate, LocalDateTime enddate)
	{
		
		return ordersplacedrepository.findByorderplacedtimeBetween(startdate, enddate);
		
	}
	
	public void addOrders(Ordersplaced ordersplaced)
	{
		ordersplacedrepository.save(ordersplaced);
	}
	
	
	
	
	
	
	
	
}
