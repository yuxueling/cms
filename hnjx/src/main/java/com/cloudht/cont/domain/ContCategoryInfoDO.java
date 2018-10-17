package com.cloudht.cont.domain;

import java.io.Serializable;



/**
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-29 08:43:59
 */
public class ContCategoryInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer contCategoryInfoId;
	//
	private Integer contCategoryId;
	//语种:字典表(CmsLangType)
	private String langType;
	//类别名称
	private String categoryName;
	//详情
	private String details;
	//数据库里没有，辅助显示语种的
	private String langName;
	/**
	 * 设置：
	 */
	public void setContCategoryInfoId(Integer contCategoryInfoId) {
		this.contCategoryInfoId = contCategoryInfoId;
	}
	/**
	 * 获取：
	 */
	public Integer getContCategoryInfoId() {
		return contCategoryInfoId;
	}
	/**
	 * 设置：
	 */
	public void setContCategoryId(Integer contCategoryId) {
		this.contCategoryId = contCategoryId;
	}
	/**
	 * 获取：
	 */
	public Integer getContCategoryId() {
		return contCategoryId;
	}
	/**
	 * 设置：语种:字典表(CmsLangType)
	 */
	public void setLangType(String langType) {
		this.langType = langType;
	}
	/**
	 * 获取：语种:字典表(CmsLangType)
	 */
	public String getLangType() {
		return langType;
	}
	/**
	 * 设置：类别名称
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	/**
	 * 获取：类别名称
	 */
	public String getCategoryName() {
		return categoryName;
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
	 * 语种名称，数据库没有
	 * @return
	 */
	public String getLangName() {
		return langName;
	}
	/**
	 * 语种名称，数据库没有
	 * @param langName
	 */
	public void setLangName(String langName) {
		this.langName = langName;
	}
}
