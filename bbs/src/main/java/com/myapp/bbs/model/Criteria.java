package com.myapp.bbs.model;

import java.util.Arrays;

/**
 * 페이지 계산을 위한 클래스
 * @author KIMeonjeong
 * */
public class Criteria {

	private int pageNum;	// 현재 페이지
	
	private int amount;	// 한 페이지당 보여질 게시물 수
	
	private int skip;	// 스킵할 게시물 수 ((pageNum - 1 ) * amount)
	
	private String keyword;	// 검색어 키워드
	
	private String type;	// 검색 타입 (뷰에서 선택됨)
	
	private String[] typeArr;	// 검색 타입 배열 (type을 배열로 변환)
	
	// 기본생성자 => 기본세팅: pageNum = 1, amount = 10
	public Criteria() {
		this(1, 10);	// 전체 생성자를 통해 (1, 10)을 입력해 객체 생성
	}

	// 생성자 => 원하는 pageNum, 원하는 amount
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.skip = (pageNum - 1 ) * amount;
	}

	public int getPageNum() {
		return pageNum;
	}

	// 새로 페이지 수를 설정했을 때 skip도 계산
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
		this.skip = (pageNum - 1 ) * amount;
	}

	public int getAmount() {
		return amount;
	}

	// 페이지당 데이터 갯수를 바꿀 경우에도 skip을 다시 계산
	public void setAmount(int amount) {
		this.amount = amount;
		this.skip = (pageNum - 1 ) * amount;
	}

	public int getSkip() {
		return skip;
	}

	public void setSkip(int skip) {
		this.skip = skip;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		// 검색할 타입만 설정되면 typeArr은 자동으로 생성
		this.typeArr = type.split("");	// 한 문자씩 끊어서 배열로 만듦
	}

	public String[] getTypeArr() {
		return typeArr;
	}

	public void setTypeArr(String[] typeArr) {
		this.typeArr = typeArr;
	}

	@Override
	public String toString() {
		return "Criteria [pageNum=" + pageNum + ", amount=" + amount + ", skip=" + skip + ", keyword=" + keyword
				+ ", type=" + type + ", typeArr=" + Arrays.toString(typeArr) + "]";
	}
}
