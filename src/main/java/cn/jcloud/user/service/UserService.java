package cn.jcloud.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jcloud.service.BizService;
import cn.jcloud.user.domain.User;
import cn.jcloud.user.repository.UserRepository;

/**
* @author 蒋维
* @date 创建时间：2018年5月3日 上午10:17:25
* @version 1.0
*/
@Service
public class UserService extends BizService<User, Long> {
	@Autowired
	private UserRepository repository;
	
	public User login(String loginName,String password){
		return repository.findByLoginNameAndPassword(loginName, password);
	}
}
