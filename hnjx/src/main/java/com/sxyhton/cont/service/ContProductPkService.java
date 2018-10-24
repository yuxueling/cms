package com.sxyhton.cont.service;

import java.util.List;
import java.util.Map;

import com.sxyhton.cont.domain.ContProductPkDO;

/**
 * 
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-23 13:01:59
 */
public interface ContProductPkService {
	
	ContProductPkDO get(Integer contProductPkId);
	
	List<ContProductPkDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContProductPkDO contProductPk);
	
	int update(ContProductPkDO contProductPk);
	
	int remove(Integer contProductPkId);
	
	int batchRemove(Integer[] contProductPkIds);

	int batchInsert(List<ContProductPkDO> contProductPkList);

	int delByProductParamId(Integer contProductParamId);
}
