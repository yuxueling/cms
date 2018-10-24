package com.sxyhton.cont.vo;

import java.io.Serializable;
import java.util.List;

import com.sxyhton.cont.domain.ContProductParamDO;


/**
 * 
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-23 13:01:59
 */
public class ContProductParamVO implements Serializable {
	private static final long serialVersionUID = 1L;
	

	//语种：字段表（CmsLangType）
	private String langType;

	//产品参数列表
	private List<ContProductParamDO> contProductParamDOList;

	public String getLangType() {
		return langType;
	}

	public void setLangType(String langType) {
		this.langType = langType;
	}

	public List<ContProductParamDO> getContProductParamDOList() {
		return contProductParamDOList;
	}

	public void setContProductParamDOList(List<ContProductParamDO> contProductParamDOList) {
		this.contProductParamDOList = contProductParamDOList;
	}
}
