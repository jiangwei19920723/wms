package cn.jcloud.sellreturn.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jcloud.common.bean.InportSettlement;
import cn.jcloud.goods.domain.Goods;
import cn.jcloud.goods.repository.GoodsRepository;
import cn.jcloud.sellreturn.domain.SellReturn;
import cn.jcloud.sellreturn.repository.SellReturnRepository;
import cn.jcloud.service.BizService;

/**
* @author 蒋维
* @date 创建时间：2018年5月18日 下午2:02:47
* @version 1.0
*/
@Service
public class SellReturnService extends BizService<SellReturn, Long> {
	@Autowired
	private SellReturnRepository repository;
	@Autowired
	private GoodsRepository goodsRepository;
	public List<SellReturn> getAll(){
		return repository.findAll();
	}
	public List<SellReturn> getSelect(String startTime, String endTime, String goodsCode, String goodsName,String sellReturnBy) throws ParseException{
		if (startTime !=null && endTime !=null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return repository.findByCreateTimeBetween(format.parse(startTime+" 00:00:00"), format.parse(endTime+" 23:59:59"));
		}else if (goodsCode !=null) {
			return repository.findByGoodsCodeLike("%"+goodsCode+"%");
		}else if (goodsName !=null) {
			return repository.findByGoodsNameLike("%"+goodsName+"%");
		}else if (sellReturnBy !=null) {
			return repository.findBySellReturnByLike("%"+sellReturnBy+"%");
		}else {
			return repository.findAll();
		}
	}
	public void deleteById(Long id){
		SellReturn sellReturn = repository.findOne(id);
		Goods goods = goodsRepository.findByGoodsName(sellReturn.getGoodsName());
		if (goods.getGoodsNumber()<sellReturn.getSellReturnNumber()) {
			throw new RuntimeException();
		}
		goods.setGoodsNumber(goods.getGoodsNumber()-sellReturn.getSellReturnNumber());
		goodsRepository.save(goods);
		repository.deleteById(id);
	}
	public List<SellReturn> getByGoodsName(String goodsName){
		return repository.findByGoodsName(goodsName);
	}
	@Override
	public SellReturn save(SellReturn t) {
		Goods goods = goodsRepository.findByGoodsName(t.getGoodsName());
		goods.setGoodsNumber(goods.getGoodsNumber()+t.getSellReturnNumber());
		goodsRepository.save(goods);
		return super.save(t);
	}
	public List<InportSettlement> getGroup(String time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date=null;
		try {
			date = sdf.parse(time+"-01 00:00:00");
		} catch (ParseException e) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendar.MONTH, 1);
		List<SellReturn> sellReturns = repository.findByCreateTimeBetween(date,calendar.getTime());
		List<Goods> goodss = goodsRepository.findAll();
		List<InportSettlement> inportSettlements = new ArrayList<>();
		for (Goods goods : goodss) {
			InportSettlement settlement = new InportSettlement();
			settlement.setGoodsCode(goods.getGoodsCode());
			settlement.setGoodsName(goods.getGoodsName());
			settlement.setGoodsUnit(goods.getGoodsUnit());
			settlement.setInportNumber(0);
			settlement.setInportPrice(0d);
			for (SellReturn inport : sellReturns) {
				if (!inport.getGoodsName().equals(goods.getGoodsName())) {
					continue;
				}
				settlement.setInportNumber(settlement.getInportNumber()+inport.getSellReturnNumber());
				settlement.setInportPrice(settlement.getInportPrice()+inport.getSellReturnPrice());
			}
			if (settlement.getInportNumber() != 0) {
				inportSettlements.add(settlement);				
			}
		}
		Double number = repository.findByMonth(date,calendar.getTime());
		if (number != null) {
			InportSettlement settlement = new InportSettlement();
			settlement.setGoodsUnit("总计：");
			settlement.setInportPrice(number);
			inportSettlements.add(settlement);
		}
		return inportSettlements;
	}
	public Double getByMonth(String time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date=null;
		try {
			date = sdf.parse(time+"-01 00:00:00");
		} catch (ParseException e) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendar.MONTH, 1);
		return repository.findByMonth(date,calendar.getTime());
	}
}
