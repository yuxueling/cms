package com.sxyhton.cont.domain;

import java.io.Serializable;



/**
 * 
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-23 13:01:59
 */
public class ContProductPkDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer contProductPkId;
	//参数主键
	private Integer contProductParamId;
	//参数值
	private String paramValue;
	//排序
	private Integer sort;

	/**
	 * 设置：主键
	 */
	public void setContProductPkId(Integer contProductPkId) {
		this.contProductPkId = contProductPkId;
	}
	/**
	 * 获取：主键
	 */
	public Integer getContProductPkId() {
		return contProductPkId;
	}
	/**
	 * 设置：参数主键
	 */
	public void setContProductParamId(Integer contProductParamId) {
		this.contProductParamId = contProductParamId;
	}
	/**
	 * 获取：参数主键
	 */
	public Integer getContProductParamId() {
		return contProductParamId;
	}
	/**
	 * 设置：参数值
	 */
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	/**
	 * 获取：参数值
	 */
	public String getParamValue() {
		return paramValue;
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
}
