package com.cloudht.cont.service;

import com.cloudht.cont.domain.ContProductPkDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-18 16:27:29
 */
public interface ContProductPkService {
	
	ContProductPkDO get(Integer contProductPkId);
	
	List<ContProductPkDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContProductPkDO contProductPk);
	
	int update(ContProductPkDO contProductPk);
	
	int remove(Integer contProductPkId);
	
	int batchRemove(Integer[] contProductPkIds);
}
