package com.cloudht.cont.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-18 16:27:29
 */
public class ContProductInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer contProductInfoId;
	//产品主键
	private Integer contProductId;
	//语种：字段表（CmsLangType）
	private String langType;
	//标题
	private String productName;
	//简介
	private String productDesc;
	//详情
	private String details;
	//产品关键词
	private String productKeywords;
	//SEO关键词
	private String seoKeywords;
	//SEO产品描述
	private String seoDesc;
	//SEO标题
	private String seoTitle;
	//创建用户id
	private Long createBy;
	//创建时间
	private Date gmtCreate;
	//修改时间
	private Date gmtModified;

	/**
	 * 设置：主键
	 */
	public void setContProductInfoId(Integer contProductInfoId) {
		this.contProductInfoId = contProductInfoId;
	}
	/**
	 * 获取：主键
	 */
	public Integer getContProductInfoId() {
		return contProductInfoId;
	}
	/**
	 * 设置：产品主键
	 */
	public void setContProductId(Integer contProductId) {
		this.contProductId = contProductId;
	}
	/**
	 * 获取：产品主键
	 */
	public Integer getContProductId() {
		return contProductId;
	}
	/**
	 * 设置：语种：字段表（CmsLangType）
	 */
	public void setLangType(String langType) {
		this.langType = langType;
	}
	/**
	 * 获取：语种：字段表（CmsLangType）
	 */
	public String getLangType() {
		return langType;
	}
	/**
	 * 设置：标题
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * 获取：标题
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * 设置：简介
	 */
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	/**
	 * 获取：简介
	 */
	public String getProductDesc() {
		return productDesc;
	}
	/**
	 * 设置：详情
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	/**
	 * 获取：详情
	 */
	public String getDetails() {
		return details;
	}
	/**
	 * 设置：产品关键词
	 */
	public void setProductKeywords(String productKeywords) {
		this.productKeywords = productKeywords;
	}
	/**
	 * 获取：产品关键词
	 */
	public String getProductKeywords() {
		return productKeywords;
	}
	/**
	 * 设置：SEO关键词
	 */
	public void setSeoKeywords(String seoKeywords) {
		this.seoKeywords = seoKeywords;
	}
	/**
	 * 获取：SEO关键词
	 */
	public String getSeoKeywords() {
		return seoKeywords;
	}
	/**
	 * 设置：SEO产品描述
	 */
	public void setSeoDesc(String seoDesc) {
		this.seoDesc = seoDesc;
	}
	/**
	 * 获取：SEO产品描述
	 */
	public String getSeoDesc() {
		return seoDesc;
	}
	/**
	 * 设置：SEO标题
	 */
	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}
	/**
	 * 获取：SEO标题
	 */
	public String getSeoTitle() {
		return seoTitle;
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
