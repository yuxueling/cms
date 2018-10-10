package com.cloudht.cont.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-29 08:43:59
 */
public class ContCategoryDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer contCategoryId;
	//类别类型：（CmsCategoryType）
	private String categoryType;
	//上级分类
	private Integer parentCategoryId;
	//类别名称
	private String categoryName;
	//编码
	private String categoryCode;
	//排序
	private Integer sort;
	//是否显示,1显示 0不显示
	private Integer isShow;
	//是否显示,1显示 0不显示
	private Integer isDisplay;
	//备注
	private String remark;
	//创建用户id
	private Long createBy;
	//创建时间
	private Date gmtCreate;
	//修改时间
	private Date gmtModified;


	//new

	//单语种详情
	private ContCategoryInfoDO contCategoryInfoDO;

	/**
	 * 设置：主键
	 */
	public void setContCategoryId(Integer contCategoryId) {
		this.contCategoryId = contCategoryId;
	}
	/**
	 * 获取：主键
	 */
	public Integer getContCategoryId() {
		return contCategoryId;
	}
	/**
	 * 设置：类别类型：（CmsCategoryType）
	 */
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	/**
	 * 获取：类别类型：（CmsCategoryType）
	 */
	public String getCategoryType() {
		return categoryType;
	}
	/**
	 * 设置：上级分类
	 */
	public void setParentCategoryId(Integer parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}
	/**
	 * 获取：上级分类
	 */
	public Integer getParentCategoryId() {
		return parentCategoryId;
	}
	/**
	 * 设置：类别名称
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	/**
	 * 获取：类别名称
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * 设置：编码
	 */
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	/**
	 * 获取：编码
	 */
	public String getCategoryCode() {
		return categoryCode;
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

	public ContCategoryInfoDO getContCategoryInfoDO() {
		return contCategoryInfoDO;
	}

	public void setContCategoryInfoDO(ContCategoryInfoDO contCategoryInfoDO) {
		this.contCategoryInfoDO = contCategoryInfoDO;
	}
	/**
	 * 设置：是否显示,1显示 0不显示
	 */
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
		this.isDisplay=isShow;
	}
	/**
	 * 获取：是否显示,1显示 0不显示
	 */
	public Integer getIsShow() {
		return isShow;
	}
	/**
	 * 获取：是否显示,1显示 0不显示
	 */
	public Integer getIsDisplay() {
		return this.isDisplay;
	}
}
