package com.mycompany.springframework.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.mycompany.springframework.dto.Ch08CartItem;
import com.mycompany.springframework.dto.Ch08Product;

import lombok.extern.slf4j.Slf4j;

@Controller // 사용자의 요청을 처리하고 난 후 정해진 뷰에 객체를 넘겨주는 역할
@Slf4j
@RequestMapping("/ch08")
public class Ch08Controller {
	@RequestMapping("/productList")
	public String productList(Model model) {
		// 상품 데이터 생성
		List<Ch08Product> productList = new ArrayList<>();
		for(int i=1; i<=5; i++) {
			Ch08Product product = new Ch08Product(i, "상품"+i, i * 10000);
			productList.add(product);
		}
		//request 범위에 저장
		model.addAttribute("productList", productList);
		model.addAttribute("chNum", "ch08");
		return "ch08/productList";
	}
	
	@RequestMapping("/cartView")
	   public String cartView(HttpSession session, Model model) {
	      //장바구니를 세션에서 가져오기
	      List<Ch08CartItem> cart = (List<Ch08CartItem>)session.getAttribute("cart");
	      //세션에 장바구니가 존재하지 않을 시 새로 장바구니를 생성해서 session 범위에 저장
	      if(cart == null) {
	         cart = new ArrayList<Ch08CartItem>();
	         session.setAttribute("cart", cart);
	      }
	      
	      model.addAttribute("chNum", "ch08");
	      return "ch08/cartView";
	   }
	
	@RequestMapping("/detailView")
	public String detailView(int pno, Model model) {
		// 상품 상세 정보 얻기
		Ch08Product product = new Ch08Product(pno, "상품"+pno, pno*10000);
		// request 범위에 저장
		model.addAttribute("product", product);
		model.addAttribute("chNum", "ch08");		
		return "ch08/detailView";
	}
	
	@RequestMapping("/addCartItem")
	   public String addCartItems(int pno, int amount, HttpSession session) {
	      //장바구니를 세션에서 가져오기
	      List<Ch08CartItem> cart = (List<Ch08CartItem>)session.getAttribute("cart");
	      
	      //세션에 장바구니가 존재하지 않을 시 새로 장바구니를 생성해서 session 범위에 저장
	      if(cart == null) {
	         cart = new ArrayList<Ch08CartItem>();
	         session.setAttribute("cart", cart);
	      }
	      
	      boolean isAmountUpdated = false;
	      
	      for(Ch08CartItem cartItem : cart) {
	         if(cartItem.getProduct().getPno() == pno) {
	            cartItem.setAmount(cartItem.getAmount() + amount);
	            isAmountUpdated = true;
	         }
	      }
	      
	      if(!isAmountUpdated) {
	         //상품 상세정보 얻기
	         Ch08Product product = new Ch08Product(pno, "상품"+pno, pno*10000);
	         //장바구니 아이템 생성
	         Ch08CartItem cartItem = new Ch08CartItem();
	         cartItem.setProduct(product);
	         cartItem.setAmount(amount);
	         
	         //장바구니에 아이템을 추가
	         cart.add(cartItem);
	      }
	      return "redirect:/ch08/cartView";
	   }
	//--------------------
	// AJAX 사용
	@RequestMapping(value="/updateCartItem", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String updateCartItem(int pno, int amount, @SessionAttribute List<Ch08CartItem> cart) {
		// pno와 같은 CartItem 찾기
		for(Ch08CartItem item: cart) {
			if(item.getProduct().getPno() == pno) {
				// CartItem의 amount를 수정
				item.setAmount(amount);
			}
		}
		
		// 수정 결과 응답 생성
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		return jsonObject.toString();
	}
	
	@RequestMapping("/removeCartItem") // 요청url과 메소드 매핑시킴
	public String removeCartItem(int pno, @SessionAttribute List<Ch08CartItem> cart) {
		// pno와 같은 CartItem 찾기
		Iterator<Ch08CartItem> iterator = cart.iterator();
		while(iterator.hasNext()) {
			Ch08CartItem cartItem = iterator.next();
			if(cartItem.getProduct().getPno() == pno) {
				iterator.remove();
			}
		}
		
		// CartItem 제거
		return "redirect:/ch08/cartView";
	}
	
	
	
}	
