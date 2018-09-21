package com.cloudht.cont.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-18 17:10:49
 */
public class ContProductDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer contProductId;
	//产品名称
	private String productName;
	//所属分类：0表示顶级
	private Integer categoryId;
	//销售状态：0-下架，1-上架
	private Integer saleStatus;
	//创建用户id
	private Long createBy;
	//创建时间
	private Date gmtCreate;
	//修改时间
	private Date gmtModified;
	//类型名称，辅助查询字段，该数据库表里没有
	private String categoryName;
	/**
	 * 类型名称，辅助查询字段，该数据库表里没有
	 * @return
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * 类型名称，辅助查询字段，该数据库表里没有
	 * @param categoryName
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	/**
	 * 设置：
	 */
	public void setContProductId(Integer contProductId) {
		this.contProductId = contProductId;
	}
	/**
	 * 获取：
	 */
	public Integer getContProductId() {
		return contProductId;
	}
	/**
	 * 设置：产品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * 获取：产品名称
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * 设置：所属分类：0表示顶级
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * 获取：所属分类：0表示顶级
	 */
	public Integer getCategoryId() {
		return categoryId;
	}
	/**
	 * 设置：销售状态：0-下架，1-上架
	 */
	public void setSaleStatus(Integer saleStatus) {
		this.saleStatus = saleStatus;
	}
	/**
	 * 获取：销售状态：0-下架，1-上架
	 */
	public Integer getSaleStatus() {
		return saleStatus;
	}
	/**
	 * 设置：创建用户id
	 */
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建用户id
	 */
	public Long getCreateBy() {
		return createBy;
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
}
