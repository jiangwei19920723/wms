package cn.jcloud.inport.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import cn.jcloud.constent.IDG;
import cn.jcloud.domain.BizDomain;

/**
* @author 蒋维
* @date 创建时间：2018年5月3日 上午11:24:25
* @version 1.0
*/
@Entity
@Table(name="wms_inport")
@GenericGenerator(name = "id", strategy = IDG.DISTRIBUTED_IDENTITY)
public class Inport extends BizDomain<Long> {
	private Long goodsId;
	private String goodsCode;
	private String goodsName;
	private Integer inportNumber;
	private String goodsUnit;
	private Double inportPrice;
	private String createBy;
	private String remark;
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
	public Integer getInportNumber() {
		return inportNumber;
	}
	public void setInportNumber(Integer inportNumber) {
		this.inportNumber = inportNumber;
	}

	public String getGoodsUnit() {
		return goodsUnit;
	}
	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}
	public Double getInportPrice() {
		return inportPrice;
	}
	public void setInportPrice(Double inportPrice) {
		this.inportPrice = inportPrice;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
