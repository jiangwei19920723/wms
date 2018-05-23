package cn.jcloud.goods.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.jcloud.goods.domain.Goods;
import cn.jcloud.repository.BizRepository;

/**
* @author 蒋维
* @date 创建时间：2018年5月3日 上午10:26:05
* @version 1.0
*/
@Repository
public interface GoodsRepository extends BizRepository<Goods, Long> {
	List<Goods> findAll();
	List<Goods> findByCreateTimeBetween(Date startTime,Date endTime);
	List<Goods> findByGoodsCodeLike(String goodsCode);
	List<Goods> findByGoodsTypeLike(String goodsType);
	List<Goods> findByGoodsFactoryLike(String goodsFactory);
	List<Goods> findByGoodsNameLike(String goodsName);
	Goods findByGoodsName(String goodsName);
	void deleteById(Long id);
}
