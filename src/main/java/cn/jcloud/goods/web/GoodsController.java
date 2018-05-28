package cn.jcloud.goods.web;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.jcloud.goods.domain.Goods;
import cn.jcloud.goods.service.GoodsService;

/**
* @author 蒋维
* @date 创建时间：2018年5月3日 上午10:27:43
* @version 1.0
*/
@RestController
@RequestMapping("/v0.1/goods")
public class GoodsController {
	@Autowired
	private GoodsService service;
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public void insert(@RequestBody @Valid Goods goods){
		service.save(goods);
	}
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public List<Goods> list(){
		return service.getAll();
	}
	@RequestMapping(value = "select/{goodsName}", method = RequestMethod.GET)
	public boolean selectByGoodsName(@PathVariable("goodsName") String goodsName){
		Goods goods = service.getByGoodsName(goodsName);
		if (goods == null) {
			return false;
		}else {
			return true;
		}
	}
	@RequestMapping(value = "select", method = RequestMethod.GET)
	public List<Goods> listCondition(String startTime, String endTime, String goodsCode, String goodsName, String goodsType, String goodsFactory){
		try {
			return service.getSelect(startTime, endTime, goodsCode, goodsName, goodsType, goodsFactory);
		} catch (ParseException e) {
			return null;
		}
	}
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	public void deleteById(@PathVariable("id")Long id){
		service.deleteById(id);
	}
	@RequestMapping(value = "updateGoodsPrice", method = RequestMethod.PUT)
	public Goods update(@RequestBody @Valid Goods goods) throws SQLException{
		return service.update(goods);
	}
}
