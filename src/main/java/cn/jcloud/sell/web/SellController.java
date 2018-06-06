package cn.jcloud.sell.web;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.jcloud.common.bean.SellVo;
import cn.jcloud.sell.domain.Sell;
import cn.jcloud.sell.domain.SellRise;
import cn.jcloud.sell.service.SellRiseService;
import cn.jcloud.sell.service.SellService;

/**
* @author 蒋维
* @date 创建时间：2018年5月3日 上午11:46:39
* @version 1.0
*/
@RestController
@RequestMapping("/v0.1/sell")
public class SellController {
	@Autowired
	private SellService service;
	@Autowired
	private SellRiseService riseService;
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public void insert(@RequestBody @Valid SellVo sell){
		riseService.save(sell);
	}
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public List<SellRise> listRise(){
		return riseService.getAll();
	}
	@RequestMapping(value = "list/{id}", method = RequestMethod.GET)
	public List<Sell> list(@PathVariable("id") Long id){
		return service.getBySellRiseId(id);
	}
	@RequestMapping(value = "select/group", method = RequestMethod.GET)
	public String[] selectGroupByGoodsName(String goodsName,String time){
		return service.getGroupByGoodsName(goodsName,time);
	}
	@RequestMapping(value = "select/month", method = RequestMethod.GET)
	public Double selectpByMonth(String time){
		return service.getByMonth(time);
	}
	@RequestMapping(value = "select/{goodsName}", method = RequestMethod.GET)
	public boolean selectByGoodsName(@PathVariable("goodsName") String goodsName){
		List<Sell> sells = service.getByGoodsName(goodsName);
		if (sells.size() == 0) {
			return false;
		}else {
			return true;
		}
	}
	@RequestMapping(value = "select", method = RequestMethod.GET)
	public List<SellRise> listCondition(String startTime, String endTime, String sellBy){
		try {
			return riseService.getSelect(startTime, endTime, sellBy);
		} catch (ParseException e) {
			return null;
		}
	}
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	public void deleteById(@PathVariable("id")Long id){
		riseService.deleteById(id);
	}
}
