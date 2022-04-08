package com.myapp.shoppingmall.ado;

import lombok.Data;

@Data
public class Cart {
	
	private int id;
	private String name;
	private String price;
	private int quantitiy;
	private String image;
	
	public Cart(int id, String name, String price, int quantitiy, String image) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantitiy = quantitiy;
		this.image = image;
	}

	
}
