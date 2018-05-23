package cn.jcloud.goods.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import cn.jcloud.constent.IDG;
import cn.jcloud.domain.BizDomain;

/**
* @author 蒋维
* @date 创建时间：2018年5月3日 上午10:20:32
* @version 1.0
*/
@Entity
@Table(name="wms_goods")
@GenericGenerator(name = "id", strategy = IDG.DISTRIBUTED_IDENTITY)
public class Goods extends BizDomain<Long> {
	private String goodsName;
	private String goodsType;
	private String goodsCode;
	private String goodsUnit;
	private String goodsFactory;
	private Integer goodsNumber;
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsUnit() {
		return goodsUnit;
	}
	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}
	public String getGoodsFactory() {
		return goodsFactory;
	}
	public void setGoodsFactory(String goodsFactory) {
		this.goodsFactory = goodsFactory;
	}
	public Integer getGoodsNumber() {
		return goodsNumber;
	}
	public void setGoodsNumber(Integer goodsNumber) {
		this.goodsNumber = goodsNumber;
	}
	
	
}
