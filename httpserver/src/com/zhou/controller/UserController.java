package com.zhou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zhou.pojo.Result;
import com.zhou.pojo.User;
import com.zhou.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/showLogin",method=RequestMethod.GET)
	public String showLogin() {
		return "login";
	}
	
	//用户登陆
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Result login(User user) {
		Result result = userService.login(user);
		return result;
	}
	
	//使用Restful风格删除用户
	@RequestMapping(value="/user/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Result login(@PathVariable Integer id) {
		Result result = userService.delete(id);
		return result;
	}
}
