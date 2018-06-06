package cn.jcloud.sell.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import cn.jcloud.constent.IDG;
import cn.jcloud.domain.BizDomain;

/**
* @author 蒋维
* @date 创建时间：2018年5月3日 上午11:38:08
* @version 1.0
*/
@Entity
@Table(name="wms_sell")
@GenericGenerator(name = "id", strategy = IDG.DISTRIBUTED_IDENTITY)
public class Sell extends BizDomain<Long> {
	private Long sellRiseId;
	private String goodsCode;
	private String goodsName;
	private String goodsType;
	private Integer sellNumber;
	private String goodsUnit;
	private String goodsFactory;
	private Double goodsPrice;

	public Long getSellRiseId() {
		return sellRiseId;
	}
	public void setSellRiseId(Long sellRiseId) {
		this.sellRiseId = sellRiseId;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Integer getSellNumber() {
		return sellNumber;
	}
	public void setSellNumber(Integer sellNumber) {
		this.sellNumber = sellNumber;
	}
	public String getGoodsUnit() {
		return goodsUnit;
	}
	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}
	public Double getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public String getGoodsFactory() {
		return goodsFactory;
	}
	public void setGoodsFactory(String goodsFactory) {
		this.goodsFactory = goodsFactory;
	}
	
}
