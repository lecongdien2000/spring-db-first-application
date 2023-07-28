package com.example.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mappers.MySqlSessionFactory;
import com.example.mappers.UserMapper;
import com.example.models.User;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor

public class HelloController {

	
	HashMap<String, String> accounts = new HashMap<>();
	
	@ResponseBody
	@GetMapping("/hello")
	public String hello() {
		return "Hello World";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Model model, ModelAndView mav) {
		Boolean isFailedLogin = (Boolean) (model.asMap().get("isFailedLogin"));
		
		System.out.println(isFailedLogin);
		String companyName = "Wooden Memorials";
		mav.addObject("companyName", companyName);
		mav.setViewName("index");
		return mav;
	}
	
	@PostMapping("/login")
	public ModelAndView login(ModelAndView mav, RedirectAttributes redirectAttributes, HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password") String password) throws IOException {

		MySqlSessionFactory msf = new MySqlSessionFactory();
		SqlSessionFactory sessionFactory = msf.startSqlSession();
		User user = null;
		try(SqlSession session = sessionFactory.openSession()){
			UserMapper userMapper = session.getMapper(UserMapper.class);
			user = userMapper.getUserByUsernameAndPassword(username, password);
		}
		
		if(user!=null) {
			redirectAttributes.addFlashAttribute("username", username);
			return this.redirectToUrl("/successful-login", mav);
		}
		
		redirectAttributes.addFlashAttribute("isFailedLogin", true);
		return this.redirectToUrl("/", mav);
		
	}

	
	@GetMapping("/successful-login")
	public String login() {
		System.out.println("hello");
		return "successful-login";
		
	}
	
	private ModelAndView redirectToUrl(String url, ModelAndView mav) {
		mav.setViewName(String.format("redirect:%s", url));
		return mav;
	}
	
	
}
