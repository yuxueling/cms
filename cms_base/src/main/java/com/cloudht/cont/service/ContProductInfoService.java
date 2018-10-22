package com.cloudht.cont.service;

import com.cloudht.cont.domain.ContProductInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-18 16:27:29
 */
public interface ContProductInfoService {
	
	ContProductInfoDO get(Integer contProductInfoId);
	
	List<ContProductInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContProductInfoDO contProductInfo);
	
	int update(ContProductInfoDO contProductInfo);
	
	int remove(Integer contProductInfoId);
	
	int batchRemove(Integer[] contProductInfoIds);

	List<ContProductInfoDO> listByDict(Map<String, Object> map);


}
