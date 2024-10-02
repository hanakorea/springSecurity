package com.example.board.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // 처음꺼로, 테이블 자동생성(보통 클래스 명이랑 동일하게 생성됨)
@Table(name = "users") // table어노테이션은 이름 설정 가능
public class User {
	
	@Id // id가 기본키라는 어노테이션
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 일련번호,시퀀스번호 생성
	private int id;
	
	@Column(nullable = false, length = 50, unique = true)
	private String username;
	
	@Column(length = 100)
	private String password;
	
	@Column(nullable = false, length = 100)
	private String email;
	
	@Enumerated(EnumType.STRING) // enum에는 문자열 들어감
	private RoleType role; // 두번째꺼
	
	@CreationTimestamp // 새로운 레코드 추가시 자동으로 작동되는 시간이 추가됨
	private Timestamp createDate;
}
