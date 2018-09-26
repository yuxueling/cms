package com.cloudht.cont.vo;

import com.cloudht.cont.domain.ContProductParamDO;
import com.cloudht.cont.domain.ContProductPkDO;

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
