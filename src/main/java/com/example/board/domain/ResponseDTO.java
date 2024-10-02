package com.example.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {
	
	// 응답결과 상태코드
	private int status;
	// 응답 데이터
	private T data;
}


// 제네릭 클래스 - 객체생성시 넣어주는 제네릭에 따라 T가 변경됨
// ResponseDTO<user> res = new ResponseDTO<>();