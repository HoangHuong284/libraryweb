package com.testForLTW.service;

import java.util.List;

import com.testForLTW.entity.Cart;


public interface CartService {
	
	void deleteById(int id);
	
	
	List<Cart> GetAllCartByUser_id(int id);
	
	void saveCart(Cart cart);
	
}
