package com.example.board.domain;

import org.springframework.data.domain.Page;

import lombok.Getter;

@Getter
public class PageDTO {
	// 페이지 블록에서 시작 번호화 끝 번호를 저장하는 변수들
	private int startPage;
	private int endPage;
	// 이전, 다음 블럭 존재 여부를 저장
	private boolean prev, next;
	
	private Page<Post> page;
	private int totalPages; // 전체 페이지 수
	private long totalElements; // 전체 레코드 수
	
	public PageDTO(Page<Post> page) {
		this.page = page;
		this.totalPages = page.getTotalPages();
		this.totalElements = page.getTotalElements();
		// 현재 페이지
		int currentPage = page.getNumber() + 1;
		this.endPage = (int)Math.ceil(currentPage / 3.0)* 3;//double로 계산을 해야 값이 나옴 -> int로 형변환 필요
		this.startPage = endPage - 2;
		
		// 마지막 페이지를 확인하는 과정이 필요
		if(totalPages < endPage) {
			endPage = totalPages;
		}
		
		this.prev = this.startPage > 1; // 이전 페이지가 있을때 true
		this.next = this.endPage < this.totalPages; 
		}
}
