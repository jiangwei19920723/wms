package cn.jcloud.goods.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jcloud.goods.domain.Goods;
import cn.jcloud.goods.repository.GoodsRepository;
import cn.jcloud.service.BizService;

/**
* @author 蒋维
* @date 创建时间：2018年5月3日 上午10:26:47
* @version 1.0
*/
@Service
public class GoodsService extends BizService<Goods, Long> {
	@Autowired
	private GoodsRepository repository;
	public List<Goods> getAll(){
		return repository.findAll();
	}

	public List<Goods> getSelect(String startTime, String endTime, String goodsCode, String goodsName, String goodsType, String goodsFactory) throws ParseException{
		if (startTime !=null && endTime !=null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return repository.findByCreateTimeBetween(format.parse(startTime+" 00:00:00"), format.parse(endTime+" 23:59:59"));
		}else if (goodsCode !=null) {
			return repository.findByGoodsCodeLike("%"+goodsCode+"%");
		}else if (goodsName !=null) {
			return repository.findByGoodsNameLike("%"+goodsName+"%");
		}else if (goodsType !=null) {
			return repository.findByGoodsTypeLike("%"+goodsType+"%");			
		}else if (goodsFactory !=null) {
			return repository.findByGoodsFactoryLike("%"+goodsFactory+"%");	
		}else {
			return repository.findAll();
		}
	}
	public void deleteById(Long id){
		repository.deleteById(id);
	}
	public void insert(Goods goods){
		repository.save(goods);
	}
	public Goods getByGoodsName(String goodsName){
		return repository.findByGoodsName(goodsName);
	}
	@Override
	public Goods save(Goods t) {
		t.setGoodsNumber(0);
		return super.save(t);
	}
}
