package cn.jcloud.inport.web;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.jcloud.common.bean.InportSettlement;
import cn.jcloud.inport.domain.Inport;
import cn.jcloud.inport.service.InportService;

/**
* @author 蒋维
* @date 创建时间：2018年5月3日 上午11:35:40
* @version 1.0
*/
@RestController
@RequestMapping("/v0.1/inport")
public class InportController {
	@Autowired
	private InportService service;
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public void insert(@RequestBody @Valid Inport inport){
		service.save(inport);
	}
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public List<Inport> list(){
		return service.getAll();
	}
	@RequestMapping(value = "select/group", method = RequestMethod.GET)
	public List<InportSettlement> selectGroup(String time){
		return service.getGroup(time);
	}
	@RequestMapping(value = "select/month", method = RequestMethod.GET)
	public Double selectpByMonth(String time){
		return service.getByMonth(time);
	}
	@RequestMapping(value = "select/{goodsName}", method = RequestMethod.GET)
	public boolean selectByGoodsName(@PathVariable("goodsName") String goodsName){
		List<Inport> inports = service.getByGoodsName(goodsName);
		if (inports.size() == 0) {
			return false;
		}else {
			return true;
		}
	}
	@RequestMapping(value = "select", method = RequestMethod.GET)
	public List<Inport> listCondition(String startTime, String endTime, String goodsCode, String goodsName){
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
