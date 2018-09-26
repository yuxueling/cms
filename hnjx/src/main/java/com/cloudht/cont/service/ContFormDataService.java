package com.cloudht.cont.service;

import com.cloudht.cont.domain.ContFormDataDO;

import java.util.List;
import java.util.Map;

/**
 * 表单数据表
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-25 10:01:26
 */
public interface ContFormDataService {
	
	ContFormDataDO get(Integer contFormDataId);
	
	List<ContFormDataDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContFormDataDO contFormData);
	
	int update(ContFormDataDO contFormData);
	
	int remove(Integer contFormDataId);
	
	int batchRemove(Integer[] contFormDataIds);
}
