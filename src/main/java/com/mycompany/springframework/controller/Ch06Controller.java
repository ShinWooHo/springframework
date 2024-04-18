package com.mycompany.springframework.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springframework.dto.Ch06Cart;
import com.mycompany.springframework.dto.Ch06Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch06")
public class Ch06Controller {
	@GetMapping("/forward")
	public String forward(Model model, HttpServletRequest request) { //HttpServletRequest 다시 알아보기
		// 데이터 생성
		Ch06Member member = new Ch06Member();
		member.setMid("spring");
		member.setMname("김벚꽃");
		member.setMemail("kim@naver.com");
		
		// request 범위에 객체 저장 (응답이 가기전까지의 범위를 request 범위라고 함)
		// (forward할 때까지 계속 사용할 수 있는 범위)
		// 응답이 전송되면 request 범위는 사라짐 
		model.addAttribute("chNum", "ch06");
		model.addAttribute("member1", member); // 응답이 가고나면 해당 데이터 정보는 사라진다. request 범위의 객체 저장
		request.setAttribute("member2", member); // request 범위의 객체 저장
		
		return "ch06/forward";
	}
	
	@GetMapping("/redirect")
	public String redirect(HttpSession session) {
		log.info("실행");
		
		// 데이터 생성
		List<String> items = new ArrayList<>();
		items.add("상품1");
		items.add("상품2");
		items.add("상품3");
		Ch06Cart cart = new Ch06Cart(); // cart 객체를 생성
		cart.setItems(items);
		
		// session 범위에 객체를 저장
		// 동일한 client(브라우저)가 사용할 수 있는 범위
		session.setAttribute("cart", cart);
		
		
		return "redirect:/ch06/sessionData"; // redirect를 만나 redirect를 실행 후 아래 sessionData경로로 이동하여 실행시킴
	}
	
	@GetMapping("/sessionData")
	public String sessionData(HttpSession session, Model model) {
		log.info("실행");
		
		// session 범위에 저장된 객체 가져오기
		Ch06Cart cart = (Ch06Cart) session.getAttribute("cart"); // cart라는 명으로 저장된 값을 가져옴
		if(cart != null) {
			// 얻어온 상품을 출력
			for(String item : cart.getItems()) {
				log.info(item);
			}
		}
		model.addAttribute("chNum", "ch06");
		return "ch06/sessionData"; 
	}
}
