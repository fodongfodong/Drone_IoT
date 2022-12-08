package com.dw.drone.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dw.drone.service.MainService;

/*
 * 프론트를 이클립스에서 관리하면 Controller ex) JSP
 * 프론트가 분리되어 있으면 RestController ex) React, Angular ...
 */
//페이지 호출할 때는 Rest (X) Controller (O)
@Controller
public class MainController {

	@Autowired
	private MainService mainService;
	
	@GetMapping("/home")
	public String loadHomePage() {
		/*
		 * return 타입은 String
		 * html 페이지 이름을 리턴
		 */
		return "index";
	}
	
	@GetMapping("/")
	public String loadIndexPage() {
		return "login";
	}
	
	@GetMapping("/drone/detail/{uuid}")
	public String loadDetailPage(@PathVariable int uuid, ModelMap map) {
		//ModelMap은 주소로 넘어오는 @PathVariable or @RequestParam 데이터를 View(HTML)에 전달한다.
		map.addAttribute("droneUUID", uuid);
		return "detail";
	}
	
	
	@PostMapping("/valid-recaptcha")
	public @ResponseBody Boolean validRecaptcha(HttpServletRequest request){
		String recaptchaResponse = request.getParameter("g-recaptcha-response");
		boolean isRecaptcha = mainService.verifyRecaptcha(recaptchaResponse);
		return isRecaptcha;
	}
	
}