package com.testForLTW.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testForLTW.entity.Cart;
import com.testForLTW.repository.CartRepository;
import com.testForLTW.service.CartService;

@Service
public class CartServicempl implements CartService{

	@Autowired
	CartRepository cartRepository;

	@Override
	public void deleteById(int id) {
		cartRepository.deleteById(id);
		
	}

	@Override
	public List<Cart> GetAllCartByUser_id(int user_id) {
		//return cartRepository.findAllByUser_id(user_id);
		return cartRepository.findAllByUser_id(user_id);
	}

	@Override
	public void saveCart(Cart cart) {
		cartRepository.save(cart);
		
	}
	
	
	
}
