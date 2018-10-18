package com.cloudht.cont.domain;

import java.io.Serializable;

/**
 * 
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-10-18 09:15:37
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
	private String metaName;
	//meta描述或内容
	private String metaContent;

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
	public void setMetaName(String metaName) {
		this.metaName = metaName;
	}
	/**
	 * 获取：meta名称
	 */
	public String getMetaName() {
		return metaName;
	}
	/**
	 * 设置：meta描述或内容
	 */
	public void setMetaContent(String metaContent) {
		this.metaContent = metaContent;
	}
	/**
	 * 获取：meta描述或内容
	 */
	public String getMetaContent() {
		return metaContent;
	}
}
