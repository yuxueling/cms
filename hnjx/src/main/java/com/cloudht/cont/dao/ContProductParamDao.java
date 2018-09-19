package com.cloudht.cont.dao;

import com.cloudht.cont.domain.ContProductParamDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-18 16:27:29
 */
@Mapper
public interface ContProductParamDao {

	ContProductParamDO get(String contProductParamId);
	
	List<ContProductParamDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContProductParamDO contProductParam);
	
	int update(ContProductParamDO contProductParam);
	
	int remove(String cont_product_param_id);
	
	int batchRemove(String[] contProductParamIds);
}
