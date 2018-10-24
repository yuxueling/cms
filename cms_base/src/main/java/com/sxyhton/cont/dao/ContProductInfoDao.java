package com.sxyhton.cont.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sxyhton.cont.domain.ContProductInfoDO;

/**
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-18 16:27:29
 */
@Mapper
public interface ContProductInfoDao {

	ContProductInfoDO get(Integer contProductInfoId);
	
	List<ContProductInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContProductInfoDO contProductInfo);
	
	int update(ContProductInfoDO contProductInfo);
	
	int remove(Integer cont_product_info_id);
	
	int batchRemove(Integer[] contProductInfoIds);

	List<ContProductInfoDO> listByDict(Map<String, Object> map);
}
