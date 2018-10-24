package com.sxyhton.cont.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-23 13:01:59
 */
public class ContProductParamDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer contProductParamId;
	//产品主键
	private Integer contProductId;
	//语种：字段表（CmsLangType）
	private String langType;
	//参数名称
	private String paramName;
	//限制数量
	private Integer limitNum;
	//排序
	private Integer sort;
	//创建用户id
	private Long createBy;
	//创建时间
	private Date gmtCreate;
	//修改时间
	private Date gmtModified;

	//new

	private List<ContProductPkDO> contProductPkDOList;

	/**
	 * 设置：主键
	 */
	public void setContProductParamId(Integer contProductParamId) {
		this.contProductParamId = contProductParamId;
	}
	/**
	 * 获取：主键
	 */
	public Integer getContProductParamId() {
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
	 * 设置：排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序
	 */
	public Integer getSort() {
		return sort;
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

	public List<ContProductPkDO> getContProductPkDOList() {
		return contProductPkDOList;
	}

	public void setContProductPkDOList(List<ContProductPkDO> contProductPkDOList) {
		this.contProductPkDOList = contProductPkDOList;
	}
}
