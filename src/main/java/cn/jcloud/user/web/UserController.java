package cn.jcloud.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.jcloud.user.domain.User;
import cn.jcloud.user.service.UserService;

/**
* @author 蒋维
* @date 创建时间：2018年5月3日 上午10:19:09
* @version 1.0
*/
@RestController
@RequestMapping("/v0.1/user")
public class UserController {
	@Autowired
	private UserService service;
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public User login(@RequestBody User user){
		user = service.login(user.getLoginName(), user.getPassword());
		if (user == null) {
			throw new RuntimeException("用户名或密码错误");
		}
		return user;
	}

}
