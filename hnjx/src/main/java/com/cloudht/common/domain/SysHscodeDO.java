package com.cloudht.common.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * HS编码
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-05-30 16:21:30
 */
public class SysHscodeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//hs编码
	private Integer sysHscodeId;
	//商品名称
	private String productName;
	//起始日期
	private Date beginDate;
	//截止日期
	private Date endDate;
	//商品单位，如千克
	private String productUnit;
	//税率
	private Integer taxRate;
	//退税率
	private Integer taxRefundRate;
	//创建时间
	private Date gmtCreate;
	//修改时间
	private Date gmtModified;
	//是否删除，0:否 1:是
	private Integer isDelete;

	/**
	 * 设置：hs编码
	 */
	public void setSysHscodeId(Integer sysHscodeId) {
		this.sysHscodeId = sysHscodeId;
	}
	/**
	 * 获取：hs编码
	 */
	public Integer getSysHscodeId() {
		return sysHscodeId;
	}
	/**
	 * 设置：商品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * 获取：商品名称
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * 设置：起始日期
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	/**
	 * 获取：起始日期
	 */
	public Date getBeginDate() {
		return beginDate;
	}
	/**
	 * 设置：截止日期
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * 获取：截止日期
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * 设置：商品单位，如千克
	 */
	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}
	/**
	 * 获取：商品单位，如千克
	 */
	public String getProductUnit() {
		return productUnit;
	}
	/**
	 * 设置：税率
	 */
	public void setTaxRate(Integer taxRate) {
		this.taxRate = taxRate;
	}
	/**
	 * 获取：税率
	 */
	public Integer getTaxRate() {
		return taxRate;
	}
	/**
	 * 设置：退税率
	 */
	public void setTaxRefundRate(Integer taxRefundRate) {
		this.taxRefundRate = taxRefundRate;
	}
	/**
	 * 获取：退税率
	 */
	public Integer getTaxRefundRate() {
		return taxRefundRate;
	}
	/**
	 * 设置：创建时间
	 */
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getGmtCreate() {
		return gmtCreate;
	}
	/**
	 * 设置：修改时间
	 */
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getGmtModified() {
		return gmtModified;
	}
	/**
	 * 设置：是否删除，0:否 1:是
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：是否删除，0:否 1:是
	 */
	public Integer getIsDelete() {
		return isDelete;
	}
}
