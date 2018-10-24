package com.sxyhton.cont.service;

import java.util.List;
import java.util.Map;

import com.sxyhton.cont.domain.ContFormDO;

/**
 * 表单表
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-27 08:56:13
 */
public interface ContFormService {
	
	ContFormDO get(Integer contFormId);
	
	List<ContFormDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContFormDO contForm);
	
	int update(ContFormDO contForm);
	
	int remove(Integer contFormId);
	
	int batchRemove(Integer[] contFormIds);

}
