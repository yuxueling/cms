package com.cloudht.cont.dao;

import com.cloudht.cont.domain.ContProductPkDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-23 13:01:59
 */
@Mapper
public interface ContProductPkDao {

	ContProductPkDO get(Integer contProductPkId);
	
	List<ContProductPkDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContProductPkDO contProductPk);
	
	int update(ContProductPkDO contProductPk);
	
	int remove(Integer cont_product_pk_id);
	
	int batchRemove(Integer[] contProductPkIds);

	int batchInsert(List<ContProductPkDO> ContProductPkList);

	int delByProductParamId(Integer contProductParamId);
}
