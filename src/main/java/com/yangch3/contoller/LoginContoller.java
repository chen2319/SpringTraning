package com.yangch3.contoller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yangch3.data.User;
import com.yangch3.service.UserService;

@Controller
public class LoginContoller {

	@Resource
	UserService userService;
	
	@RequestMapping("/home")
    public String home() {
        return "login";
    }
	
	@RequestMapping("/login")
    public String login(String id, String password, Model model, HttpServletRequest request) {
		User user = userService.login(id, password);
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUsername", user);
			model.addAttribute("loginUsername", user);
			model.addAttribute("users", userService.getAllUsers());
			return "hello";
		}
		model.addAttribute("loginError", true);
		return "login";
		
    }
	
	@RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public String getUserInfo(@PathVariable String id, Model model) {
    	User user = userService.getUserById(id);
    	if (user == null) {
    		model.addAttribute("userid", id);
    		return "error";
    	}
    	model.addAttribute("user", user);
        return "userinfo";
    }
	
	@RequestMapping(path = "/user/{id}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable String id, Model model, HttpServletRequest request) {
    	userService.deleteUserById(id);
    	HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUsername");
    	model.addAttribute("users", userService.getAllUsers());
    	model.addAttribute("loginUsername", user);
    	return "hello";
    }
}
