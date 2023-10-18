package com.testForLTW.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_item")
public class Order_Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="count")
	private int count;

	@ManyToOne
	@JoinColumn(name="book_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Book book;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Order order;
	
}
