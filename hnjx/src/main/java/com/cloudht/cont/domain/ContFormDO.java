package com.cloudht.cont.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 表单表
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-25 10:01:26
 */
public class ContFormDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//表单表主键
	private Integer contFormId;
	//标题
	private String title;
	//语种 字典CmsLangType
	private String langType;
	//seo标题
	private String seoTitle;
	//seo关键字
	private String seoKeywords;
	//seo描述
	private String seoDescribe;
	//备注
	private String remark;
	//
	private Long modifiedBy;
	//创建时间
	private Date gmtCreate;
	//修改时间
	private Date gmtModified;

	/**
	 * 设置：表单表主键
	 */
	public void setContFormId(Integer contFormId) {
		this.contFormId = contFormId;
	}
	/**
	 * 获取：表单表主键
	 */
	public Integer getContFormId() {
		return contFormId;
	}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：语种 字典CmsLangType
	 */
	public void setLangType(String langType) {
		this.langType = langType;
	}
	/**
	 * 获取：语种 字典CmsLangType
	 */
	public String getLangType() {
		return langType;
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
	 * 设置：seo关键字
	 */
	public void setSeoKeywords(String seoKeywords) {
		this.seoKeywords = seoKeywords;
	}
	/**
	 * 获取：seo关键字
	 */
	public String getSeoKeywords() {
		return seoKeywords;
	}
	/**
	 * 设置：seo描述
	 */
	public void setSeoDescribe(String seoDescribe) {
		this.seoDescribe = seoDescribe;
	}
	/**
	 * 获取：seo描述
	 */
	public String getSeoDescribe() {
		return seoDescribe;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：
	 */
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	/**
	 * 获取：
	 */
	public Long getModifiedBy() {
		return modifiedBy;
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
