package com.ecommerce.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Ordersplaced {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String firstname;
	private String lastname;
	private String address1;
	private String address2;
	private long postcode;
	private String city;
	private long phone;
	private String email;
	private String additionalinfo;
	private double amountpayable;
	@CreationTimestamp
	private LocalDateTime orderplacedtime;

	public Ordersplaced() {
		super();
	}

	public Ordersplaced(int id, String firstname, String lastname, String address1, String address2, long postcode,
			String city, long phone, String email, String additionalinfo, double amountpayable,
			LocalDateTime orderplacedtime) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address1 = address1;
		this.address2 = address2;
		this.postcode = postcode;
		this.city = city;
		this.phone = phone;
		this.email = email;
		this.additionalinfo = additionalinfo;
		this.amountpayable = amountpayable;
		this.orderplacedtime = orderplacedtime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public long getPostcode() {
		return postcode;
	}

	public void setPostcode(long postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdditionalinfo() {
		return additionalinfo;
	}

	public void setAdditionalinfo(String additionalinfo) {
		this.additionalinfo = additionalinfo;
	}

	public double getAmountpayable() {
		return amountpayable;
	}

	public void setAmountpayable(double amountpayable) {
		this.amountpayable = amountpayable;
	}
	
	  public LocalDateTime getOrderplacedtime() 
	  {
		  return orderplacedtime;
	  } 
	  public void setOrderplacedtime(LocalDateTime orderplacedtime) 
	  {
		  this.orderplacedtime= orderplacedtime; 
		  }
	 

}
