package org.doit.ik.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 페이징 기준, 척도
@Getter
@Setter
@ToString
public class Criteria {
	
	private int pageNum; // 현재 페이지 번호
	private int amount; // 한 페이지 출력할 게시글 수
	
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	// ?pageNum=2&amount=10&type=T&keyword=홍길동&...
	// org.springframework.web.util
	// 		ㄴ UriComponentsBuilder : 파라미터 편하게 만들어주는 클래스
	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
						.queryParam("pageNum", this.pageNum)
						.queryParam("amount", this.amount);
		return builder.toUriString();
	}
	
} // class
