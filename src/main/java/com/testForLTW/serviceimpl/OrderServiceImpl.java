package com.testForLTW.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.testForLTW.entity.Order;
import com.testForLTW.repository.OrderRepository;
import com.testForLTW.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderRepository orderRepository;

	@Override
	public void saveOrder(Order order) {
		orderRepository.save(order);
		
	}

	
	@Override
	public Order findById(int id) {
		return orderRepository.findById(id);
	}

	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public Page<Order> findAll(Pageable pageable) {
		return orderRepository.findAll(pageable);
	}

	@Override
	public void deleteById(int id) {
		orderRepository.deleteById(id);
		
	}


	public List<Order> getAllOrderByUser_Id(String id) {
		
		return orderRepository.findAllByUser_id(Integer.parseInt(id));
	}


	@Override
	public List<Order> getAllOrderByUser_Id(int id) {
		// TODO Auto-generated method stub
		return orderRepository.findAllByUser_id(id);
	}


	
	
}
