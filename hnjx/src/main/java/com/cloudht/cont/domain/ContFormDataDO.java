package com.cloudht.cont.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 表单数据表
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-27 08:56:14
 */
public class ContFormDataDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer contFormDataId;
	//表单表主键
	private Integer contFormId;
	//标题
	private String title;
	//值
	private String value;
	//标题样式
	private String titleStyle;
	//输入框样式
	private String inputStyle;
	//创建时间
	private Date gmtCreate;
	//修改时间
	private Date gmtModified;
	//创建人id
	private Long createBy;

	/**
	 * 设置：
	 */
	public void setContFormDataId(Integer contFormDataId) {
		this.contFormDataId = contFormDataId;
	}
	/**
	 * 获取：
	 */
	public Integer getContFormDataId() {
		return contFormDataId;
	}
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
	 * 设置：值
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 获取：值
	 */
	public String getValue() {
		return value;
	}
	/**
	 * 设置：标题样式
	 */
	public void setTitleStyle(String titleStyle) {
		this.titleStyle = titleStyle;
	}
	/**
	 * 获取：标题样式
	 */
	public String getTitleStyle() {
		return titleStyle;
	}
	/**
	 * 设置：输入框样式
	 */
	public void setInputStyle(String inputStyle) {
		this.inputStyle = inputStyle;
	}
	/**
	 * 获取：输入框样式
	 */
	public String getInputStyle() {
		return inputStyle;
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
	/**
	 * 设置：创建人id
	 */
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建人id
	 */
	public Long getCreateBy() {
		return createBy;
	}
}
