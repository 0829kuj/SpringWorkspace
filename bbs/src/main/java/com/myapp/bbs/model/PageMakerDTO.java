package com.myapp.bbs.model;

/**
 * 페이지네이션을 위해 전체 게시글 수 외 cri를 입력받아 계산하여
 * 시작페이지, 끝페이지, 이전/다음페이지 유무를 저장
 * */
public class PageMakerDTO {
	
	private int startPage;		// 시작 페이지
	
	private int endPage;		// 끝 페이지
	
	private boolean prev, next;	// 이전, 다음페이지 존재유무
	
	private int total;			// 전체 게시글 수
	
	private Criteria cri; 		// 현재 페이지, 페이지당 게시글 표시 수 정보

	public PageMakerDTO(int total, Criteria cri) {
		this.total = total;
		this.cri = cri;
	
		// 마지막페이지(현재 페이지네이션바의 마지막 숫자): 10 단위로 표시 1~10, 11~20, 21~30
		// Math.ceil은 소수점을 정수로 올림
		this.endPage = (int)(Math.ceil(cri.getPageNum() / 10.0)) * 10;
		
		// 시작 페이지
		this.startPage = this.endPage - 9;
		
		// 실제 마지막 페이지 (총 마지막 페이지)
		int realEnd = (int)(Math.ceil(total * 1.0 / cri.getAmount()));
		
		// 전체 마지막 페이지(realEnd)가 화면에 보이는 마지막페이지(endPage)보다 작은 경우, 보이는 페이지(endPage) 값 조절
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
	
		// < 이전페이지 참? 시작 페이지(startPage)값이 1보다 큰 경우 true
	    this.prev = this.startPage > 1;
	    
	    // > 다음페이지 참? 마지막 페이지(endPage)값이 1보다 큰 경우 true
	    this.next = this.endPage < realEnd;

	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	@Override
	public String toString() {
		return "PageMakerDTO [startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next
				+ ", total=" + total + ", cri=" + cri + "]";
	}
	
	
}
