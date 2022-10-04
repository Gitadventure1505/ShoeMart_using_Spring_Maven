package com.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.User;

public interface UserRepository extends JpaRepository<User, Integer>
{
	Optional<User> findUserByEmail(String email);

	
	List<User> findByFirstNameAndLastName(String firstname, String Lastname);
	
	List<User> findByFirstName(String firstname);
	
	List<User> findByLastName(String lastName);
	

}
 