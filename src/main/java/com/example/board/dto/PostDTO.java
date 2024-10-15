package com.example.board.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
	
	@NotNull(message="title은 null이면 안됩니다.")
	@NotBlank(message="title은 반드시 입력해야 합니다.")
	@Size(max =200, message="200글자 내로 입력해야 합니다.")
	private String title;
	
	@NotNull(message="content는 null이면 안됩니다.")
	@NotBlank(message = "content는 반드시 입력해야합니다.")
	private String content;
}
