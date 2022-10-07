package com.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;

@Service
public class UserService
{
	@Autowired
	UserRepository userrepository;
	
	public List<User> getAllUsers()
	{

		return userrepository.findAll();
	}
	
	public List<User> getUsersByFirstNameAndLastName(String firstname, String lastname)
	{
		List<User> users = new ArrayList<User>();
		users.addAll(userrepository.findByFirstNameContaining(firstname));
		users.addAll(userrepository.findByLastNameContaining(lastname));
		return users;
	}
	
	

	
	
	
}
