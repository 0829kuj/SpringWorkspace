package com.myapp.maybeCafe.model;

import lombok.Data;

@Data
public class PageMakerDTO {
	private int startPage;		// 시작 페이지
	private int endPage;		// 끝 페이지
	private boolean prev, next;	// 이전, 다음페이지 존재유무
	private int total;			// 전체 게시글 수
	private PageVO pageVo;		// 현재 페이지, 페이지당 게시글 표시 수 정보
	
	public PageMakerDTO(int total, PageVO page) {
		this.total = total;
		this.pageVo = page;
		
		// 마지막 페이지(현제 페이지네이션바의 마지막 숫자): 10 단위로 표시 1~10, 11~20
		// Math.ceil은 올림함수
		this.endPage = (int)(Math.ceil(pageVo.getPageNum() / 10.0)) * 10;
		
		// 시작 페이지
		this.startPage = this.endPage - 9;
		
		// 실제 마지막 페이지 (총 마지막 페이지)
		int realEnd = (int)(Math.ceil(total * 1.0 / pageVo.getAmount()));
		
		// 전체 마지막 페이지(realEnd)가 화면에 보이는 마지막 페이지(endPage)보다 작은 경우
		if (realEnd < this.endPage) {
			this.endPage = realEnd;		// 화면에 보일 마지막 페이지를 전체 마지막 페이지로 변경
		}
		
		// < 이전페이지 참? 시작 페이지(startPage)값이 1보다 큰 경우 true
		this.prev = this.startPage > 1;
		
		// < 다음페이지 참? 마지막 페이지(endPage)값이 1보다 큰 경우 true
		this.next = this.endPage < realEnd;
	}
	
	
}
