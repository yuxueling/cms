package com.cloudht.cont.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-10-12 15:59:14
 */
public class ContSitemapDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//网站地图主键
	private Integer contSitemapId;
	//语言
	private String langType;
	//页面类型
	private String pageType;
	//页面标题
	private String pageTitle;
	//页面地址
	private String loc;
	//页面更新时间
	private Date lastmod;
	//更新频率
	private String changefreq;
	//权重
	private Float priority;
	//创建时间
	private Date gmtCreate;
	//更新时间
	private Date gmtModified;

	/**
	 * 设置：网站地图主键
	 */
	public void setContSitemapId(Integer contSitemapId) {
		this.contSitemapId = contSitemapId;
	}
	/**
	 * 获取：网站地图主键
	 */
	public Integer getContSitemapId() {
		return contSitemapId;
	}
	/**
	 * 设置：语言
	 */
	public void setLangType(String langType) {
		this.langType = langType;
	}
	/**
	 * 获取：语言
	 */
	public String getLangType() {
		return langType;
	}
	/**
	 * 设置：页面类型
	 */
	public void setPageType(String pageType) {
		this.pageType = pageType;
	}
	/**
	 * 获取：页面类型
	 */
	public String getPageType() {
		return pageType;
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
	public void setLoc(String loc) {
		this.loc = loc;
	}
	/**
	 * 获取：页面地址
	 */
	public String getLoc() {
		return loc;
	}
	/**
	 * 设置：页面更新时间
	 */
	public void setLastmod(Date lastmod) {
		this.lastmod = lastmod;
	}
	/**
	 * 获取：页面更新时间
	 */
	public Date getLastmod() {
		return lastmod;
	}
	/**
	 * 设置：更新频率
	 */
	public void setChangefreq(String changefreq) {
		this.changefreq = changefreq;
	}
	/**
	 * 获取：更新频率
	 */
	public String getChangefreq() {
		return changefreq;
	}
	/**
	 * 设置：权重
	 */
	public void setPriority(Float priority) {
		this.priority = priority;
	}
	/**
	 * 获取：权重
	 */
	public Float getPriority() {
		return priority;
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
	 * 设置：更新时间
	 */
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getGmtModified() {
		return gmtModified;
	}
	public String getUrl() {
		return "<url>"
				+ "<loc>"+this.getLoc()+"</loc>"
				+ "<lastmod>"+this.getLastmod()+"</lastmod>"
				+ "<changefreq>"+this.getChangefreq()+"</changefreq>"
				+ "<priority>"+this.getPriority()+"</priority>"
			 + "</url>";
	}
}
