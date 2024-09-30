package com.example.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// @Data // getter,setter,tostring 등 포함
public class User {
	private int id; // 회원번호
	private String username; // 회원 아이디
	private String password; // 회원 비밀번호
	private String email; // 회원 이메일
}
