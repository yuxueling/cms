package com.sxyhton.common.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 文件上传
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-10-22 11:04:35
 */
public class SysFileDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//文件类型
	private Integer type;
	//URL地址
	private String url;
	//创建时间
	private Date createDate;

	public SysFileDO() {
        super();
    }


    public SysFileDO(Integer type, String url, Date createDate) {
        super();
        this.type = type;
        this.url = url;
        this.createDate = createDate;
    }
	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：文件类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：文件类型
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：URL地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：URL地址
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateDate() {
		return createDate;
	}
	public String getFileType() {
    	return this.url.substring(this.url.lastIndexOf(".") + 1, this.url.length()).toLowerCase();
    }
	@Override
    public String toString() {
        return "FileDO{" +
                "id=" + id +
                ", type=" + type +
                ", url='" + url + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
