package com.testForLTW.entity;

import java.sql.Date;
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
@Table(name="book")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="tieude" , columnDefinition = "nvarchar(1111)" )
	private String tieude;
	
    @Column(name = "tacgia", columnDefinition = "nvarchar(1111)")
	private String tacgia;
    
    @Column(name = "theloai", columnDefinition = "nvarchar(1111)")
	private String theloai ;
    
    @Column(name = "ngayphathanh")
	private Date ngayphathanh;
    
    @Column(name = "sotrang")
	private Integer sotrang;
    
    @Column(name = "sold")
	private Integer sold;
    
    @Column(name = "img", columnDefinition = "nvarchar(1111)")
	private String img ;
    
    @Column(name = "mota", columnDefinition = "nvarchar(1111)")
    private String mota;
    
    @Column(name = "price")
	private Integer price;
    
    @Column(name = "quantity")
	private int quantity;
    
    @OneToMany(mappedBy = "book" , cascade = CascadeType.ALL)
    private List<Order_Item> order_Item;
    
    @OneToMany(mappedBy = "book" , cascade = CascadeType.ALL)
    private List<Cart> cart;
    
    @OneToMany(mappedBy = "book" , cascade = CascadeType.ALL)
    private List<Comment> comment;

	
    
}
