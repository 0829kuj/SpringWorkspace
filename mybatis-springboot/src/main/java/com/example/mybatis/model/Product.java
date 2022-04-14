package com.example.mybatis.model;

public class Product {
	
	private Long prodId;	// CamelCase 다른 단어끼리 합칠때 두번째 단어부턴 첫문자만 대문자사용
	private String prodName; //prod_id , prod_name, prod_id DB 열이름
	private int prodPrice;
	//모든 필드변수
	public Product(Long prodId, String prodName, int prodPrice) {
		this.prodId = prodId;
		this.prodName = prodName;
		this.prodPrice = prodPrice;
	}
	//id빼고 (입력시에 id는 자동생성되므로)
	public Product(String prodName, int prodPrice) {
		this.prodName = prodName;
		this.prodPrice = prodPrice;
	}
	
	public Long getProdId() {
		return prodId;
	}
	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}
	
}
