package cn.jcloud.inport.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.jcloud.inport.domain.Inport;
import cn.jcloud.repository.BizRepository;

/**
 * @author 蒋维
 * @date 创建时间：2018年5月3日 上午11:31:25
 * @version 1.0
 */
@Repository
public interface InportRepository extends BizRepository<Inport, Long> {
	List<Inport> findAll();

	List<Inport> findByCreateTimeBetween(Date startTime, Date endTime);

	List<Inport> findByGoodsCodeLike(String goodsCode);

	List<Inport> findByGoodsNameLike(String goodsName);

	List<Inport> findByGoodsName(String goodsName);

	@Query(value = "select SUM(inport_number),SUM(inport_price) from wms_inport where create_time>=:startTime and create_time<:endTime and goods_name=:goodsName group by goods_name", nativeQuery = true)
	String[] findGroupByGoodsName(@Param("startTime") Date startDate,@Param("endTime") Date endDate,@Param("goodsName") String goodsName);
	@Query(value = "select SUM(inport_price) from wms_inport where create_time>=:startTime and create_time<:endTime", nativeQuery = true)
	Double findByMonth(@Param("startTime") Date startDate,@Param("endTime") Date endDate);
	void deleteById(Long id);
}
