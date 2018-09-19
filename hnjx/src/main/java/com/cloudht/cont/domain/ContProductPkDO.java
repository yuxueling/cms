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
public class ContProductPkDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer contProductPkId;
	//参数主键
	private String contProductParamId;
	//参数值
	private String paramValue;

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
	public void setContProductParamId(String contProductParamId) {
		this.contProductParamId = contProductParamId;
	}
	/**
	 * 获取：参数主键
	 */
	public String getContProductParamId() {
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
}
