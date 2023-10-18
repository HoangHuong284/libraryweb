package com.testForLTW.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.testForLTW.entity.Order;


public interface OrderService {

	public void saveOrder(Order order);
	
	List<Order> getAllOrderByUser_Id(int id);

	Order findById(int id);

	List<Order> findAll();

	Page<Order> findAll(Pageable pageable);

	void deleteById(int id);

}
