package cn.jcloud.inport.service;

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
import cn.jcloud.inport.domain.Inport;
import cn.jcloud.inport.repository.InportRepository;
import cn.jcloud.service.BizService;

/**
* @author 蒋维
* @date 创建时间：2018年5月3日 上午11:34:40
* @version 1.0
*/
@Service
public class InportService extends BizService<Inport, Long> {
	@Autowired
	private InportRepository repository;
	@Autowired
	private GoodsRepository goodsRepository;
	
	public List<Inport> getAll(){
		return repository.findAll();
	}
	public List<Inport> getSelect(String startTime, String endTime, String goodsCode, String goodsName) throws ParseException{
		if (startTime !=null && endTime !=null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return repository.findByCreateTimeBetween(format.parse(startTime+" 00:00:00"), format.parse(endTime+" 23:59:59"));
		}else if (goodsCode !=null) {
			return repository.findByGoodsCodeLike("%"+goodsCode+"%");
		}else if (goodsName !=null) {
			return repository.findByGoodsNameLike("%"+goodsName+"%");
		}else {
			return repository.findAll();
		}
	}
	public void deleteById(Long id){
		Inport inport = repository.findOne(id);
		Goods goods = goodsRepository.findByGoodsName(inport.getGoodsName());
		if (goods.getGoodsNumber()<inport.getInportNumber()) {
			throw new RuntimeException();
		}
		goods.setGoodsNumber(goods.getGoodsNumber()-inport.getInportNumber());
		goodsRepository.save(goods);
		repository.deleteById(id);
	}
	public List<Inport> getByGoodsName(String goodsName){
		return repository.findByGoodsName(goodsName);
	}
	@Override
	public Inport save(Inport t) {
		Goods goods = goodsRepository.findByGoodsName(t.getGoodsName());
		goods.setGoodsNumber(goods.getGoodsNumber()+t.getInportNumber());
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
		List<Inport> inports = repository.findByCreateTimeBetween(date,calendar.getTime());
		List<Goods> goodss = goodsRepository.findAll();
		List<InportSettlement> inportSettlements = new ArrayList<>();
		for (Goods goods : goodss) {
			InportSettlement settlement = new InportSettlement();
			settlement.setGoodsCode(goods.getGoodsCode());
			settlement.setGoodsName(goods.getGoodsName());
			settlement.setGoodsUnit(goods.getGoodsUnit());
			settlement.setInportNumber(0);
			settlement.setInportPrice(0d);
			for (Inport inport : inports) {
				if (!inport.getGoodsName().equals(goods.getGoodsName())) {
					continue;
				}
				settlement.setInportNumber(settlement.getInportNumber()+inport.getInportNumber());
				settlement.setInportPrice(settlement.getInportPrice()+inport.getInportPrice());
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
