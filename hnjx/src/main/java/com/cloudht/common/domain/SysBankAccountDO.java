package com.cloudht.common.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 银行账户信息
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-05-30 16:21:30
 */
public class SysBankAccountDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//银行账号信息表主键
	private Integer sysBankAccountId;
	//引用了sys_bank_information表的主键
	private Integer sysBankInfoId;
	//账号
	private String accountNo;
	//币别
	private String currency;
	//账号性质/账号类型，0：结算户
	private Integer accountType;
	//对应会计代码
	private String accountingCode;
	//对应会计科目名称
	private String accountingSubject;
	//对接用途
	private String purpose;
	//创建时间
	private Date gmtCreate;
	//修改时间
	private Date gmtModified;
	//是否删除，0：否，1：是
	private Integer isDelete;

	/**
	 * 设置：银行账号信息表主键
	 */
	public void setSysBankAccountId(Integer sysBankAccountId) {
		this.sysBankAccountId = sysBankAccountId;
	}
	/**
	 * 获取：银行账号信息表主键
	 */
	public Integer getSysBankAccountId() {
		return sysBankAccountId;
	}
	/**
	 * 设置：引用了sys_bank_information表的主键
	 */
	public void setSysBankInfoId(Integer sysBankInfoId) {
		this.sysBankInfoId = sysBankInfoId;
	}
	/**
	 * 获取：引用了sys_bank_information表的主键
	 */
	public Integer getSysBankInfoId() {
		return sysBankInfoId;
	}
	/**
	 * 设置：账号
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	/**
	 * 获取：账号
	 */
	public String getAccountNo() {
		return accountNo;
	}
	/**
	 * 设置：币别
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * 获取：币别
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * 设置：账号性质/账号类型，0：结算户
	 */
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
	/**
	 * 获取：账号性质/账号类型，0：结算户
	 */
	public Integer getAccountType() {
		return accountType;
	}
	/**
	 * 设置：对应会计代码
	 */
	public void setAccountingCode(String accountingCode) {
		this.accountingCode = accountingCode;
	}
	/**
	 * 获取：对应会计代码
	 */
	public String getAccountingCode() {
		return accountingCode;
	}
	/**
	 * 设置：对应会计科目名称
	 */
	public void setAccountingSubject(String accountingSubject) {
		this.accountingSubject = accountingSubject;
	}
	/**
	 * 获取：对应会计科目名称
	 */
	public String getAccountingSubject() {
		return accountingSubject;
	}
	/**
	 * 设置：对接用途
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	/**
	 * 获取：对接用途
	 */
	public String getPurpose() {
		return purpose;
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
	 * 设置：是否删除，0：否，1：是
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：是否删除，0：否，1：是
	 */
	public Integer getIsDelete() {
		return isDelete;
	}
}
