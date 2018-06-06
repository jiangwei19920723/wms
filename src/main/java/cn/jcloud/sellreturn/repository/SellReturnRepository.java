package cn.jcloud.sellreturn.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.jcloud.repository.BizRepository;
import cn.jcloud.sellreturn.domain.SellReturn;

/**
* @author 蒋维
* @date 创建时间：2018年5月18日 下午2:02:21
* @version 1.0
*/
@Repository
public interface SellReturnRepository extends BizRepository<SellReturn, Long> {
	List<SellReturn> findAll();
	List<SellReturn> findBySellReturnByLike(String sellReturnBy);
	List<SellReturn> findByCreateTimeBetween(Date startTime,Date endTime);
	List<SellReturn> findByGoodsCodeLike(String goodsCode);
	List<SellReturn> findByGoodsNameLike(String goodsName);
	List<SellReturn> findByGoodsName(String goodsName);
	@Query(value = "select SUM(sell_return_number),SUM(sell_return_price) from wms_sell_return where create_time>=:startTime and create_time<:endTime and goods_name=:goodsName group by goods_name", nativeQuery = true)
	String[] findGroupByGoodsName(@Param("startTime") Date startDate,@Param("endTime") Date endDate,@Param("goodsName") String goodsName);
	@Query(value = "select SUM(sell_return_price) from wms_sell_return where create_time>=:startTime and create_time<:endTime", nativeQuery = true)
	Double findByMonth(@Param("startTime") Date startDate,@Param("endTime") Date endDate);
	void deleteById(Long id);
}
