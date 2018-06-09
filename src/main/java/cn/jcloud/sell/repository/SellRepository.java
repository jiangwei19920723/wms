package cn.jcloud.sell.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.jcloud.repository.BizRepository;
import cn.jcloud.sell.domain.Sell;

/**
* @author 蒋维
* @date 创建时间：2018年5月3日 上午11:45:10
* @version 1.0
*/
@Repository
public interface SellRepository extends BizRepository<Sell, Long> {
	List<Sell> findAll();
	List<Sell> findBySellRiseId(Long id);
	List<Sell> findByCreateTimeBetween(Date startTime,Date endTime);
	List<Sell> findByGoodsCodeLike(String goodsCode);
	List<Sell> findByGoodsNameLike(String goodsName);
	List<Sell> findByGoodsName(String goodsName);
	@Query(value = "select SUM(sell_number),SUM(goods_price*sell_number) from wms_sell where create_time>=:startTime and create_time<:endTime and goods_name=:goodsName group by goods_name", nativeQuery = true)
	String[] findGroupByGoodsName(@Param("startTime") Date startDate,@Param("endTime") Date endDate,@Param("goodsName") String goodsName);
	@Query(value = "select SUM(goods_price) from wms_sell where create_time>=:startTime and create_time<:endTime", nativeQuery = true)
	Double findByMonth(@Param("startTime") Date startDate,@Param("endTime") Date endDate);
	void deleteById(Long id);
}
