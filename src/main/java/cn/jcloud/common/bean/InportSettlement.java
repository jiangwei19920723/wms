package cn.jcloud.common.bean;
/**
* @author 蒋维
* @date 创建时间：2018年6月14日 上午9:24:36
* @version 1.0
*/
public class InportSettlement {
	private String goodsCode;
	private String goodsName;
	private String goodsUnit;
	private Integer inportNumber;
	private Double inportPrice;
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
	public String getGoodsUnit() {
		return goodsUnit;
	}
	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}
	public Integer getInportNumber() {
		return inportNumber;
	}
	public void setInportNumber(Integer inportNumber) {
		this.inportNumber = inportNumber;
	}
	public Double getInportPrice() {
		return inportPrice;
	}
	public void setInportPrice(Double inportPrice) {
		this.inportPrice = inportPrice;
	}
	
}
