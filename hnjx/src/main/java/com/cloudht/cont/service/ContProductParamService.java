package com.cloudht.cont.service;

import com.cloudht.cont.domain.ContProductParamDO;
import com.cloudht.cont.vo.ContProductParamVO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yuxueling
 * @email 980899486@qq.com
 * @date 2018-09-23 13:01:59
 */
public interface ContProductParamService {
	
	ContProductParamDO get(Integer contProductParamId);
	
	List<ContProductParamDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContProductParamDO contProductParam);
	
	int update(ContProductParamDO contProductParam);
	
	int remove(Integer contProductParamId);

	int batchRemove(Integer[] contProductParamIds);

	List<ContProductParamVO> listByDict(Integer contProductId);
}
