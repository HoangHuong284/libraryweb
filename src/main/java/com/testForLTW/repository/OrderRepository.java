package com.testForLTW.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.testForLTW.entity.Order;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order,Long>{
	//List<Order> findAllByUser_id(int user_id);
	List<Order> findAllByUser_id(int user_id);
	Order findById(int id);
	
	Page<Order> findAll(Pageable pageable);

	void deleteById(int id);
	

	
}
