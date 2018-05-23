package cn.jcloud.user.repository;

import org.springframework.stereotype.Repository;

import cn.jcloud.repository.BizRepository;
import cn.jcloud.user.domain.User;

/**
* @author 蒋维
* @date 创建时间：2018年5月3日 上午10:15:37
* @version 1.0
*/
@Repository
public interface UserRepository extends BizRepository<User, Long> {
	User findByLoginNameAndPassword(String loginName,String password);
}
