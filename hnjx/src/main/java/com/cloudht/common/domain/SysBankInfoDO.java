package com.cloudht.common.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 银行信息
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-05-30 16:21:30
 */
public class SysBankInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//银行信息表主键
	private Integer sysBankInfoId;
	//银行名称
	private String bankName;
	//代码
	private String bankCode;
	//银行地址
	private String bankAddress;
	//银行识别代码，例BKCHCNBJ300
	private String bankSwift;
	//备注
	private String remarks;
	//创建时间
	private Date gmtCreate;
	//修改时间
	private Date gmtModified;
	//是否作废，0：否，1：是
	private Integer isDelete;

	/**
	 * 设置：银行信息表主键
	 */
	public void setSysBankInfoId(Integer sysBankInfoId) {
		this.sysBankInfoId = sysBankInfoId;
	}
	/**
	 * 获取：银行信息表主键
	 */
	public Integer getSysBankInfoId() {
		return sysBankInfoId;
	}
	/**
	 * 设置：银行名称
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/**
	 * 获取：银行名称
	 */
	public String getBankName() {
		return bankName;
	}
	/**
	 * 设置：代码
	 */
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	/**
	 * 获取：代码
	 */
	public String getBankCode() {
		return bankCode;
	}
	/**
	 * 设置：银行地址
	 */
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	/**
	 * 获取：银行地址
	 */
	public String getBankAddress() {
		return bankAddress;
	}
	/**
	 * 设置：银行识别代码，例BKCHCNBJ300
	 */
	public void setBankSwift(String bankSwift) {
		this.bankSwift = bankSwift;
	}
	/**
	 * 获取：银行识别代码，例BKCHCNBJ300
	 */
	public String getBankSwift() {
		return bankSwift;
	}
	/**
	 * 设置：备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注
	 */
	public String getRemarks() {
		return remarks;
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
	 * 设置：是否作废，0：否，1：是
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：是否作废，0：否，1：是
	 */
	public Integer getIsDelete() {
		return isDelete;
	}
}
