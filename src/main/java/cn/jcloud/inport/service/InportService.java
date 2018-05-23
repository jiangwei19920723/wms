package cn.jcloud.inport.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jcloud.goods.domain.Goods;
import cn.jcloud.goods.repository.GoodsRepository;
import cn.jcloud.inport.domain.Inport;
import cn.jcloud.inport.repository.InportRepository;
import cn.jcloud.sell.domain.Sell;
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
	public String[] getGroupByGoodsName(String goodsName,String time){
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
		return repository.findGroupByGoodsName(date,calendar.getTime(),goodsName);
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
