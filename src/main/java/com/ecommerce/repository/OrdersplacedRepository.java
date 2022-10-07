package com.ecommerce.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.Ordersplaced;

public interface OrdersplacedRepository extends JpaRepository<Ordersplaced, Integer>
{
	
	List<Ordersplaced> findByorderplacedtimeBetween(Date startdate, Date enddate);
}
