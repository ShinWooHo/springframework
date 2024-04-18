package com.mycompany.springframework.dto;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch04SignUpFormValidator implements Validator {

	@Override
	// 
	public boolean supports(Class<?> clazz) {
		log.info("실행");
		return Ch04SignUpForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Ch04SignUpForm signUpForm = (Ch04SignUpForm) target;
		
		// 아이디 검사
		String id = signUpForm.getMid();
		if (id == null || id.equals("")) {
			errors.rejectValue("mid", null, "아이디는 반드시 입력해야 합니다.");
		} else if (id.length() < 6 || id.length() > 12) {
			errors.rejectValue("mid", null, "아이디는 6자이상, 12자 이하로 입력해야 합니다.");
		}

		// 비밀번호 검사
		String password = signUpForm.getMpassword();
		String pattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}$";
		if (password == null || password.equals("")) {
			errors.rejectValue("mpassword", null, "비밀번호는 반드시 입력해야 합니다.");
		} else if (password.length() < 8 || password.length() > 15) {
			errors.rejectValue("mpassword", null, "비밀번호는 8자이상, 15자 이하로 입력해야 합니다.");
		} else if (!Pattern.matches(pattern, password)) {
			errors.rejectValue("mpassword", null, "비밀번호는 아파벳 대소문자 및 숫자를 포함해야 합니다.");
		}
		
		// 이름 검사
		String name = signUpForm.getMname();
		pattern = "^[가-힣]{2,4}$";
		if(name == null || name.equals("")) {
			errors.rejectValue("mname", null, "이름을 입력해야 합니다.");
		} else if(name.length() < 2 || name.length() > 4) {
			errors.rejectValue("mname", null, "이름을 2자이상, 4자이하로 입력해야 합니다.");
		} else if(!Pattern.matches(pattern, name)) {
			errors.rejectValue("mname", null, "이름 형식에 알맞게 입력해 주세요.");
		}
		
		// 이메일 검사
		String email = signUpForm.getMemail();
		pattern = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-za-z0-9\\-]+$";
		if (email == null || email.equals("")) {
			errors.rejectValue("memail", null, "이메일는 반드시 입력해야 합니다.");
		} else if (!Pattern.matches(pattern, email)) {
			errors.rejectValue("memail", null, "이메일 형식에 알맞게 입력해 주세요.");
		}
		
		// 전화번호 검사
		String phoneNumber = signUpForm.getMphonenumber();
		pattern = "^(01[016789]{1})-?[0-9]{3,4}-?[0-9]{4}$";
		if (phoneNumber == null || phoneNumber.equals("")) {
			errors.rejectValue("mphonenumber", null, "전화번호를 반드시 입력해야 합니다.");
		} else if (!Pattern.matches(pattern, phoneNumber)) {
			errors.rejectValue("mphonenumber", null, "전화번호 형식에 알맞지 않습니다.");
		}
		
		
		
	}

}
