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
public class ContProductImgDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//图片主键
	private Integer contProductImgId;
	//产品主键
	private Integer contProductId;
	//图片路径
	private String imgUrl;

	/**
	 * 设置：图片主键
	 */
	public void setContProductImgId(Integer contProductImgId) {
		this.contProductImgId = contProductImgId;
	}
	/**
	 * 获取：图片主键
	 */
	public Integer getContProductImgId() {
		return contProductImgId;
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
	 * 设置：图片路径
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	/**
	 * 获取：图片路径
	 */
	public String getImgUrl() {
		return imgUrl;
	}
}
