package com.sxyhton.cont.service;

import java.util.List;
import java.util.Map;

import com.sxyhton.cont.domain.ContFormDataDO;

/**
 * 表单数据表
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-27 08:56:14
 */
public interface ContFormDataService {
	
	ContFormDataDO get(Integer contFormDataId);
	
	List<ContFormDataDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContFormDataDO contFormData);
	
	int update(ContFormDataDO contFormData);
	
	int remove(Integer contFormDataId);
	
	int batchRemove(Integer[] contFormDataIds);

	int batchSave(List<ContFormDataDO> contFormDataList);
}
