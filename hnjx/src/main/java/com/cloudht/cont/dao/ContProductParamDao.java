package com.cloudht.cont.dao;

import com.cloudht.cont.domain.ContProductParamDO;

import java.util.List;
import java.util.Map;

import com.cloudht.cont.vo.ContProductParamVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-23 13:01:59
 */
@Mapper
public interface ContProductParamDao {

	ContProductParamDO get(Integer contProductParamId);
	
	List<ContProductParamDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContProductParamDO contProductParam);
	
	int update(ContProductParamDO contProductParam);
	
	int remove(Integer cont_product_param_id);
	
	int batchRemove(Integer[] contProductParamIds);

	List<ContProductParamVO> listByDict(Integer contProductId);
}
