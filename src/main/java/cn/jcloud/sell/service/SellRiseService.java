package cn.jcloud.sell.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jcloud.common.bean.SellVo;
import cn.jcloud.goods.domain.Goods;
import cn.jcloud.goods.repository.GoodsRepository;
import cn.jcloud.sell.domain.Sell;
import cn.jcloud.sell.domain.SellRise;
import cn.jcloud.sell.repository.SellRepository;
import cn.jcloud.sell.repository.SellRiseRepository;
import cn.jcloud.service.BizService;

/**
* @author 蒋维
* @date 创建时间：2018年6月5日 下午2:50:12
* @version 1.0
*/
@Service
public class SellRiseService  extends BizService<SellRise, Long>  {
	@Autowired
	private SellRiseRepository repository;
	@Autowired
	private SellRepository sellRepository;
	@Autowired
	private GoodsRepository goodsRepository;
	public List<SellRise> getAll(){
		return repository.findAll();
	}
	public List<SellRise> getSelect(String startTime, String endTime ,String sellBy) throws ParseException{
		if (startTime !=null && endTime !=null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return repository.findByCreateTimeBetween(format.parse(startTime+" 00:00:00"), format.parse(endTime+" 23:59:59"));
		}else if (sellBy !=null) {
			return repository.findBySellByLike("%"+sellBy+"%");
		}else {
			return repository.findAll();
		}
	}
	public void save(SellVo sell) {
		SellRise rise = new SellRise();
		rise.setCreateBy(sell.getCreateBy());
		rise.setSellBy(sell.getSellBy());
		rise.setPhone(sell.getPhone());
		rise.setSellPrice(sell.getSellPrice());
		rise.setRemark(sell.getRemark());
		rise = repository.save(rise);
		for (String good : sell.getGoods()) {
			String[] strs = good.split(",");
			Goods goods = goodsRepository.findByGoodsName(strs[0]);
			Sell sell2 = new Sell();
			sell2.setSellRiseId(rise.getId());
			sell2.setGoodsName(goods.getGoodsName());
			sell2.setGoodsCode(goods.getGoodsCode());
			sell2.setGoodsUnit(goods.getGoodsUnit());
			sell2.setGoodsType(goods.getGoodsType());
			sell2.setGoodsFactory(goods.getGoodsFactory());
			sell2.setSellNumber(Integer.parseInt(strs[1]));
			sell2.setGoodsPrice(Double.parseDouble(strs[2]));
			if (goods.getGoodsNumber()<sell2.getSellNumber()) {
				throw new RuntimeException();
			}
			goods.setGoodsNumber(goods.getGoodsNumber()-sell2.getSellNumber());
			goodsRepository.save(goods);
			sellRepository.save(sell2);
		}
	}
	public void deleteById(Long id){
		SellRise sellRise = repository.findOne(id);
		List<Sell> list = sellRepository.findBySellRiseId(id);
		for (Sell sell : list) {
			Goods goods = goodsRepository.findByGoodsName(sell.getGoodsName());
			goods.setGoodsNumber(goods.getGoodsNumber()+sell.getSellNumber());
			goodsRepository.save(goods);
			sellRepository.delete(sell);
		}
		repository.delete(sellRise);
	}
}
