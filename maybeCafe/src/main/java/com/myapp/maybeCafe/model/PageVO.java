package com.myapp.maybeCafe.model;
/**
 * 페이지 계산용 클래스
 * @author KIMeonjeong
 * */
public class PageVO {
	private int pageNum;	// 현재페이지
	private int amount;		// 한 페이지의 게시글 수
	private int skip;		// 스킵할 게시글 수 ((page - 1) * amount)
	
	// 기본생성자 => 기본세팅: pageNum = 1, amount = 10
	public PageVO() {
		this(1, 10);
	}
	
	public PageVO(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.skip = (pageNum - 1) * amount;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	
	// 새로 페이지 수를 설정했을 때 skip도 계산
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
		this.skip = (pageNum - 1) * amount;
	}
	
	public int getAmount() {
		return amount;
	}
	
	// 페이지 당 데이터 갯수를 바꿀때에도 skip을 다시 계산
	public void setAmount(int amount) {
		this.amount = amount;
		this.skip = (pageNum - 1) * amount;
	}
	
	public int getSkip() {
		return skip;
	}
	
	public void setSkip(int skip) {
		this.skip = skip;
	}

	@Override
	public String toString() {
		return "PageVO [pageNum=" + pageNum + ", amount=" + amount + ", skip=" + skip + "]";
	}
}
