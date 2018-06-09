package cn.jcloud.common.bean;

import java.util.List;

/**
* @author 蒋维
* @date 创建时间：2018年6月5日 下午2:16:33
* @version 1.0
*/

public class SellVo {
	private String createBy;
	private String phone;
	private String sellBy;
	private String remark;
	private Double sellPrice;
	private Double arrears;
	private List<String> goods;
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSellBy() {
		return sellBy;
	}
	public void setSellBy(String sellBy) {
		this.sellBy = sellBy;
	}
	public Double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public List<String> getGoods() {
		return goods;
	}
	public void setGoods(List<String> goods) {
		this.goods = goods;
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
