package com.cloudht.cont.service;

import com.cloudht.cont.domain.ContProductDO;
import com.sxyht.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-18 16:27:29
 */
public interface ContProductService {
	
	ContProductDO get(Integer contProductId);
	
	List<ContProductDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContProductDO contProduct);
	
	int update(ContProductDO contProduct);
	
	int remove(Integer contProductId);
	
	int batchRemove(Integer[] contProductIds);

	PageUtils leftCategoryList(Map<String, Object> params);
}