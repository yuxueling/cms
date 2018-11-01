package com.sxyhton.cont.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sxyhton.cont.domain.ContProductDO;

/**
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-18 16:27:29
 */
@Mapper
public interface ContProductDao {

	ContProductDO get(Integer contProductId);
	
	List<ContProductDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContProductDO contProduct);
	
	int update(ContProductDO contProduct);
	
	int remove(Integer cont_product_id);
	
	int batchRemove(Integer[] contProductIds);
	
	List<ContProductDO> leftCategoryList(Map<String, Object> map);
	
	int leftCategoryListCount(Map<String, Object> map);
	List<Map<String,Object>> productList(Map<String,Object> map);
}
