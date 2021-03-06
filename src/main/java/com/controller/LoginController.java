package com.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	/**
	 * 跳转登录页面
	 * @param role
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("to_login")
	public String to_login(
			@RequestParam("role") String role,
			HttpServletRequest request,
			HttpSession session,
			Model model
			){
		/*System.out.println(request.getHeader("Referer"));*/
		if(request.getHeader("Referer").contains("show")){
			session.setAttribute("url", request.getHeader("Referer"));
		}
		if(role.equals("user")){
			model.addAttribute("role", "user");
		}else if(role.equals("admin")){
			model.addAttribute("role", "admin");
		}
		return "login";		
	}
	/**
	 * 注销
	 * @param session
	 * @param response
	 * @param role
	 * @throws IOException
	 */
	@RequestMapping("login_out")
	public String login_out(
			HttpSession session,
			@RequestParam("role") String role
			) throws IOException{

		if(role.equals("user")){
			session.removeAttribute("user");
		}else if(role.equals("admin")){
			session.removeAttribute("admin");
		}
		return "redirect:show.html";
	}
}
