package cn.jcloud.sellreturn.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import cn.jcloud.constent.IDG;
import cn.jcloud.domain.BizDomain;

/**
* @author 蒋维
* @date 创建时间：2018年5月18日 下午2:01:43
* @version 1.0
*/
@Entity
@Table(name="wms_sell_return")
@GenericGenerator(name = "id", strategy = IDG.DISTRIBUTED_IDENTITY)
public class SellReturn extends BizDomain<Long> {
	private Long goodsId;
	private String goodsCode;
	private String goodsName;
	private Integer sellReturnNumber;
	private String goodsUnit;
	private Double sellReturnPrice;
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
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
	public Integer getSellReturnNumber() {
		return sellReturnNumber;
	}
	public void setSellReturnNumber(Integer sellReturnNumber) {
		this.sellReturnNumber = sellReturnNumber;
	}
	public String getGoodsUnit() {
		return goodsUnit;
	}
	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}
	public Double getSellReturnPrice() {
		return sellReturnPrice;
	}
	public void setSellReturnPrice(Double sellReturnPrice) {
		this.sellReturnPrice = sellReturnPrice;
	}
	
}