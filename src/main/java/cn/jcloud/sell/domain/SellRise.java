package cn.jcloud.sell.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import cn.jcloud.constent.IDG;
import cn.jcloud.domain.BizDomain;

/**
* @author 蒋维
* @date 创建时间：2018年6月7日 上午9:52:46
* @version 1.0
*/
@Entity
@Table(name="wms_sell_rise")
@GenericGenerator(name = "id", strategy = IDG.DISTRIBUTED_IDENTITY)
public class SellRise extends BizDomain<Long> {
	private String createBy;
	private String sellBy;
	private String phone;
	private Double sellPrice;
	private String remark;
	private Double arrears;
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getSellBy() {
		return sellBy;
	}
	public void setSellBy(String sellBy) {
		this.sellBy = sellBy;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Double getArrears() {
		return arrears;
	}
	public void setArrears(Double arrears) {
		this.arrears = arrears;
	}

}
