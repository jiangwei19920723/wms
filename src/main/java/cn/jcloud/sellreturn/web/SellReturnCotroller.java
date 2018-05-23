package cn.jcloud.sellreturn.web;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.jcloud.sellreturn.domain.SellReturn;
import cn.jcloud.sellreturn.service.SellReturnService;

/**
* @author 蒋维
* @date 创建时间：2018年5月18日 下午2:03:09
* @version 1.0
*/
@RestController
@RequestMapping("/v0.1/sellreturn")
public class SellReturnCotroller {
	@Autowired
	private SellReturnService service;
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public void insert(@RequestBody @Valid SellReturn sellReturn){
		sellReturn = service.save(sellReturn);
	}
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public List<SellReturn> list(){
		return service.getAll();
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
		List<SellReturn> sellReturns = service.getByGoodsName(goodsName);
		if (sellReturns.size() == 0) {
			return false;
		}else {
			return true;
		}
	}
	@RequestMapping(value = "select", method = RequestMethod.GET)
	public List<SellReturn> listCondition(String startTime, String endTime, String goodsCode, String goodsName){
		try {
			return service.getSelect(startTime, endTime, goodsCode, goodsName);
		} catch (ParseException e) {
			return null;
		}
	}
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	public void deleteById(@PathVariable("id")Long id){
		service.deleteById(id);
	}
}
