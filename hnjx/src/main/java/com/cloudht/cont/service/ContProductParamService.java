package com.cloudht.cont.service;

import com.cloudht.cont.domain.ContProductParamDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-18 16:27:29
 */
public interface ContProductParamService {
	
	ContProductParamDO get(String contProductParamId);
	
	List<ContProductParamDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContProductParamDO contProductParam);
	
	int update(ContProductParamDO contProductParam);
	
	int remove(String contProductParamId);
	
	int batchRemove(String[] contProductParamIds);
}
