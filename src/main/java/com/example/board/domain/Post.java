package com.example.board.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length =100)
	private String title;
	
	@Lob // 대용량 데이터를 넣을수 있는 데이터 타입
	@Column(nullable = false)
	private String content;
	
	@CreationTimestamp // 자동 시간 추가
	private Timestamp creatDate;
	
	private int cnt; // 조회수
	
	// 작성자(엔티티는 하나의 칼럼만 가져오는것 불가, 그래서 객체로)
	// 1대 다 관계 설정 어노테이션, fetch는 유저정보를 가져올 때 옵션 설정(게시글 작성시 정보가져오기/필요할때 정보가져오기)
	@ManyToOne(fetch = FetchType.EAGER) // 작성시 유저 정보 가져오기(EAGER, LAZY : 필요시)
	@JoinColumn(name = "userid") //외래키 설정
	private User user;
	
	// 댓글을 저장하는 컬럼 추가 필요(하나의 post에 여러개의 댓글 저장 가능)
	// mappedBy는 양방향관계일때(서로가 서로를 참조해야 할경우에 사용) - 양방향관계에서 '주'테이블 지정
	@OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@OrderBy("id desc")
	private List<Reply> replyList;
}
