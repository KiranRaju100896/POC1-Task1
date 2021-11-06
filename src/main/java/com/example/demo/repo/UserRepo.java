package com.example.demo.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;
@Repository
public interface UserRepo extends CrudRepository<User, String> {

	User findByEmail(String email) ;
	
	User findByUserid(String userid) ;
	
	List<User> findByFirstnameOrderByDobDesc(String name);
	
	List<User> findByLastnameOrderByDobDesc(String name);
	
	List<User> findByPincodeOrderByDobDesc(int pin);
 	
}
