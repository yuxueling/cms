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
public class ContProductParamDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private String contProductParamId;
	//产品主键
	private Integer contProductId;
	//语种：字段表（CmsLangType）
	private String langType;
	//参数名称
	private String paramName;
	//限制数量
	private Integer limitNum;
	//创建用户id
	private Long createBy;
	//创建时间
	private Date gmtCreate;
	//修改时间
	private Date gmtModified;

	/**
	 * 设置：主键
	 */
	public void setContProductParamId(String contProductParamId) {
		this.contProductParamId = contProductParamId;
	}
	/**
	 * 获取：主键
	 */
	public String getContProductParamId() {
		return contProductParamId;
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
	 * 设置：参数名称
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	/**
	 * 获取：参数名称
	 */
	public String getParamName() {
		return paramName;
	}
	/**
	 * 设置：限制数量
	 */
	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}
	/**
	 * 获取：限制数量
	 */
	public Integer getLimitNum() {
		return limitNum;
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
