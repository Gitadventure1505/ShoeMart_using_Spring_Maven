package com.ecommerce.repository;


import java.time.LocalDateTime;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.Ordersplaced;

public interface OrdersplacedRepository extends JpaRepository<Ordersplaced, Integer>
{
	
	List<Ordersplaced> findByorderplacedtimeBetween(LocalDateTime startdate, LocalDateTime enddate);
	
	List<Ordersplaced> findByEmail(String email);
}
