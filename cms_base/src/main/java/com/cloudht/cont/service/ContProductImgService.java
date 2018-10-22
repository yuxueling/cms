package com.cloudht.cont.service;

import com.cloudht.cont.domain.ContProductImgDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-18 16:27:29
 */
public interface ContProductImgService {
	
	ContProductImgDO get(Integer contProductImgId);
	
	List<ContProductImgDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContProductImgDO contProductImg);
	
	int update(ContProductImgDO contProductImg);
	
	int remove(Integer contProductImgId);
	
	int batchRemove(Integer[] contProductImgIds);
}
