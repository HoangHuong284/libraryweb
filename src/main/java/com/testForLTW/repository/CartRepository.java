package com.testForLTW.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.testForLTW.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer>{

//	@Query(value="DELETE FROM `cart` e WHERE e.id= ?1",nativeQuery = true)
	void deleteById(int id);

	List<Cart> findAllByUser_id(int user_id);
	
	
}
