package com.testForLTW.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "username", columnDefinition = "nvarchar(1111)")
	private String username;
	
	@Column(name = "email", columnDefinition = "nvarchar(1111)")
	private String email ;
	
	@Column(name = "password", columnDefinition = "nvarchar(1111)")
	private String password ;
	
	@Column(name = "role", columnDefinition = "nvarchar(1111)")
	private String role;
	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
	private List<Order> order;
	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
	private List<Cart> cart;
	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
	private List<Comment> comment;
	
}
