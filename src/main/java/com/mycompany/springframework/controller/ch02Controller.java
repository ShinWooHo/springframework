package com.mycompany.springframework.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.springframework.dto.ch02FileInfo;
import com.mycompany.springframework.interceptor.Auth;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/ch02")
@Slf4j
public class ch02Controller {
	@GetMapping("/getMethod") //get 방식만 요청 처리 함 
	public String getMethod(String bkind, String bno, Model model) {
		// 요청 처리 코드
		log.info("getMethod() 실행");
		log.info("bkind: " + bkind);
		log.info("bno: " + bno);

		model.addAttribute("chNum", "ch02");
		return "ch02/getMethod";

	}
 // 기본값 1개만 존재할 경우 value 필요 x 기본값외 여러 속성들이 있을 경우 value를 반드시 붙여준다. 
	@RequestMapping(value="/getMethodAjax", method=RequestMethod.GET)
	public String getMethodAjax(String bkind, String bno, Model model) {
		// 요청 처리 코드
		log.info("getMethodAjax() 실행");
		log.info("bkind: " + bkind);
		log.info("bno: " + bno);
		model.addAttribute("chNum", "ch02");
		return "ch02/getMethodAjax";

	}

	@RequestMapping("/postMethod")
	public String postMethod(String mid, String mpassword, Model model) {
		// 요청 처리 코드
		log.info("postMethod() 실행");
		log.info("mid: " + mid);
		log.info("mpassword: " + mpassword);
		model.addAttribute("chNum", "ch02");
		return "ch02/postMethod";

	}

	@PostMapping("/postMethodAjax")
	   public void postMethodAjax(String mid, String mpassword, HttpServletResponse response) throws IOException {
	      //요청 처리 코드
		
	      log.info("postMethodAjax() 실행");
	      log.info("mid: " + mid);
	      log.info("mpassword: " + mpassword);
	      
	      Map<String, String> map = new HashMap<>();
	      map.put("spring", "12345");
	      map.put("summer", "67890");
	      
	      JSONObject result = new JSONObject();
	      
	      if(map.containsKey(mid)) {
	         if(map.get(mid).equals(mpassword)) {
	            //로그인 성공
	            result.put("result", "success");
	         } else {
	            //비빌번호가 틀린 경우
	        	result.put("result", "fail");
	        	result.put("result", "wrongMpassword");
	         }
	      } else {
	         //아이디가 존재하지 않는 경우
	    	 result.put("result", "fail");
	    	 result.put("result", "wrongMid");
	      }
	      
	      //직접 응답을 생성해서 브라우저로 보냄 json = 문자열 ,writer로 얻음
	      PrintWriter pw = response.getWriter();
	      response.setContentType("application/json; charset=UTF-8");
	      pw.println(result.toString());
	      pw.flush();
	      pw.close();
	   	  }
	
	// a태그는 Get방식
	@GetMapping("/modelAndViewReturn")
	public ModelAndView modelAndViewReturn(String chNum) {
		log.info("modelAndViewReturn() 실행");
		
		// data와 jsp를 가지고 있는 객체
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("chNum", "ch02");
		modelAndView.addObject("login", true);
		modelAndView.addObject("userName", "김자바");
		modelAndView.setViewName("ch02/modelAndViewReturn");
		return modelAndView; 
	}
	
	@GetMapping("/voidReturn")
	public void imageDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//다운로드할 이미지 파일 선택 
		String fileName = "photo1.jpg";
		// 실제 파일의 경로를 얻기
		String urlfilePath = "resources/image/photos/photo1.jpg";
		// 파일 시스템이 어디에 저장되었는지 알 수 있음
		String fileSystemPath = request.getServletContext().getRealPath(urlfilePath);
		log.info("fileSystemPath:" + fileSystemPath);
		// 파일의 MIME 타입 얻기
		String mimeType = request.getServletContext().getMimeType(fileSystemPath);
		log.info("mimeType:" + mimeType);
		
		// 파일 데이터를 읽는 입력 스트림을 얻기
		InputStream is = new FileInputStream(fileSystemPath);
		
		// 응답 본문에 출력하는 출력 스트림을 얻기
		OutputStream os = response.getOutputStream();
		
		// 응답 헤더에 Content-Type 값을 지정(image/jpeg)
		response.setContentType(mimeType);
		
		// 응답 본문에 데이터 싣기
		// 입력 스트림에서 데이터를 읽고 -> 출력 스트림으로 데이터를 쓰기
		
		// 방법1
		/*byte[] bytes = new byte[1024];
		while(true) {
			int readNum = is.read(bytes);
			if(readNum == -1) break;
			os.write(bytes, 0, readNum);
		}*/
		
		// 방법2 Spring이 제공하는 유틸 클래스 (메소드)
		FileCopyUtils.copy(is, os);
		
		os.flush();
		is.close();
		os.close();
	}
	
	@GetMapping("/objectReturn")
	public String objectReturn(Model model) {
		model.addAttribute("chNum", "ch02");
		return "ch02/objectReturn";
	}
	
	@GetMapping(value="/objectReturnJson1", 
				produces="application/json; charset=UTF-8")
	@ResponseBody // 리턴된 객체를 JSON으로 해석하고 응답 본문에 넣겠다.
	public String objectReturnJson1() {
		log.info("objectReturnJson1() 실행");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("fileName", "photo1.jpg");
		//return {"fileName":photo1.jpg"}
		return jsonObject.toString();
	}
	
	@GetMapping(value="/objectReturnJson2", 
				produces="application/json; charset=UTF-8")
	@ResponseBody // 리턴된 객체를 JSON으로 변환하고 응답 본문에 넣겠다.
	public ch02FileInfo objectReturnJson2() {
		log.info("objectReturnJson2() 실행");
		ch02FileInfo fileInfo = new ch02FileInfo();
		fileInfo.setFileName("photo2.jpg");
		fileInfo.setInfo("아름다운 풍경 사진");
		return fileInfo;
	}
	
	@GetMapping("/testAuthInterceptor1")
	public String testAuthInterceptor1(Model model) {
		log.info("testAuthInterceptor1() 실행");
		model.addAttribute("chNum", "ch02");
		return "ch02/testAuthInterceptor1";
	}
	
	@GetMapping("/testAuthInterceptor2")
	@Auth
	public String testAuthInterceptor2(Model model) {
		log.info("testAuthInterceptor2() 실행");
		model.addAttribute("chNum", "ch02");
		return "ch02/testAuthInterceptor2";
	}
	

}
