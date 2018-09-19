package com.cloudht.cont.service;

import com.cloudht.cont.domain.ContCategoryDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-18 16:27:29
 */
public interface ContCategoryService {
	
	ContCategoryDO get(Integer contCategoryId);
	
	List<ContCategoryDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContCategoryDO contCategory);
	
	int update(ContCategoryDO contCategory);
	
	int remove(Integer contCategoryId);
	
	int batchRemove(Integer[] contCategoryIds);
}
