package com.example.board.advice;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.board.domain.ResponseDTO;

// 스프링 컨테이너에 추가하여 언제든 사용가능 하게 하기
@Component // 클래스를 bean으로 등록 (특정 메서드를 bean으로 등록할때는 @bean)
@Aspect // 특정 메서드가 진행되기전에 구현시키게 도와주는 어노테이션
public class ValidationCheckAdvice {
	
	// @around(경로(controller파일들.*메서드들(매개변수))
	@Around("execution(* com.example..controller.*Controller.*(..))")
	public Object validationCheck(ProceedingJoinPoint jp) throws Throwable{
		// jp는 보내진 매개변수를 모두 저장하고 이중 빼서 사용
		// 예외는 불러온곳에서 try catch사용 하도록 함
		Object[] args = jp.getArgs();
		
		for(Object arg : args) {
			if(arg instanceof BindingResult) {
				// object로 변환시 사라지는 메서드들이 있기 때문에 형변환
				BindingResult bindingResult = (BindingResult)arg;
				
				if(bindingResult.hasErrors()) {
					Map<String, String> errors = new HashMap<>();
					for(FieldError error : bindingResult.getFieldErrors()) {
						errors.put(error.getField(), error.getDefaultMessage());
					}
					return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), errors);
				}
			}
		}
		return jp.proceed(); // 해당하지 않는 메서드들이 정상 처리되도록
	}
}
