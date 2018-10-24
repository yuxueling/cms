package com.sxyhton.cont.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-10-19 11:18:07
 */
public class ContSeoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer contSeoId;
	//语言类型
	private String langType;
	//页面标题
	private String pageTitle;
	//页面地址
	private String pageAddress;
	//seo标题
	private String seoTitle;
	//meta名称
	private String keywords;
	//meta描述或内容
	private String description;
	//
	private Long createBy;
	//最后修改时间
	private Date gmtModified;

	/**
	 * 设置：主键
	 */
	public void setContSeoId(Integer contSeoId) {
		this.contSeoId = contSeoId;
	}
	/**
	 * 获取：主键
	 */
	public Integer getContSeoId() {
		return contSeoId;
	}
	/**
	 * 设置：语言类型
	 */
	public void setLangType(String langType) {
		this.langType = langType;
	}
	/**
	 * 获取：语言类型
	 */
	public String getLangType() {
		return langType;
	}
	/**
	 * 设置：页面标题
	 */
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	/**
	 * 获取：页面标题
	 */
	public String getPageTitle() {
		return pageTitle;
	}
	/**
	 * 设置：页面地址
	 */
	public void setPageAddress(String pageAddress) {
		this.pageAddress = pageAddress;
	}
	/**
	 * 获取：页面地址
	 */
	public String getPageAddress() {
		return pageAddress;
	}
	/**
	 * 设置：seo标题
	 */
	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}
	/**
	 * 获取：seo标题
	 */
	public String getSeoTitle() {
		return seoTitle;
	}
	/**
	 * 设置：meta名称
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	/**
	 * 获取：meta名称
	 */
	public String getKeywords() {
		return keywords;
	}
	/**
	 * 设置：meta描述或内容
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：meta描述或内容
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：
	 */
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：
	 */
	public Long getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：最后修改时间
	 */
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	/**
	 * 获取：最后修改时间
	 */
	public Date getGmtModified() {
		return gmtModified;
	}
}
