package com.testForLTW.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testForLTW.entity.Order_Item;
import com.testForLTW.repository.Order_ItemRepository;
import com.testForLTW.service.Order_ItemService;

@Service
public class Order_ItemServiceImpl implements Order_ItemService{

	@Autowired
	Order_ItemRepository order_ItemRepository;

	@Override
	public List<Order_Item> getAllByOrder_Id(int id) {
		return order_ItemRepository.findAllByOrder_id(id);
	}

	@Override
	public void saveOrder_Item(Order_Item order_Item) {
		order_ItemRepository.save(order_Item);
		
	}

	@Override
	public void deleteById(int id) {
		order_ItemRepository.deleteById(id);
	}
	
}
