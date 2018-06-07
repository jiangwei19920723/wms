package cn.jcloud.sell.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.jcloud.repository.BizRepository;
import cn.jcloud.sell.domain.SellRise;

/**
* @author 蒋维
* @date 创建时间：2018年6月5日 下午2:48:26
* @version 1.0
*/
@Repository
public interface SellRiseRepository  extends BizRepository<SellRise, Long> {
	List<SellRise> findByCreateTimeBetween(Date startTime,Date endTime);
	List<SellRise> findBySellByLike(String sellBy);
	List<SellRise> findAll();
}
