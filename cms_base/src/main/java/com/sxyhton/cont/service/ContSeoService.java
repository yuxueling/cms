package com.sxyhton.cont.service;

import java.util.List;
import java.util.Map;

import com.sxyhton.cont.domain.ContSeoDO;

/**
 * 
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-10-18 09:15:37
 */
public interface ContSeoService {
	
	ContSeoDO get(Integer contSeoId);
	
	List<ContSeoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContSeoDO contSeo);
	
	int update(ContSeoDO contSeo);
	
	int remove(Integer contSeoId);
	
	int batchRemove(Integer[] contSeoIds);
}
